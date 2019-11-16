package af.asr;

import java.util.Optional;

@SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "WeakerAccess", "unused"})
public class AutoTenantContext implements AutoCloseable {

    private final Optional<String> previousIdentifier;

    public AutoTenantContext(final String tenantName)
    {
        previousIdentifier = TenantContextHolder.identifier();
        TenantContextHolder.clear();
        TenantContextHolder.setIdentifier(tenantName);
    }

    public AutoTenantContext()
    {
        previousIdentifier = TenantContextHolder.identifier();
        TenantContextHolder.clear();
    }

    @Override
    public void close() {
        TenantContextHolder.clear();
        previousIdentifier.ifPresent(TenantContextHolder::setIdentifier);
    }
}