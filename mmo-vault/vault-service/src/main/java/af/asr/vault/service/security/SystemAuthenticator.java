package af.asr.vault.service.security;


import static af.asr.vault.service.config.AnubisConstants.LOGGER_NAME;

import af.asr.TenantContextHolder;
import af.asr.api.util.ApiConstants;
import af.asr.vault.api.TokenConstants;
import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.provider.InvalidKeyTimestampException;
import af.asr.vault.service.provider.SystemRsaKeyProvider;
import af.asr.vault.service.service.PermittableService;
import af.asr.vault.service.token.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class SystemAuthenticator {
    private final SystemRsaKeyProvider systemRsaKeyProvider;
    private final Set<ApplicationPermission> permissions;
    private final Logger logger;

    @Autowired
    public SystemAuthenticator(
            final SystemRsaKeyProvider systemRsaKeyProvider,
            final PermittableService permittableService,
            final @Qualifier(LOGGER_NAME) Logger logger) {
        this.systemRsaKeyProvider = systemRsaKeyProvider;
        this.permissions = permittableService.getPermittableEndpointsAsPermissions(AcceptedTokenType.SYSTEM);
        this.logger = logger;
    }

    @SuppressWarnings("WeakerAccess")
    public AnubisAuthentication authenticate(
            final String user,
            final String token,
            final String keyTimestamp) {
        if (!user.equals(ApiConstants.SYSTEM_SU))
            throw AmitAuthenticationException.invalidHeader();

        try {
            final JwtParser jwtParser = Jwts.parser()
                    .setSigningKey(systemRsaKeyProvider.getPublicKey(keyTimestamp))
                    .requireIssuer(TokenType.SYSTEM.getIssuer())
                    .require(TokenConstants.JWT_SIGNATURE_TIMESTAMP_CLAIM, keyTimestamp);

            TenantContextHolder.identifier().ifPresent(jwtParser::requireSubject);

            //noinspection unchecked
            final Jwt<Header, Claims> result = jwtParser.parse(token);
            if (result.getBody() == null ||
                    result.getBody().getAudience() == null) {
                logger.info("System token for user {}, with key timestamp {} failed to authenticate. Audience was not set.", user, keyTimestamp);
                throw AmitAuthenticationException.invalidToken();
            }

            logger.info("System token for user {}, with key timestamp {} authenticated successfully.", user, keyTimestamp);

            return new AnubisAuthentication(
                    TokenConstants.PREFIX + token,
                    user,
                    result.getBody().getAudience(),
                    TokenType.SYSTEM.getIssuer(),
                    permissions);
        }
        catch (final JwtException e) {
            logger.debug("token = {}", token);
            logger.info("System token for user {}, with key timestamp {} failed to authenticate. Exception was {}", user, keyTimestamp, e.getMessage());
            throw AmitAuthenticationException.invalidToken();
        } catch (final InvalidKeyTimestampException e) {
            logger.info("System token for user {}, with key timestamp {} failed to authenticate. Exception was {}", user, keyTimestamp, e.getMessage());
            throw AmitAuthenticationException.invalidTokenKeyTimestamp("system", keyTimestamp);
        }
    }
}