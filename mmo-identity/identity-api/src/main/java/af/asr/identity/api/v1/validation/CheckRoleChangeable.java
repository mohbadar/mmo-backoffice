package af.asr.identity.api.v1.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@SuppressWarnings("WeakerAccess")
public class CheckRoleChangeable implements ConstraintValidator<ChangeableRole, String> {
    @Override
    public void initialize(final ChangeableRole constraintAnnotation) {

    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return isChangeableRoleIdentifier(value);
    }

    public static boolean isChangeableRoleIdentifier(final String value) {
        return !value.equals("pharaoh") && !value.equals("deactivated");
    }
}