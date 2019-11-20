package af.asr.permittedfeignclient.service.config;

import af.asr.api.util.AnnotatedErrorDecoder;
import af.asr.api.util.EmptyBodyInterceptor;
import af.asr.api.util.TenantedTargetInterceptor;
import af.asr.api.util.TokenedTargetInterceptor;
import af.asr.identity.api.v1.client.IdentityManager;
import af.asr.permittedfeignclient.service.LibraryConstants;
import af.asr.vault.service.config.EnableAnubis;
import feign.Client;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAnubis
@Configuration
public class PermittedFeignClientConfiguration {
    @Bean(name = LibraryConstants.LOGGER_NAME)
    public Logger logger() {
        return LoggerFactory.getLogger(LibraryConstants.LOGGER_NAME);
    }

    @Bean
    public IdentityManager identityManager(
            @SuppressWarnings("SpringJavaAutowiringInspection") final @Nonnull Client feignClient,
            final @Qualifier(LibraryConstants.LOGGER_NAME) @Nonnull Logger logger) {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .client(feignClient) //Integrates to ribbon.
                .errorDecoder(new AnnotatedErrorDecoder(logger, IdentityManager.class))
                .requestInterceptor(new TenantedTargetInterceptor())
                .requestInterceptor(new TokenedTargetInterceptor())
                .requestInterceptor(new EmptyBodyInterceptor())
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(IdentityManager.class, "http://identity-v1/identity/v1");
    }
}