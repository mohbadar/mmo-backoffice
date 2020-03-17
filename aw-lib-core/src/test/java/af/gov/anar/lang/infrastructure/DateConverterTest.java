
package af.gov.anar.lang.infrastructure;

import af.gov.anar.lang.validation.date.DateConverter;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class DateConverterTest {
  private final static Instant momentInTime = Instant.ofEpochSecond(1481281507);

  @Test
  public void dateToIsoString() throws Exception {
    final Date date = Date.from(momentInTime);
    final String isoString = DateConverter.toIsoString(date);
    Assert.assertEquals("2016-12-09T11:05:07Z", isoString);
  }

  @Test
  public void dateFromIsoString() throws Exception {
    final LocalDate date = DateConverter.dateFromIsoString("2017-07-13Z");
    final String isoString = DateConverter.toIsoString(date);
    Assert.assertEquals("2017-07-13Z", isoString);
  }

  @Test
  public void localDateTimeToIsoString() throws Exception {
    final LocalDateTime localDateTime = LocalDateTime.ofInstant(momentInTime, ZoneId.of("UTC"));
    final String isoString = DateConverter.toIsoString(localDateTime);
    Assert.assertEquals("2016-12-09T11:05:07Z", isoString);

    final LocalDateTime roundTrip = DateConverter.fromIsoString(isoString);
    Assert.assertEquals(localDateTime, roundTrip);
  }

  @Test
  public void localDateTimeToEpochMillis() throws Exception {
    final LocalDateTime localDateTime = LocalDateTime.ofInstant(momentInTime, ZoneId.of("UTC"));
    final Long epochMillis = DateConverter.toEpochMillis(localDateTime);
    Assert.assertEquals(Long.valueOf(1481281507000L), epochMillis);

    final LocalDateTime roundTrip = DateConverter.fromEpochMillis(epochMillis);
    Assert.assertEquals(localDateTime, roundTrip);
  }

  @Test
  public void localDateToIsoString() throws Exception {
    final LocalDateTime localDateTime = LocalDateTime.ofInstant(momentInTime, ZoneId.of("UTC"));
    final LocalDate localDate = DateConverter.toLocalDate(localDateTime);
    final String isoString = DateConverter.toIsoString(localDate);
    Assert.assertEquals("2016-12-09Z", isoString);
  }

  @Test
  public void localDateToEpochDay() throws Exception {
    final LocalDateTime localDateTime = LocalDateTime.ofInstant(momentInTime, ZoneId.of("UTC"));
    final LocalDate localDate = DateConverter.toLocalDate(localDateTime);
    final Long epochDay = DateConverter.toEpochDay(localDate);
    Assert.assertEquals(Long.valueOf(17144), epochDay);
  }
}