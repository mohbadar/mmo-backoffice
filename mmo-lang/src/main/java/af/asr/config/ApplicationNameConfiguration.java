package af.asr.config;

import af.asr.ApplicationName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SuppressWarnings("WeakerAccess")
@Configuration
public class ApplicationNameConfiguration {
    @Bean
    public ApplicationName applicationName(
            @Value("${spring.application.name}") final String springApplicationName) {
        return ApplicationName.fromSpringApplicationName(springApplicationName);
    }
}