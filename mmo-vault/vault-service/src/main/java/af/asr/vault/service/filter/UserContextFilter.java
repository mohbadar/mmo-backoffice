package af.asr.vault.service.filter;


import af.asr.api.util.UserContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserContextFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        final String principalName = authentication.getName();
        final Object credentials = authentication.getCredentials();

        if (principalName != null && credentials != null) {
            UserContextHolder.setAccessToken(principalName, credentials.toString());
        }

        filterChain.doFilter(request, response);
    }
}
