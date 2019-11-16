package af.asr.validation.constaints;


import af.asr.validation.CheckLocalDateTimeString;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The annotated string must either null or must contain a time which can be
 * read by DateConverter.fromIsoString()
 */
@SuppressWarnings("unused")
@Target({ FIELD, METHOD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CheckLocalDateTimeString.class)
public @interface ValidLocalDateTimeString {
    String message() default "Invalid local date time string.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}