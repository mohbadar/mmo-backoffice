package af.asr.vault.api.validation;


import af.asr.DateConverter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;

@SuppressWarnings("WeakerAccess")
public class CheckKeyTimestamp implements ConstraintValidator<ValidKeyTimestamp, String> {
    @Override
    public void initialize(ValidKeyTimestamp constraintAnnotation) { }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (value == null)
            return false;
        try {
            final String timeString = value.replace('_', ':');
            DateConverter.fromIsoString(timeString);
            return true;
        }
        catch (final DateTimeException ignored) {
            return false;
        }
    }
}