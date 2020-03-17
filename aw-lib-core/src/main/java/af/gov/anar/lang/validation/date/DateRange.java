
package af.gov.anar.lang.validation.date;

import af.gov.anar.lang.infrastructure.exception.service.ServiceException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

/**
 * A range of dates specified by a start and an end.  Inclusive of both.
 */
@SuppressWarnings("WeakerAccess")
public class DateRange {
  private final @Nonnull
  LocalDate start;
  private final @Nonnull
  LocalDate end;

  public DateRange(final @Nonnull LocalDate start, final @Nonnull LocalDate end) {
    this.start = start;
    this.end = end;
  }

  public Stream<LocalDate> stream() {
    return Stream.iterate(start, (current) -> current.plusDays(1))
        .limit(ChronoUnit.DAYS.between(start, end) + 1);
  }

  public LocalDateTime getStartDateTime() {
    return start.atStartOfDay();
  }

  public LocalDateTime getEndDateTime() {
    return end.plusDays(1).atStartOfDay();
  }

  @Override
  public String toString() {
    return DateConverter.toIsoString(start) + ".." + DateConverter.toIsoString(end);
  }

  @Nonnull
  public static DateRange fromIsoString(@Nullable final String isoDateRangeString) {
    if (isoDateRangeString == null) {
      final LocalDate today = LocalDate.now(Clock.systemUTC());
      return new DateRange(today, today);
    } else {
      final String[] dates = isoDateRangeString.split("\\.\\.");
      if (dates.length != 2)
        throw ServiceException.badRequest("Date range should consist of exactly two dates.",
            isoDateRangeString);

      try {
        return new DateRange(DateConverter.dateFromIsoString(dates[0]), DateConverter.dateFromIsoString(dates[1]));
      }
      catch(final DateTimeParseException e){
        throw ServiceException.badRequest("Date {0} must use ISO format",
            isoDateRangeString);
      }
    }
  }
}
