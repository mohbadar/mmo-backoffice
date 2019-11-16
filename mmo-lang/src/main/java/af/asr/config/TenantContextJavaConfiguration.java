package af.asr.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TenantContextJavaConfiguration {

    public TenantContextJavaConfiguration() {
        super();
    }

    @Bean
    public FilterRegistrationBean tenantFilterRegistration() {
        final TenantHeaderFilter tenantHeaderFilter = new TenantHeaderFilter();
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(tenantHeaderFilter);
        registration.addUrlPatterns("/*");
        registration.setName("tenantHeaderFilter");
        registration.setOrder(Integer.MIN_VALUE); //Before all other filters.  Especially the security filter.
        return registration;
    }
}