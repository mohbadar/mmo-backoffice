package af.asr.permittedfeignclient.service.service;

import af.asr.ApplicationName;
import af.asr.AutoTenantContext;
import af.asr.api.context.AutoGuest;
import af.asr.api.util.NotFoundException;
import af.asr.identity.api.v1.client.IdentityManager;
import af.asr.identity.api.v1.domain.Authentication;
import af.asr.permittedfeignclient.service.LibraryConstants;
import af.asr.security.RsaKeyPairFactory;
import af.asr.vault.service.config.TenantSignatureRepository;
import af.asr.vault.service.security.AmitAuthenticationException;
import af.asr.vault.service.token.TenantRefreshTokenSerializer;
import af.asr.vault.service.token.TokenSerializationResult;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class ApplicationAccessTokenService {
    private static final long REFRESH_TOKEN_LIFESPAN = TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES);

    private final String applicationName;
    private final TenantSignatureRepository tenantSignatureRepository;
    private final IdentityManager identityManager;
    private final TenantRefreshTokenSerializer tenantRefreshTokenSerializer;
    private final Logger logger;

    private final Map<TokenCacheKey, TokenSerializationResult> refreshTokenCache;
    private final Map<TokenCacheKey, Authentication> accessTokenCache;

    @Autowired
    public ApplicationAccessTokenService(
            final @Nonnull ApplicationName applicationName,
            final @Nonnull TenantSignatureRepository tenantSignatureRepository,
            final @Nonnull IdentityManager identityManager,
            final @Nonnull TenantRefreshTokenSerializer tenantRefreshTokenSerializer,
            @Qualifier(LibraryConstants.LOGGER_NAME) final @Nonnull Logger logger
    ) {

        this.applicationName = applicationName.toString();
        this.tenantSignatureRepository = tenantSignatureRepository;
        this.identityManager = identityManager;
        this.tenantRefreshTokenSerializer = tenantRefreshTokenSerializer;
        this.logger = logger;

        this.refreshTokenCache = ExpiringMap.builder()
                .maxSize(300)
                .expirationPolicy(ExpirationPolicy.CREATED)
                .expiration(30, TimeUnit.SECONDS)
                .entryLoader(tokenCacheKey -> this.createRefreshToken((TokenCacheKey) tokenCacheKey))
                .build();
        this.accessTokenCache = ExpiringMap.builder()
                .maxSize(300)
                .expirationPolicy(ExpirationPolicy.CREATED)
                .expiration(30, TimeUnit.SECONDS)
                .entryLoader(tokenCacheKey -> this.createAccessToken((TokenCacheKey) tokenCacheKey))
                .build();
    }

    @SuppressWarnings("WeakerAccess")
    public String getAccessToken(final String user, final String tenant) {
        return getAccessToken(user, tenant, null);
    }

    public String getAccessToken(final String user, final String tenant, final @Nullable String endpointSetIdentifier) {
        final TokenCacheKey tokenCacheKey = new TokenCacheKey(user, tenant, endpointSetIdentifier);
        final Authentication authentication = accessTokenCache.get(tokenCacheKey);
        return authentication.getAccessToken();
    }

    private Authentication createAccessToken(final TokenCacheKey tokenCacheKey) {
        final String refreshToken = refreshTokenCache.get(tokenCacheKey).getToken();
        try (final AutoTenantContext ignored = new AutoTenantContext(tokenCacheKey.getTenant())) {
            try (final AutoGuest ignored2 = new AutoGuest()) {
                logger.debug("Getting access token for {}", tokenCacheKey);
                return identityManager.refresh(refreshToken);
            } catch (final Exception e) {
                logger.error("Couldn't get access token from identity for {}.", tokenCacheKey, e);
                throw new NotFoundException("Couldn't get access token");
            }
        }
    }

    private TokenSerializationResult createRefreshToken(final TokenCacheKey tokenCacheKey) {
        try (final AutoTenantContext ignored = new AutoTenantContext(tokenCacheKey.getTenant())) {
            logger.debug("Creating refresh token for {}", tokenCacheKey);

            final Optional<RsaKeyPairFactory.KeyPairHolder> optionalSigningKeyPair
                    = tenantSignatureRepository.getLatestApplicationSigningKeyPair();

            final RsaKeyPairFactory.KeyPairHolder signingKeyPair = optionalSigningKeyPair.orElseThrow(AmitAuthenticationException::missingTenant);

            final TenantRefreshTokenSerializer.Specification specification = new TenantRefreshTokenSerializer.Specification()
                    .setSourceApplication(applicationName)
                    .setUser(tokenCacheKey.getUser())
                    .setSecondsToLive(REFRESH_TOKEN_LIFESPAN)
                    .setPrivateKey(signingKeyPair.privateKey())
                    .setKeyTimestamp(signingKeyPair.getTimestamp());

            tokenCacheKey.getEndpointSet().ifPresent(specification::setEndpointSet);

            return tenantRefreshTokenSerializer.build(specification);
        } catch (final Exception e) {
            logger.error("Couldn't create refresh token for {}.", tokenCacheKey, e);
            throw new NotFoundException("Couldn't create refresh token.");
        }
    }

}