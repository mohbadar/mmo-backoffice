package af.asr.validation;


import af.asr.DateConverter;

import java.time.DateTimeException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;

public class CheckLocalDateTimeString implements ConstraintValidator<ValidLocalDateTimeString, String> {
    public void initialize(ValidLocalDateTimeString constraint) {
    }

    public boolean isValid(final String obj, ConstraintValidatorContext context) {
        if (obj == null)
            return true;
        try {
            DateConverter.fromIsoString(obj);
            return true;
        }
        catch (final DateTimeException e) {
            return false;
        }
    }
}