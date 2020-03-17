
package af.gov.anar.lang.infrastructure.exception.service;

import af.gov.anar.lang.infrastructure.error.service.ServiceError;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.NestedServletException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Manage Service Accessibility and fire an exception whenever we can't service is not available.
 * To Enable this feature we need to add @EnableServiceException annotation.
 */
public final class ServiceExceptionFilter extends OncePerRequestFilter {

  public ServiceExceptionFilter() {
    super();
  }

  @Override
  protected void doFilterInternal(final HttpServletRequest request,
                                  final HttpServletResponse response,
                                  final FilterChain filterChain)
      throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (final NestedServletException ex) {
      if (ServiceException.class.isAssignableFrom(ex.getCause().getClass())) {
        @SuppressWarnings("ThrowableResultOfMethodCallIgnored") final ServiceException serviceException = ServiceException.class.cast(ex.getCause());
        final ServiceError serviceError = serviceException.serviceError();
        logger.info("Responding with a service error " + serviceError);
        response.sendError(serviceError.getCode(), serviceError.getMessage());
      } else {
        logger.info("Unexpected exception caught " + ex);
        throw ex;
      }
    }
  }
}
