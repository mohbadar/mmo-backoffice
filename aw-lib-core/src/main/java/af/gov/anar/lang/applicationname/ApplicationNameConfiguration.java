package af.gov.anar.lang.applicationname;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationNameConfiguration {
    @Bean
    public ApplicationName applicationName(
            @Value("${spring.application.name}") final String springApplicationName) {
        return ApplicationName.fromSpringApplicationName(springApplicationName);
    }
}
