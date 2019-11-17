package af.asr.vault.service.token;


import com.google.gson.Gson;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({"WeakerAccess", "unused"})
@Component
public class TenantAccessTokenSerializer {

    final private Gson gson;

    @Autowired
    public TenantAccessTokenSerializer(final @Qualifier("anubisGson") Gson gson) {
        this.gson = gson;
    }


    public static class Specification {
        private String keyTimestamp;
        private PrivateKey privateKey;
        private String user;
        private TokenContent tokenContent;
        private long secondsToLive;
        private String sourceApplication;

        public Specification setKeyTimestamp(final String keyTimestamp) {
            this.keyTimestamp = keyTimestamp;
            return this;
        }

        public Specification setPrivateKey(final PrivateKey privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public Specification setUser(final String user) {
            this.user = user;
            return this;
        }

        public Specification setSourceApplication(final String applicationIdentifier) {
            this.sourceApplication = applicationIdentifier;
            return this;
        }

        public Specification setTokenContent(final TokenContent tokenContent) {
            this.tokenContent = tokenContent;
            return this;
        }

        public Specification setSecondsToLive(final long secondsToLive) {
            this.secondsToLive = secondsToLive;
            return this;
        }
    }

    public TokenSerializationResult build(final Specification specification)
    {
        final long issued = System.currentTimeMillis();

        final String serializedTokenContent = gson.toJson(specification.tokenContent);

        if (specification.keyTimestamp == null) {
            throw new IllegalArgumentException("token signature timestamp must not be null.");
        }
        if (specification.privateKey == null) {
            throw new IllegalArgumentException("token signature privateKey must not be null.");
        }
        if (specification.sourceApplication == null) {
            throw new IllegalArgumentException("token signature source application must not be null.");
        }

        final JwtBuilder jwtBuilder =
                Jwts.builder()
                        .setSubject(specification.user)
                        .claim(TokenConstants.JWT_SIGNATURE_TIMESTAMP_CLAIM, specification.keyTimestamp)
                        .claim(TokenConstants.JWT_CONTENT_CLAIM, serializedTokenContent)
                        .claim(TokenConstants.JWT_SOURCE_APPLICATION_CLAIM, specification.sourceApplication)
                        .setIssuer(TokenType.TENANT.getIssuer())
                        .setIssuedAt(new Date(issued))
                        .signWith(SignatureAlgorithm.RS512, specification.privateKey);
        if (specification.secondsToLive <= 0) {
            throw new IllegalArgumentException("token secondsToLive must be positive.");
        }

        final Date expiration = new Date(issued + TimeUnit.SECONDS.toMillis(specification.secondsToLive));
        jwtBuilder.setExpiration(expiration);

        return new TokenSerializationResult(TokenConstants.PREFIX + jwtBuilder.compact(), expiration);
    }
}