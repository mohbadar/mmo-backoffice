package af.asr.async.core;

import af.asr.TenantContextHolder;
import af.asr.api.util.UserContext;
import af.asr.api.util.UserContextHolder;

import java.util.Optional;
import java.util.concurrent.Callable;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class DelegatingContextCallable<V> implements Callable<V> {

    private final Callable<V> delegate;
    private final Optional<String> optionalTenantIdentifier;
    private final Optional<UserContext> optionalUserContext;

    DelegatingContextCallable(final Callable<V> delegate, final String tenantIdentifier,
                              final UserContext userContext) {
        super();
        this.delegate = delegate;
        this.optionalTenantIdentifier = Optional.ofNullable(tenantIdentifier);
        this.optionalUserContext = Optional.ofNullable(userContext);
    }

    @Override
    public V call() throws Exception {
        try {
            TenantContextHolder.clear();
            optionalTenantIdentifier.ifPresent(TenantContextHolder::setIdentifier);

            UserContextHolder.clear();
            optionalUserContext.ifPresent(UserContextHolder::setUserContext);

            return this.delegate.call();
        } finally {
            TenantContextHolder.clear();
            UserContextHolder.clear();
        }
    }
}