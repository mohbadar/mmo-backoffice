
package af.gov.anar.lang.infrastructure;

import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import af.gov.anar.lang.infrastructure.exception.service.ServiceExceptionFilter;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.util.NestedServletException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceExceptionFilterTest {

  public ServiceExceptionFilterTest() {
    super();
  }

  @Test
  public void shouldProceedNoServiceException() throws Exception {
    final HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
    final HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
    final FilterChain mockedFilterChain = Mockito.mock(FilterChain.class);

    final ServiceExceptionFilter serviceExceptionFilter = new ServiceExceptionFilter();
    serviceExceptionFilter.doFilter(mockedRequest, mockedResponse, mockedFilterChain);

    Mockito.verifyZeroInteractions(mockedResponse);
    Mockito.verify(mockedFilterChain, Mockito.times(1)).doFilter(mockedRequest, mockedResponse);
  }

  @Test
  public void shouldFilterServiceException() throws Exception {
    final String errorMessage = "Should fail.";

    final HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
    final HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);

    final FilterChain mockedFilterChain = (request, response) -> {
      throw new NestedServletException(errorMessage, ServiceException.conflict(errorMessage));
    };

    final ServiceExceptionFilter serviceExceptionFilter = new ServiceExceptionFilter();
    serviceExceptionFilter.doFilter(mockedRequest, mockedResponse, mockedFilterChain);

    Mockito.verify(mockedResponse, Mockito.times(1)).sendError(Mockito.eq(409), Mockito.eq(errorMessage));
  }
}
