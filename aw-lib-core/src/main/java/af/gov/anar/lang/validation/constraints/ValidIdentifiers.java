
package af.gov.anar.lang.validation.constraints;


import af.gov.anar.lang.validation.CheckIdentifiers;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Checks that a list of identifiers is valid, in the same way that ValidIdentifier checks if a single identifier is
 * valid.

 */
@SuppressWarnings("unused")
@Target({ FIELD, METHOD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CheckIdentifiers.class)
public @interface ValidIdentifiers {
  String message() default "One or more invalid identifiers.";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };

  int maxLength() default 32;
  boolean optional() default false;
}