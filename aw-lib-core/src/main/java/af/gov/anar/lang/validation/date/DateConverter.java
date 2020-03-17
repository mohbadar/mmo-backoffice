
package af.gov.anar.lang.validation.date;

import javax.annotation.Nonnull;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public interface DateConverter {
  @Nonnull
  static Long toEpochMillis(@Nonnull final LocalDateTime localDateTime) {
    return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
  }

  @Nonnull
  static Long toEpochDay(@Nonnull final LocalDate localDate) {
    return localDate.toEpochDay();
  }

  @Nonnull
  static LocalDateTime fromEpochMillis(@Nonnull final Long epochMillis) {
    return LocalDateTime.from(Instant.ofEpochMilli(epochMillis).atZone(ZoneOffset.UTC));
  }

  @Nonnull
  static String toIsoString(@Nonnull final Date date) {
    return toIsoString(LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC")));
  }

  @Nonnull
  static String toIsoString(@Nonnull final LocalDateTime localDateTime) {
    return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME) + "Z";
  }

  @Nonnull
  static LocalDateTime fromIsoString(@Nonnull final String isoDateTimeString) {
    return LocalDateTime.from(Instant.parse(isoDateTimeString).atZone(ZoneOffset.UTC));
  }

  @Nonnull
  static LocalDate dateFromIsoString(@Nonnull final String isoDateString) {
    final int zIndex = isoDateString.indexOf("Z");
    final String shortenedString = isoDateString.substring(0, zIndex);
    return LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(shortenedString));
  }

  @Nonnull
  static String toIsoString(@Nonnull final LocalDate localDate) {
    return localDate.format(DateTimeFormatter.ISO_DATE) + "Z";
  }

  @Nonnull
  static LocalDate toLocalDate(@Nonnull final LocalDateTime localDateTime) {
    return localDateTime.toLocalDate();
  }
}
