
package af.gov.anar.lang.validation;


import af.gov.anar.lang.validation.constraints.ValidIdentifier;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class CheckIdentifier implements ConstraintValidator<ValidIdentifier, String> {
   private int maximumLength = 32;
   private boolean optional = false;
   @Override
   public void initialize(final ValidIdentifier constraint) {
      maximumLength = constraint.maxLength();
      optional = constraint.optional();
   }

   @Override
   public boolean isValid(final String obj, final ConstraintValidatorContext context) {
      if (obj == null)
         return optional;

      return validate(obj, maximumLength);
   }

   static boolean validate(final String obj, final int maximumLength) {
      if (obj.length() < 2)
         return false;

      if (obj.length() > maximumLength)
         return false;

      try {
         return encode(obj).equals(obj);
      }
      catch (UnsupportedEncodingException e) {
         return false; //If we can't encode with UTF-8, then there are no valid names.
      }
   }

   static private String encode(String identifier) throws UnsupportedEncodingException {
      return URLEncoder.encode(identifier, "UTF-8");
   }
}
