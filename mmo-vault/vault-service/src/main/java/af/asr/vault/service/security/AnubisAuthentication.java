package af.asr.vault.service.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


class AnubisAuthentication implements Authentication {

    private boolean authenticated;

    private final String token;
    private final String userIdentifier;
    private final String forApplicationName;
    private final String sourceApplicationName;
    private final Set<ApplicationPermission> applicationPermissions;

    AnubisAuthentication(final String token,
                         final String userIdentifier,
                         final String forApplicationName,
                         final String sourceApplicationName,
                         final Set<ApplicationPermission> applicationPermissions) {
        authenticated = true;

        this.token = token;
        this.userIdentifier = userIdentifier;
        this.forApplicationName = forApplicationName;
        this.sourceApplicationName = sourceApplicationName;
        this.applicationPermissions = Collections.unmodifiableSet(new HashSet<>(applicationPermissions));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return applicationPermissions;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public String getDetails() {
        return null;
    }

    @Override
    public AnubisPrincipal getPrincipal() {
        return new AnubisPrincipal(userIdentifier, forApplicationName, sourceApplicationName);
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(final boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return userIdentifier;
    }

    @Override
    public String toString() {
        return "AnubisAuthentication{" +
                "authenticated=" + authenticated +
                ", token='" + token + '\'' +
                ", userIdentifier='" + userIdentifier + '\'' +
                ", forApplicationName='" + forApplicationName + '\'' +
                ", sourceApplicationName='" + sourceApplicationName + '\'' +
                ", applicationPermissions=" + applicationPermissions +
                '}';
    }
}