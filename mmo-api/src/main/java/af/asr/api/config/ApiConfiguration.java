package af.asr.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"af.asr.api.util"})
public class ApiConfiguration {

    public static final String LOGGER_NAME = "api-logger";

    @Bean(name = LOGGER_NAME)
    public Logger logger() {
        return LoggerFactory.getLogger(LOGGER_NAME);
    }
}