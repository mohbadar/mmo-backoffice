package af.asr.identity.api.v1.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckNotRootRole implements ConstraintValidator<NotRootRole, String> {
    @Override
    public void initialize(final NotRootRole constraintAnnotation) {

    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return !value.equals("pharaoh");
    }
}