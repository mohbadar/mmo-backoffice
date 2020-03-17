
package af.gov.anar.lang.validation;


import af.gov.anar.lang.validation.constraints.ValidIdentifiers;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


public class CheckIdentifiers implements ConstraintValidator<ValidIdentifiers, List<String>> {
  private int maximumLength = 32;
  private boolean optional;

  @Override
  public void initialize(final ValidIdentifiers constraintAnnotation) {
    maximumLength = constraintAnnotation.maxLength();
    optional = constraintAnnotation.optional();

  }

  @Override
  public boolean isValid(final List<String> value, final ConstraintValidatorContext context) {
    if (optional && value == null)
      return true;

    return value != null && value.stream().allMatch(x -> x != null && CheckIdentifier.validate(x, maximumLength));
  }
}
