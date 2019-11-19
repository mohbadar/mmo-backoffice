package af.asr.async.config;



import af.asr.async.core.DelegatingContextExecutor;
import af.asr.async.util.AsyncConstants;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;


import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncProcessingJavaConfiguration
        implements AsyncConfigurer {

    private final Environment env;

    @Autowired
    public AsyncProcessingJavaConfiguration(final Environment env) {
        super();
        this.env = env;
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(
                Integer.valueOf(this.env.getProperty(AsyncConstants.CORE_POOL_SIZE_PROP, AsyncConstants.CORE_POOL_SIZE_DEFAULT)));
        executor.setMaxPoolSize(
                Integer.valueOf(this.env.getProperty(AsyncConstants.MAX_POOL_SIZE_PROP, AsyncConstants.MAX_POOL_SIZE_DEFAULT)));
        executor.setQueueCapacity(
                Integer.valueOf(this.env.getProperty(AsyncConstants.QUEUE_CAPACITY_PROP, AsyncConstants.QUEUE_CAPACITY_DEFAULT)));
        executor.setThreadNamePrefix(
                this.env.getProperty(AsyncConstants.THREAD_NAME_PROP, AsyncConstants.THREAD_NAME_DEFAULT));
        executor.initialize();

        return new DelegatingContextExecutor(new DelegatingSecurityContextAsyncTaskExecutor(executor));
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}