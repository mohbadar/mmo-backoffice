package af.asr.vault.service.filter;


import af.asr.api.util.ApiConstants;
import af.asr.vault.api.RoleConstants;
import af.asr.vault.api.TokenConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;


public class IsisAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    public IsisAuthenticatedProcessingFilter(final AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
        setCheckForPrincipalChanges(true);
    }

    @Override protected Object getPreAuthenticatedPrincipal(final HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(ApiConstants.USER_HEADER)).orElse(RoleConstants.GUEST_USER_IDENTIFIER);
    }

    @Override protected Object getPreAuthenticatedCredentials(final HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(ApiConstants.AUTHORIZATION_HEADER)).orElse(TokenConstants.NO_AUTHENTICATION);
    }
}