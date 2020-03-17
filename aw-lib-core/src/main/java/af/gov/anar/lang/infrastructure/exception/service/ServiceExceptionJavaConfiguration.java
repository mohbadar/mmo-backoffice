
package af.gov.anar.lang.infrastructure.exception.service;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceExceptionJavaConfiguration {

  public ServiceExceptionJavaConfiguration() {
    super();
  }

  @Bean
  public FilterRegistrationBean serviceExceptionFilterRegistration() {
    final ServiceExceptionFilter serviceExceptionFilter = new ServiceExceptionFilter();
    final FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(serviceExceptionFilter);
    registration.addUrlPatterns("/*");
    registration.setName("serviceExceptionFilter");
    return registration;
  }
}
