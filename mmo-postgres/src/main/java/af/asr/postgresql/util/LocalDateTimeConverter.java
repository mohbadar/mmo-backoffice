package af.asr.postgresql.util;


import af.asr.DateConverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    public LocalDateTimeConverter() {
        super();
    }

    @Override
    public Timestamp convertToDatabaseColumn(final LocalDateTime attribute) {
        if (attribute == null) {
            return null;
        } else {
            return new Timestamp(DateConverter.toEpochMillis(attribute));
        }
    }

    @Override
    public LocalDateTime convertToEntityAttribute(final Timestamp dbData) {
        if (dbData == null) {
            return null;
        } else {
            return DateConverter.fromEpochMillis(dbData.getTime());
        }
    }
}