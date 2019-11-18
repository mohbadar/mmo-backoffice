package af.asr.identity.service.config;


import af.asr.async.config.EnableAsync;
import af.asr.cassandra.config.EnableCassandra;
import af.asr.command.config.EnableCommandProcessing;
import af.asr.config.EnableServiceException;
import af.asr.config.EnableTenantContext;
import af.asr.crypto.config.EnableCrypto;
import af.asr.identity.service.internal.util.IdentityConstants;
import af.asr.vault.service.config.EnableAnubis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@EnableDiscoveryClient
@EnableAsync
@EnableTenantContext
@EnableCassandra
@EnableCommandProcessing
@EnableServiceException
@EnableCrypto
@EnableAnubis(provideSignatureStorage = false)
@ComponentScan({
        "af.asr.identity.service.*"
})
public class IdentityServiceConfig extends WebMvcConfigurerAdapter {

    public IdentityServiceConfig() {}


    @Bean(name = IdentityConstants.JSON_SERIALIZER_NAME)
    public Gson gson() {
        return new GsonBuilder().create();
    }

    @Bean(name = IdentityConstants.LOGGER_NAME)
    public Logger logger() {
        return LoggerFactory.getLogger(IdentityConstants.LOGGER_NAME);
    }

    @Override
    public void configurePathMatch(final PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(Boolean.FALSE);
    }

    public static void main(String[] args) {
        SpringApplication.run(IdentityServiceConfig.class, args);
    }
}