package af.asr.vault.service.security;


import af.asr.ApplicationName;
import af.asr.api.util.ApiConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApplicationPermission implements GrantedAuthority {
    public static final String URL_AUTHORITY = "maats_feather";

    private final List<PermissionSegmentMatcher> servletPathSegmentMatchers;

    private final AllowedOperation allowedOperation;

    private final boolean acceptTokenIntendedForForeignApplication;

    public ApplicationPermission(
            final String servletPath,
            final AllowedOperation allowedOperation,
            final boolean acceptTokenIntendedForForeignApplication) {
        this.allowedOperation = allowedOperation;
        servletPathSegmentMatchers = PermissionSegmentMatcher.getServletPathSegmentMatchers(servletPath);
        this.acceptTokenIntendedForForeignApplication = acceptTokenIntendedForForeignApplication;
    }


    AllowedOperation getAllowedOperation() {
        return allowedOperation;
    }

    @Override public String getAuthority() {
        return URL_AUTHORITY;
    }

    boolean matches(final FilterInvocation filterInvocation,
                    final ApplicationName applicationName,
                    final AnubisPrincipal principal) {
        return matches(filterInvocation.getRequest(), applicationName, principal);
    }

    boolean matches(final HttpServletRequest request,
                    final ApplicationName applicationName,
                    final AnubisPrincipal principal) {
        if (!acceptTokenIntendedForForeignApplication && !applicationName.toString().equals(principal.getForApplicationName()))
            return false;
        boolean isSu = principal.getUser().equals(ApiConstants.SYSTEM_SU);
        return matchesHelper(
                request.getServletPath(),
                request.getMethod(),
                (matcher, segment) -> matcher.matches(segment, principal, acceptTokenIntendedForForeignApplication, isSu));
    }

    private boolean matchesHelper(final String servletPath, final String method,
                                  @Nonnull final BiPredicate<PermissionSegmentMatcher, String> segmentMatcher) {
        final boolean opMatches = allowedOperation.containsHttpMethod(method);
        final String[] requestPathSegments = servletPath.split("/");

        if (servletPathSegmentMatchers.size() > requestPathSegments.length + 1)
            return false;

        if (servletPathSegmentMatchers.size() == requestPathSegments.length + 1)
            if (!lastSegmentIsStarSegment(servletPathSegmentMatchers))
                return false;

        if (servletPathSegmentMatchers.size() < requestPathSegments.length)
            if (!lastSegmentIsStarSegment(servletPathSegmentMatchers))
                return false;

        final Optional<Integer> indexOfFirstNonMappableSegment =
                Stream.iterate(0, n -> n + 1)
                        .limit(Math.min(servletPathSegmentMatchers.size(), requestPathSegments.length))
                        .filter(i -> !segmentMatcher.test(servletPathSegmentMatchers.get(i), requestPathSegments[i]))
                        .findFirst();

        return opMatches && !indexOfFirstNonMappableSegment.isPresent();
    }

    private static boolean lastSegmentIsStarSegment(
            final List<PermissionSegmentMatcher> servletPathSegmentMatchers) {
        return servletPathSegmentMatchers.get(servletPathSegmentMatchers.size() -1).isStarSegment();
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ApplicationPermission))
            return false;
        ApplicationPermission that = (ApplicationPermission) o;
        return Objects.equals(servletPathSegmentMatchers, that.servletPathSegmentMatchers)
                && allowedOperation == that.allowedOperation;
    }

    @Override public int hashCode() {
        return Objects.hash(servletPathSegmentMatchers, allowedOperation);
    }

    @Override
    public String toString() {
        return "ApplicationPermission{" +
                "servletPathSegmentMatchers='" + servletPathSegmentMatchers.stream().map(PermissionSegmentMatcher::getPermissionSegment).collect(Collectors.joining("/")) +
                "', allowedOperation=" + allowedOperation +
                '}';
    }
}