package af.asr.vault.service.security;


import af.asr.ApplicationName;
import af.asr.vault.api.TokenConstants;
import af.asr.vault.api.domain.TokenContent;
import af.asr.vault.api.domain.TokenPermission;
import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.provider.InvalidKeyTimestampException;
import af.asr.vault.service.provider.TenantRsaKeyProvider;
import af.asr.vault.service.service.PermittableService;
import af.asr.vault.service.token.TokenType;
import com.google.gson.Gson;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static af.asr.vault.service.config.AnubisConstants.LOGGER_NAME;

@Component
public class TenantAuthenticator {
    private final TenantRsaKeyProvider tenantRsaKeyProvider;
    private final String applicationNameWithVersion;
    private final Gson gson;
    private final Set<ApplicationPermission> guestPermissions;
    private final Logger logger;

    @Autowired
    public TenantAuthenticator(
            final TenantRsaKeyProvider tenantRsaKeyProvider,
            final ApplicationName applicationName,
            final PermittableService permittableService,
            final @Qualifier("anubisGson") Gson gson,
            final @Qualifier(LOGGER_NAME) Logger logger) {
        this.tenantRsaKeyProvider = tenantRsaKeyProvider;
        this.applicationNameWithVersion = applicationName.toString();
        this.gson = gson;
        this.guestPermissions
                = permittableService.getPermittableEndpointsAsPermissions(AcceptedTokenType.GUEST);
        this.logger = logger;
    }

    AnubisAuthentication authenticate(
            final @Nonnull String user,
            final @Nonnull String token,
            final @Nonnull String keyTimestamp) {
        try {
            final JwtParser parser = Jwts.parser()
                    .requireSubject(user)
                    .requireIssuer(TokenType.TENANT.getIssuer())
                    .setSigningKey(tenantRsaKeyProvider.getPublicKey(keyTimestamp));

            @SuppressWarnings("unchecked") Jwt<Header, Claims> jwt = parser.parse(token);

            final String serializedTokenContent = jwt.getBody().get(TokenConstants.JWT_CONTENT_CLAIM, String.class);
            final String sourceApplication = jwt.getBody().get(TokenConstants.JWT_SOURCE_APPLICATION_CLAIM, String.class);
            final TokenContent tokenContent = gson.fromJson(serializedTokenContent, TokenContent.class);
            if (tokenContent == null)
                throw AmitAuthenticationException.missingTokenContent();

            final Set<ApplicationPermission> permissions = translatePermissions(tokenContent.getTokenPermissions());
            permissions.addAll(guestPermissions);

            logger.info("Tenant token for user {}, with key timestamp {} authenticated successfully.", user, keyTimestamp);

            return new AnubisAuthentication(TokenConstants.PREFIX + token,
                    jwt.getBody().getSubject(), applicationNameWithVersion, sourceApplication, permissions
            );
        }
        catch (final JwtException e) {
            logger.info("Tenant token for user {}, with key timestamp {} failed to authenticate. Exception was {}", user, keyTimestamp, e);
            throw AmitAuthenticationException.invalidToken();
        } catch (final InvalidKeyTimestampException e) {
            logger.info("Tenant token for user {}, with key timestamp {} failed to authenticate. Exception was {}", user, keyTimestamp, e);
            throw AmitAuthenticationException.invalidTokenKeyTimestamp("tenant", keyTimestamp);
        }
    }

    private Set<ApplicationPermission> translatePermissions(
            @Nonnull final List<TokenPermission> tokenPermissions)
    {
        return tokenPermissions.stream()
                .filter(x -> x.getPath().startsWith(applicationNameWithVersion))
                .flatMap(this::getAppPermissionFromTokenPermission)
                .collect(Collectors.toSet());
    }

    private Stream<ApplicationPermission> getAppPermissionFromTokenPermission(final TokenPermission tokenPermission) {
        final String servletPath = tokenPermission.getPath().substring(applicationNameWithVersion.length());
        return tokenPermission.getAllowedOperations().stream().map(x -> new ApplicationPermission(servletPath, x, false));
    }
}