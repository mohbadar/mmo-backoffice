package af.asr.api.util;

import feign.RequestInterceptor;
import feign.RequestTemplate;


@SuppressWarnings("WeakerAccess")
public class TokenedTargetInterceptor implements RequestInterceptor {

    @Override
    public void apply(final RequestTemplate template) {
        UserContextHolder.getUserContext()
                .map(UserContext::getAccessToken)
                .ifPresent(token -> template.header(ApiConstants.AUTHORIZATION_HEADER, token));
        UserContextHolder.getUserContext()
                .map(UserContext::getUser)
                .ifPresent(user -> template.header(ApiConstants.USER_HEADER, user));
    }
}