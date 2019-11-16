package af.asr.config;

import af.asr.TenantContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public final class TenantHeaderFilter extends OncePerRequestFilter {

    public static final String TENANT_HEADER = "X-Tenant-Identifier";

    public TenantHeaderFilter() {
        super();
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        final String tenantHeaderValue = request.getHeader(TenantHeaderFilter.TENANT_HEADER);

        if (tenantHeaderValue == null || tenantHeaderValue.isEmpty()) {
            response.sendError(400, "Header [" + TENANT_HEADER + "] must be given!");
        } else {
            TenantContextHolder.clear();
            TenantContextHolder.setIdentifier(tenantHeaderValue);
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            TenantContextHolder.clear();
        }
    }
}