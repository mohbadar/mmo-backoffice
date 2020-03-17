package af.gov.anar.lib.calendar;

import af.gov.anar.lang.infrastructure.exception.common.ArithmeticException;
import af.gov.anar.lang.infrastructure.exception.common.IllegalArgumentException;
import af.gov.anar.lang.infrastructure.exception.common.NullPointerException;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalendarUtilityTest {

	private static DateFormat dateTimeParser = null;

	@Before
	public void setUp() throws Exception {
		dateTimeParser = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void getCeilingTestCheckException() throws ParseException, IllegalArgumentException, ArithmeticException {
		CalendarUtility.getCeiling(null, Calendar.HOUR);
	}

	@Test(expected = java.lang.ArithmeticException.class)
	public void getCeilingTestCheckException2() throws ParseException, ArithmeticException, IllegalArgumentException {

		Date d = dateTimeParser.parse("March 28, 280000001 13:52:10.099");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		CalendarUtility.getCeiling(c, Calendar.YEAR);
	}

	@Test(expected = java.lang.ArithmeticException.class)
	public void getRoundCheckException2() throws ParseException, ArithmeticException, IllegalArgumentException {

		Date d = dateTimeParser.parse("March 28, 280000001 13:52:10.099");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		CalendarUtility.getRound(c, Calendar.YEAR);
	}

	@Test(expected = java.lang.ArithmeticException.class)
	public void truncteCheckException() throws ParseException, ArithmeticException, IllegalArgumentException {

		Date d = dateTimeParser.parse("March 28, 280000001 13:52:10.099");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		CalendarUtility.truncate(c, Calendar.YEAR);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void getFragmentInDaysTestCheckException()
			throws ParseException, IllegalArgumentException, ArithmeticException {
		CalendarUtility.getFragmentInDays(null, Calendar.HOUR);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void getFragmentInHoursTestCheckException()
			throws ParseException, IllegalArgumentException, ArithmeticException {
		CalendarUtility.getFragmentInHours(null, Calendar.HOUR);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void getFragmentInMilliSecondsTestCheckException()
			throws ParseException, IllegalArgumentException, ArithmeticException {
		CalendarUtility.getFragmentInMilliseconds(null, Calendar.HOUR);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void getFragmentInMinutesTestCheckException()
			throws ParseException, IllegalArgumentException, ArithmeticException {
		CalendarUtility.getFragmentInMinutes(null, Calendar.HOUR);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void getFragmentInSecondsTestCheckException()
			throws ParseException, IllegalArgumentException, ArithmeticException {
		CalendarUtility.getFragmentInSeconds(null, Calendar.HOUR);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void isSameDayTestCheckException() throws ParseException, IllegalArgumentException, ArithmeticException {
		CalendarUtility.isSameDay(null, null);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void isSameInstanceTestCheckException()
			throws ParseException, IllegalArgumentException, ArithmeticException {
		CalendarUtility.isSameInstant(null, null);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void isSameLocalTimeTestCheckException()
			throws ParseException, IllegalArgumentException, ArithmeticException {
		CalendarUtility.isSameLocalTime(null, null);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void getRoundTestCheckException() throws ParseException, IllegalArgumentException, ArithmeticException {
		CalendarUtility.getRound(null, Calendar.MONTH);
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void toCalendarTestCheckException() throws NullPointerException {
		CalendarUtility.toCalendar(null);
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void toCalendar2TestCheckException() throws NullPointerException {
		CalendarUtility.toCalendar(null, null);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void truncateTestCheckException()
			throws NullPointerException, IllegalArgumentException, ArithmeticException {
		CalendarUtility.truncate(null, Calendar.MONTH);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void truncatedEqualsTestCheckException()
			throws NullPointerException, IllegalArgumentException, ArithmeticException {
		CalendarUtility.truncatedEquals(null, null, Calendar.MONTH);
	}

	@Test
	public void getCeilingTest() throws ParseException, IllegalArgumentException, ArithmeticException {
		Date d = dateTimeParser.parse("March 28, 2002 13:52:10.099");
		Date d2 = dateTimeParser.parse("March 28, 2002 14:00:00.000");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d2);
		assertThat(CalendarUtility.getCeiling(c, Calendar.HOUR), is(c1));
	}

	@Test()
	public void getFragmentInDaysTest() throws ParseException, IllegalArgumentException {
		Date d = dateTimeParser.parse("January 28, 2008 13:52:10.099");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		assertThat(CalendarUtility.getFragmentInDays(c, Calendar.MONTH), is(28L));
	}

	@Test
	public void getFragmentInHoursTest() throws ParseException, IllegalArgumentException {
		Date d = dateTimeParser.parse("January 1, 2008 07:15:10.538");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		assertThat(CalendarUtility.getFragmentInHours(c, Calendar.MONTH), is(7L));
	}

	@Test
	public void getFragmentInMillisecondsTest() throws ParseException, IllegalArgumentException {
		Date d = dateTimeParser.parse("January 1, 2008 07:15:10.538");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		assertThat(CalendarUtility.getFragmentInMilliseconds(c, Calendar.SECOND), is(538L));
	}

	@Test
	public void getFragmentInMinutesTest() throws ParseException, IllegalArgumentException {
		Date d = dateTimeParser.parse("January 1, 2008 07:15:10.538");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		assertThat(CalendarUtility.getFragmentInMinutes(c, Calendar.HOUR_OF_DAY), is(15L));
	}

	@Test
	public void getFragmentInSecondsTest() throws ParseException, IllegalArgumentException {
		Date d = dateTimeParser.parse("January 1, 2008 07:15:10.538");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		assertThat(CalendarUtility.getFragmentInSeconds(c, Calendar.MINUTE), is(10L));
	}

	@Test
	public void isSameDayTest() throws ParseException, IllegalArgumentException {
		Date d = dateTimeParser.parse("January 1, 2008 07:15:10.538");
		Calendar c = Calendar.getInstance();
		Date d2 = dateTimeParser.parse("January 1, 2008 09:15:10.538");
		Calendar c2 = Calendar.getInstance();
		c.setTime(d);
		c2.setTime(d2);
		assertThat(CalendarUtility.isSameDay(c, c2), is(true));
	}

	@Test
	public void isSameInstantTest() throws ParseException, IllegalArgumentException {
		Date d = dateTimeParser.parse("January 1, 2008 07:15:10.751");
		Calendar c = Calendar.getInstance();
		Date d2 = dateTimeParser.parse("January 1, 2008 07:15:10.751");
		Calendar c2 = Calendar.getInstance();
		c.setTime(d);
		c2.setTime(d2);
		assertThat(CalendarUtility.isSameInstant(c, c2), is(true));
	}

	@Test
	public void isSameLocalTimeTest() throws ParseException, IllegalArgumentException {
		Date d = dateTimeParser.parse("January 1, 2008 07:15:10.751");
		Calendar c = Calendar.getInstance();
		Date d2 = dateTimeParser.parse("January 1, 2008 07:15:10.751");
		Calendar c2 = Calendar.getInstance();
		c.setTime(d);
		c2.setTime(d2);
		assertThat(CalendarUtility.isSameLocalTime(c, c2), is(true));
	}

	@Test
	public void getRoundTest() throws ParseException, IllegalArgumentException, ArithmeticException {
		Date d = dateTimeParser.parse("March 28, 2002 13:45:01.231");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		Date d2 = dateTimeParser.parse("March 28, 2002 14:00:00.000");
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		assertThat(CalendarUtility.getRound(c, Calendar.HOUR), is(c2));
	}

	@Test
	public void toCalendarTest() throws ParseException, NullPointerException {
		Date d = dateTimeParser.parse("March 28, 2002 13:45:01.231");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		assertThat(CalendarUtility.toCalendar(d), is(c));
	}

	@Test
	public void toCalendarWithTimeZoneTest() throws ParseException, NullPointerException {
		Date d = dateTimeParser.parse("March 28, 2002 13:45:01.231");
		TimeZone timeZone = TimeZone.getDefault();
		Calendar c = Calendar.getInstance(timeZone);
		c.setTime(d);
		assertThat(CalendarUtility.toCalendar(d, timeZone), is(c));
	}

	@Test
	public void truncateTest() throws ParseException, IllegalArgumentException, ArithmeticException {
		Date d = dateTimeParser.parse("March 28, 2002 13:45:01.231");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		Date d2 = dateTimeParser.parse("March 28, 2002 13:00:00.000");
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		assertThat(CalendarUtility.truncate(c, Calendar.HOUR), is(c2));
	}

	@Test
	public void truncatedEqualsTest() throws ParseException, IllegalArgumentException {
		Date d = dateTimeParser.parse("March 28, 2002 13:45:01.231");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		Date d2 = dateTimeParser.parse("March 28, 2002 13:00:00.000");
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		assertThat(CalendarUtility.truncatedEquals(c, c2, Calendar.HOUR), is(true));
	}
}
