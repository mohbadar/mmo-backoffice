
package af.gov.anar.lang.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

class TestHelper {

  static <T> boolean isValid(final T annotatedInstance)
  {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final Validator validator = factory.getValidator();
    final Set<ConstraintViolation<T>> errors = validator.validate(annotatedInstance);

    return errors.size() == 0;
  }
}
