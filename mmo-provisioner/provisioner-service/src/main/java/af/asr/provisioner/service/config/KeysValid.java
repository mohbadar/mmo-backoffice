package af.asr.provisioner.service.config;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@SuppressWarnings("unused")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {CheckKeysValid.class}
)
public @interface KeysValid {
    String message() default "Public and private keys must be valid and matching.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}