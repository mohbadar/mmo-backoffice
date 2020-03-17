
package af.gov.anar.api.util;

/**
 * @author Myrle Krantz
 */
@SuppressWarnings("WeakerAccess")
public class NotFoundException extends RuntimeException {

  public NotFoundException(final String reason) {
    super(reason);
  }
}
