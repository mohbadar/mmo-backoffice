package af.asr.api.util;

import af.asr.api.config.ApiConfiguration;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.stereotype.Component;


@SuppressWarnings("unused")
@Component
public class ApiFactory {

    private final Logger logger;

    @Autowired
    public ApiFactory(@Qualifier(ApiConfiguration.LOGGER_NAME) final Logger logger) {
        this.logger = logger;
    }

    public <T> T create(final Class<T> clazz, final String target) {
        final CookieInterceptingClient client = new CookieInterceptingClient(target);
        return Feign.builder()
                .contract(new SpringMvcContract())
                .client(client)
                .errorDecoder(new AnnotatedErrorDecoder(logger, clazz))
                .requestInterceptor(new TenantedTargetInterceptor())
                .requestInterceptor(new TokenedTargetInterceptor())
                .requestInterceptor(new EmptyBodyInterceptor())
                .requestInterceptor(client.getCookieInterceptor())
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(clazz, target);
    }

    public <T> FeignTargetWithCookieJar<T> createWithCookieJar(final Class<T> clazz, final String target) {
        final CookieInterceptingClient client = new CookieInterceptingClient(target);
        final T feignTarget = Feign.builder()
                .contract(new SpringMvcContract())
                .client(client)
                .errorDecoder(new AnnotatedErrorDecoder(logger, clazz))
                .requestInterceptor(new TenantedTargetInterceptor())
                .requestInterceptor(new TokenedTargetInterceptor())
                .requestInterceptor(new EmptyBodyInterceptor())
                .requestInterceptor(client.getCookieInterceptor())
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(clazz, target);

        return new FeignTargetWithCookieJar<>(feignTarget, client);
    }
}