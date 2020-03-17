package af.gov.anar.lib.cors.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The configuration class for having package location to scan
 */
@Configuration
@ComponentScan(basePackages = "af.*")
public class CorsConfig {
}
