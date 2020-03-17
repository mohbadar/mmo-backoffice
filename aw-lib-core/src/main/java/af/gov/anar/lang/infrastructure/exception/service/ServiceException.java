
package af.gov.anar.lang.infrastructure.exception.service;

import af.gov.anar.lang.infrastructure.error.service.ServiceError;

import java.text.MessageFormat;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class ServiceException extends RuntimeException {

  private final ServiceError serviceError;

  public ServiceException(final ServiceError serviceError) {
    super(serviceError.getMessage());
    this.serviceError = serviceError;
  }

  public static ServiceException badRequest(final String message, final Object... args) {
    return new ServiceException(ServiceError
        .create(400)
        .message(MessageFormat.format(message, args))
        .build());
  }

  public static ServiceException notFound(final String message, final Object... args) {
    return new ServiceException(ServiceError
        .create(404)
        .message(MessageFormat.format(message, args))
        .build());
  }

  public static ServiceException conflict(final String message, final Object... args) {

    return new ServiceException(ServiceError
        .create(409)
        .message(MessageFormat.format(message, args))
        .build());
  }

  public static ServiceException internalError(final String message, final Object... args) {
    return new ServiceException(ServiceError
        .create(500)
        .message(MessageFormat.format(message, args))
        .build());
  }

  public ServiceError serviceError() {
    return this.serviceError;
  }

  @Override
  public String toString() {
    return "ServiceException{" +
            "serviceError=" + serviceError +
            '}';
  }
}
