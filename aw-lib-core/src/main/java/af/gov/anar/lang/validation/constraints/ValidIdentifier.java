
package af.gov.anar.lang.validation.constraints;

import af.gov.anar.lang.validation.CheckIdentifier;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The annotated string must not be null, must have a minimum length of 2, and must be
 * equals to itself URL-encoded.
 */
@SuppressWarnings("unused")
@Target({ FIELD, METHOD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CheckIdentifier.class)
public @interface ValidIdentifier {
  String message() default "Invalid fineract identifier.";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };

  int maxLength() default 32;
  boolean optional() default false;
}
