package af.asr.api.util;


import af.asr.api.config.ApiConfiguration;
import feign.Feign;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

//@EnableApiFactory (for logger)
@SuppressWarnings({"unused"})
public class CustomFeignClientsConfiguration extends FeignClientsConfiguration {
    private static class AnnotatedErrorDecoderFeignBuilder extends Feign.Builder {
        private final Logger logger;

        AnnotatedErrorDecoderFeignBuilder(final Logger logger) {
            this.logger = logger;
        }

        public <T> T target(Target<T> target) {
            this.errorDecoder(new AnnotatedErrorDecoder(logger, target.type()));
            return build().newInstance(target);
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public TenantedTargetInterceptor tenantedTargetInterceptor()
    {
        return new TenantedTargetInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public TokenedTargetInterceptor tokenedTargetInterceptor()
    {
        return new TokenedTargetInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public Decoder feignDecoder() {
        return new GsonDecoder();
    }

    @Bean
    @ConditionalOnMissingBean
    public Encoder feignEncoder() {
        return new GsonEncoder();
    }

    @Bean(name = ApiConfiguration.LOGGER_NAME)
    public Logger logger() {
        return LoggerFactory.getLogger(ApiConfiguration.LOGGER_NAME);
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    public Feign.Builder feignBuilder(@Qualifier(ApiConfiguration.LOGGER_NAME) final Logger logger) {
        return new AnnotatedErrorDecoderFeignBuilder(logger);
    }
}
