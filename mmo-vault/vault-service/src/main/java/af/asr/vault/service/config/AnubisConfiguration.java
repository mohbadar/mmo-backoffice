package af.asr.vault.service.config;

import static af.asr.vault.service.config.AnubisConstants.LOGGER_NAME;

import af.asr.cassandra.config.EnableCassandra;
import af.asr.config.EnableApplicationName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableApplicationName
@EnableCassandra
@EnableConfigurationProperties(AnubisProperties.class)
public class AnubisConfiguration {

    @Bean(name = LOGGER_NAME)
    public Logger logger() {
        return LoggerFactory.getLogger(LOGGER_NAME);
    }

    @Bean()
    public Gson anubisGson()
    {
        return new GsonBuilder().create();
    }
}