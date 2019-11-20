package af.asr.permittedfeignclient.service.annotation;


import static af.asr.api.config.ApiConfiguration.LOGGER_NAME;
import af.asr.api.util.AnnotatedErrorDecoder;
import af.asr.api.util.TenantedTargetInterceptor;
import af.asr.permittedfeignclient.service.security.ApplicationTokenedTargetInterceptor;
import af.asr.permittedfeignclient.service.service.ApplicationAccessTokenService;
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
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class PermittedFeignClientsConfiguration extends FeignClientsConfiguration {
    private static class FeignBuilder extends Feign.Builder {
        private final ApplicationAccessTokenService applicationAccessTokenService;
        private final Logger logger;

        FeignBuilder(
                final ApplicationAccessTokenService applicationAccessTokenService,
                final Logger logger) {
            this.applicationAccessTokenService = applicationAccessTokenService;
            this.logger = logger;
        }

        public <T> T target(final Target<T> target) {
            this.errorDecoder(new AnnotatedErrorDecoder(logger, target.type()));
            this.requestInterceptor(new TenantedTargetInterceptor());
            this.requestInterceptor(new ApplicationTokenedTargetInterceptor(
                    applicationAccessTokenService,
                    target.type()));
            return build().newInstance(target);
        }
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

    @Bean(name = LOGGER_NAME)
    @ConditionalOnMissingBean
    public Logger logger() {
        return LoggerFactory.getLogger(LOGGER_NAME);
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    public Feign.Builder permittedFeignBuilder(
            @SuppressWarnings("SpringJavaAutowiringInspection")
            final ApplicationAccessTokenService applicationAccessTokenService,
            @Qualifier(LOGGER_NAME) final Logger logger) {
        return new FeignBuilder(
                applicationAccessTokenService,
                logger);
    }
}