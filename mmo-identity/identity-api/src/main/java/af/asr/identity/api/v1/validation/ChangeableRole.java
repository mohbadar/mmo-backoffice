package af.asr.identity.api.v1.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@SuppressWarnings({"unused", "WeakerAccess"})
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {CheckRoleChangeable.class}
)
public @interface ChangeableRole {
    String message() default "The role 'pharaoh' cannot be changed or deleted.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}