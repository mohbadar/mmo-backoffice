
package af.gov.anar.lang.infrastructure;

import af.gov.anar.lang.infrastructure.exception.service.ServiceException;
import af.gov.anar.lang.validation.date.DateConverter;
import af.gov.anar.lang.validation.date.DateRange;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateRangeTest {

  @Test
  public void dateRangeFromIsoString() {
    final String startString = "2017-07-13Z";
    final String endString = "2017-07-16Z";
    final LocalDate start = DateConverter.dateFromIsoString(startString);
    final LocalDate end = DateConverter.dateFromIsoString(endString);
    final String dateRangeString = startString + ".." + endString;

    final DateRange dateRange = DateRange.fromIsoString(dateRangeString);
    Assert.assertEquals(dateRangeString, dateRange.toString());
    Assert.assertEquals(start.atStartOfDay(), dateRange.getStartDateTime());
    Assert.assertEquals(end.plusDays(1).atStartOfDay(), dateRange.getEndDateTime());
    Assert.assertEquals(4, dateRange.stream().count());

    final DateRange dateRangeToday = DateRange.fromIsoString(null);
    Assert.assertEquals(1, dateRangeToday.stream().count());

    try {
      final DateRange dateRangeHalf = DateRange.fromIsoString(startString);
      Assert.fail("Invalid date range format should throw exception.");
    }
    catch (final ServiceException ignore) {

    }

    try {
      final DateRange dateRangeHalf = DateRange.fromIsoString("notanisostringZ..anothernonisostringZ");
      Assert.fail("Invalid date range format should throw exception.");
    }
    catch (final ServiceException ignore) {

    }
  }
}
