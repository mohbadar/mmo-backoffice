package af.asr.postgresql.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    public LocalDateConverter() {
        super();
    }

    @Override
    public Date convertToDatabaseColumn(final LocalDate attribute) {
        if (attribute == null) {
            return null;
        } else {
            return Date.valueOf(attribute);
        }
    }

    @Override
    public LocalDate convertToEntityAttribute(final Date dbData) {
        if (dbData == null) {
            return null;
        } else {
            return dbData.toLocalDate();
        }
    }
}