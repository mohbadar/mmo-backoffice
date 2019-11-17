package af.asr.vault.api.client;


import af.asr.api.util.AnnotatedErrorDecoder;
import af.asr.api.util.TenantedTargetInterceptor;
import af.asr.api.util.TokenedTargetInterceptor;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.slf4j.Logger;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

@SuppressWarnings("unused")
public interface AnubisApiFactory {

    static Anubis create(final String target, final Logger logger) {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .errorDecoder(new AnnotatedErrorDecoder(logger, Anubis.class))
                .requestInterceptor(new TenantedTargetInterceptor())
                .requestInterceptor(new TokenedTargetInterceptor())
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .target(Anubis.class, target);
    }
}