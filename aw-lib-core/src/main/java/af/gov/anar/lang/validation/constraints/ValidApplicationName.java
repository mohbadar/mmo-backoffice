
package af.gov.anar.lang.validation.constraints;


import af.gov.anar.lang.validation.CheckApplicationName;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The annotated string must not be null, and must contain an application name of the form
 * appname-v1
 */
@SuppressWarnings("unused")
@Target({ FIELD, METHOD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CheckApplicationName.class)
public @interface ValidApplicationName {
  String message() default "Invalid fineract identifier.";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };
}
