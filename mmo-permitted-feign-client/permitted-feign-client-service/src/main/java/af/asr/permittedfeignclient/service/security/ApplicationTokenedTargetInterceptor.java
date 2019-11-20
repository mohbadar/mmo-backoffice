package af.asr.permittedfeignclient.service.security;

import af.asr.TenantContextHolder;
import af.asr.api.util.ApiConstants;
import af.asr.api.util.UserContextHolder;
import af.asr.permittedfeignclient.service.annotation.EndpointSet;
import af.asr.permittedfeignclient.service.service.ApplicationAccessTokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import javax.annotation.Nonnull;

import org.springframework.util.Assert;

public class ApplicationTokenedTargetInterceptor implements RequestInterceptor {
    private final ApplicationAccessTokenService applicationAccessTokenService;
    private final String endpointSetIdentifier;

    public <T> ApplicationTokenedTargetInterceptor(
            final @Nonnull ApplicationAccessTokenService applicationAccessTokenService,
            final @Nonnull Class<T> type) {
        Assert.notNull(applicationAccessTokenService);
        Assert.notNull(type);

        this.applicationAccessTokenService = applicationAccessTokenService;
        final EndpointSet endpointSet = type.getAnnotation(EndpointSet.class);
        Assert.notNull(endpointSet, "Permitted feign clients require an endpoint set identifier provided via @EndpointSet.");
        this.endpointSetIdentifier = endpointSet.identifier();
    }

    @Override
    public void apply(final RequestTemplate template) {
        UserContextHolder.getUserContext().ifPresent(userContext -> {
            final String accessToken = applicationAccessTokenService.getAccessToken(userContext.getUser(),
                    TenantContextHolder.checkedGetIdentifier(), endpointSetIdentifier);

            template.header(ApiConstants.USER_HEADER, userContext.getUser());
            template.header(ApiConstants.AUTHORIZATION_HEADER, accessToken);
        });
    }
}