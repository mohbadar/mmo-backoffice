package af.asr.identity.api.v1.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@SuppressWarnings({"unused", "WeakerAccess"})
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {CheckNotRootRole.class}
)
public @interface NotRootRole {
    String message() default "The role 'pharaoh' cannot be assigned to a user.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}