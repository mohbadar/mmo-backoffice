package af.asr.vault.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@SuppressWarnings("unused")
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {CheckKeyTimestamp.class}
)
public @interface ValidKeyTimestamp {
    String message() default "Invalid key timestamp.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}