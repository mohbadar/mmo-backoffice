package af.asr.api.util;


import static af.asr.config.TenantHeaderFilter.TENANT_HEADER;
import feign.RequestInterceptor;
import feign.RequestTemplate;

@SuppressWarnings("WeakerAccess")
public class TenantedTargetInterceptor implements RequestInterceptor {

    @Override
    public void apply(final RequestTemplate template) {
        TenantContextHolder.identifier()
                .ifPresent(tenantIdentifier -> template.header(TENANT_HEADER, tenantIdentifier));
    }
}