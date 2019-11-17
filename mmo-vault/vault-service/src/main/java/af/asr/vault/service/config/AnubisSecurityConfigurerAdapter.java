package af.asr.vault.service.config;

import af.asr.ApplicationName;

import af.asr.vault.service.filter.IsisAuthenticatedProcessingFilter;
import af.asr.vault.service.filter.UserContextFilter;
import af.asr.vault.service.security.ApplicationPermission;
import af.asr.vault.service.security.IsisAuthenticatedAuthenticationProvider;
import af.asr.vault.service.security.UrlPermissionChecker;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
@Configuration
@EnableWebSecurity
public class AnubisSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    final private Logger logger;
    final private ApplicationName applicationName;

    public AnubisSecurityConfigurerAdapter(final @Qualifier(AnubisConstants.LOGGER_NAME) Logger logger,
                                           final ApplicationName applicationName) {
        this.logger = logger;
        this.applicationName = applicationName;
    }

    @PostConstruct
    public void configureSecurityContext()
    {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    /**
     * In and of itself, registering the securityFilterChain would not be necessary.  It's already
     * registered.  But the order is not fixed in the version of spring we are working with, so we
     * need to set it here.  It is set to zero in a later version, but we should continue to set it
     * explicitly here.
     */
    @Bean
    public FilterRegistrationBean securityFilterChain(@Qualifier(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME)
                                                      final Filter securityFilter) {
        final FilterRegistrationBean registration = new FilterRegistrationBean(securityFilter);
        registration.setOrder(Integer.MIN_VALUE + 1); //Just after the tenant filter.
        registration.setName(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME);
        return registration;
    }

    @Bean
    public FilterRegistrationBean userContextFilter()
    {
        final FilterRegistrationBean registration = new FilterRegistrationBean(new UserContextFilter());
        registration.setOrder(Integer.MIN_VALUE + 2);
        registration.addUrlPatterns("*");

        return registration;
    }

    private AccessDecisionManager defaultAccessDecisionManager() {
        final List<AccessDecisionVoter<?>> voters = new ArrayList<>();
        voters.add(new UrlPermissionChecker(logger, applicationName));
        return new UnanimousBased(voters);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        final Filter filter = new IsisAuthenticatedProcessingFilter(super.authenticationManager());

        http.httpBasic().disable()
                .csrf().disable()
                .apply (new UrlAuthorizationConfigurer<>(getApplicationContext()))
                .getRegistry().anyRequest().hasAuthority(ApplicationPermission.URL_AUTHORITY)
                .accessDecisionManager(defaultAccessDecisionManager()).and()
                .formLogin().disable()
                .logout().disable()
                .addFilter(filter)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().accessDeniedHandler(
                (request, response, accessDeniedException) -> response.setStatus(HttpStatus.SC_NOT_FOUND));
    }

    @Autowired
    public void configureGlobal(
            final AuthenticationManagerBuilder auth,
            @SuppressWarnings("SpringJavaAutowiringInspection") final IsisAuthenticatedAuthenticationProvider provider)
            throws Exception {
        auth.authenticationProvider(provider);
    }
}