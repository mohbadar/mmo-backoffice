package af.asr.cassandra.util;

import af.asr.DateConverter;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ParseUtils;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;

public class LocalDateTimeCodec extends TypeCodec<LocalDateTime> {

    public LocalDateTimeCodec() {
        super(DataType.timestamp(), LocalDateTime.class);
    }

    @Override
    public ByteBuffer serialize(final LocalDateTime value, final ProtocolVersion protocolVersion) throws InvalidTypeException {
        final Long epochMillis = DateConverter.toEpochMillis(value);
        return TypeCodec.bigint().serializeNoBoxing(epochMillis, protocolVersion);
    }

    @Override
    public LocalDateTime deserialize(final ByteBuffer bytes, final ProtocolVersion protocolVersion) throws InvalidTypeException {
        final Long epochMillis = TypeCodec.bigint().deserializeNoBoxing(bytes, protocolVersion);
        return DateConverter.fromEpochMillis(epochMillis);
    }

    @Override
    public LocalDateTime parse(final String value) throws InvalidTypeException {
        final String toParse;
        if (ParseUtils.isQuoted(value)) {
            toParse = ParseUtils.unquote(value);
        } else {
            toParse = value;
        }

        if (ParseUtils.isLongLiteral(toParse)) {
            return DateConverter.fromEpochMillis(Long.parseLong(toParse));
        } else {
            return DateConverter.fromIsoString(toParse);
        }
    }

    @Override
    public String format(final LocalDateTime value) throws InvalidTypeException {
        return ParseUtils.quote(DateConverter.toIsoString(value));
    }
}