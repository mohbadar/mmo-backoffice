package af.asr.vault.service.service;


import af.asr.vault.service.security.AnubisPrincipal;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class PermissionSegmentMatcher {
    final private String permissionSegment;

    private PermissionSegmentMatcher(final String permissionSegment) {
        this.permissionSegment = permissionSegment;
    }

    public boolean isStarSegment() {
        return permissionSegment.equals("*");
    }

    private boolean isUserIdentifierSegment() {
        return permissionSegment.equals("{useridentifier}");
    }

    private boolean isApplicationIdentifierSegment() {
        return permissionSegment.equals("{applicationidentifier}");
    }

    boolean isParameterSegment() {
        return permissionSegment.startsWith("{") && permissionSegment.endsWith("}");
    }

    public String getPermissionSegment() { return permissionSegment; }

    public boolean matches(
            final String requestSegment,
            final AnubisPrincipal principal,
            boolean acceptTokenIntendedForForeignApplication,
            boolean isSu) {
        if (isStarSegment())
            return true;
        else if (isUserIdentifierSegment())
            return requestSegment.equals(principal.getUser());
        else if (acceptTokenIntendedForForeignApplication && isApplicationIdentifierSegment())
            return requestSegment.equals(principal.getForApplicationName());
        else if (!acceptTokenIntendedForForeignApplication && isApplicationIdentifierSegment())
            return requestSegment.equals(principal.getSourceApplicationName());
        else if (isParameterSegment())
            return isSu;
        else
            return permissionSegment.equals(requestSegment);
    }

    static public List<PermissionSegmentMatcher> getServletPathSegmentMatchers(final @Nonnull String servletPath) {
        return Arrays.stream(servletPath.split("/"))
                .map(PermissionSegmentMatcher::new)
                .collect(Collectors.toList());
    }
}