package af.asr.async.core;


import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class DelegatingContextExecutor implements AsyncTaskExecutor {

    private final DelegatingSecurityContextAsyncTaskExecutor delegate;

    public DelegatingContextExecutor(final DelegatingSecurityContextAsyncTaskExecutor delegate) {
        super();
        this.delegate = delegate;
    }

    @Override
    public void execute(final Runnable task, final long startTimeout) {
        final Runnable taskWrapper = this.wrap(task);
        this.delegate.execute(taskWrapper, startTimeout);
    }

    @Override
    public Future<?> submit(final Runnable task) {
        final Runnable taskWrapper = this.wrap(task);
        return this.delegate.submit(taskWrapper);
    }

    @Override
    public <T> Future<T> submit(final Callable<T> task) {
        final Callable<T> taskWrapper = this.wrap(task);
        return this.delegate.submit(taskWrapper);
    }

    @Override
    public void execute(final Runnable task) {
        final Runnable taskWrapper = this.wrap(task);
        this.delegate.execute(taskWrapper);
    }

    private Runnable wrap(final Runnable task) {
        final String tenantIdentifier = TenantContextHolder.identifier().orElse(null);
        final UserContext userContext = UserContextHolder.getUserContext().orElse(null);
        return new DelegatingContextRunnable(task, tenantIdentifier, userContext);
    }

    private <T> Callable<T> wrap(final Callable<T> task) {
        final String tenantIdentifier = TenantContextHolder.identifier().orElse(null);
        final UserContext userContext = UserContextHolder.getUserContext().orElse(null);
        return new DelegatingContextCallable<>(task, tenantIdentifier, userContext);
    }
}