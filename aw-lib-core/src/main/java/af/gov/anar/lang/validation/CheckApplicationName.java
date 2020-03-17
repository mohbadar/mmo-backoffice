
package af.gov.anar.lang.validation;

import af.gov.anar.lang.applicationname.ApplicationName;
import af.gov.anar.lang.validation.constraints.ValidApplicationName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CheckApplicationName implements ConstraintValidator<ValidApplicationName, String> {
   public void initialize(final ValidApplicationName constraint) {
   }

   public boolean isValid(final String obj, final ConstraintValidatorContext context) {
      if (obj == null)
         return false;

      try {
         ApplicationName.fromSpringApplicationName(obj);
         return true;
      }
      catch (final IllegalArgumentException ignored)
      {
         return false;
      }
   }
}
