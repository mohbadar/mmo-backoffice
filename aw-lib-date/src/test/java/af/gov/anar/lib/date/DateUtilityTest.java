package af.gov.anar.lib.date;

import af.gov.anar.lang.infrastructure.exception.common.IllegalArgumentException;
import af.gov.anar.lang.infrastructure.exception.common.NullPointerException;
import af.gov.anar.lang.infrastructure.exception.common.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public final class DateUtilityTest {

	private static Date TEST_DATE;

	private static Calendar TEST_CALANDER;

	private static String TEST_CALANDER_STRING;

	private static Date currDate;

	private static Calendar calendar;

	private static LocalDateTime currLocalDateTime;

	@BeforeClass
	public static void setup() {
		final GregorianCalendar cal = new GregorianCalendar(2018, 6, 5, 4, 3, 2);
		cal.set(Calendar.MILLISECOND, 1);
		TEST_DATE = cal.getTime();

		cal.setTimeZone(TimeZone.getDefault());

		TEST_CALANDER = cal;

		StringBuilder builder = new StringBuilder();
		builder.append(cal.get(Calendar.YEAR));
		builder.append(cal.get(Calendar.MONTH) + 1);
		builder.append(cal.get(Calendar.DAY_OF_MONTH));
		builder.append(cal.get(Calendar.HOUR_OF_DAY));
		TEST_CALANDER_STRING = builder.toString();
		currDate = new Date();
		currLocalDateTime = LocalDateTime.now();

	}

	@Test
	public void testAddDays() throws Exception {
		Date result = DateUtility.addDays(TEST_DATE, 0);

		assertNotSame(TEST_DATE, result);
		assertDate(TEST_DATE, 2018, 6, 5, 4, 3, 2, 1);
		assertDate(result, 2018, 6, 5, 4, 3, 2, 1);

		result = DateUtility.addDays(TEST_DATE, 1);
		assertNotSame(TEST_DATE, result);
		assertDate(TEST_DATE, 2018, 6, 5, 4, 3, 2, 1);
		assertDate(result, 2018, 6, 6, 4, 3, 2, 1);

		result = DateUtility.addDays(TEST_DATE, -1);
		assertNotSame(TEST_DATE, result);
		assertDate(TEST_DATE, 2018, 6, 5, 4, 3, 2, 1);
		assertDate(result, 2018, 6, 4, 4, 3, 2, 1);

	}

	@Test
	public void testAddHours() throws Exception {
		Date result = DateUtility.addHours(TEST_DATE, 0);

		assertNotSame(TEST_DATE, result);
		assertDate(TEST_DATE, 2018, 6, 5, 4, 3, 2, 1);
		assertDate(result, 2018, 6, 5, 4, 3, 2, 1);

		result = DateUtility.addHours(TEST_DATE, 1);
		assertNotSame(TEST_DATE, result);
		assertDate(TEST_DATE, 2018, 6, 5, 4, 3, 2, 1);
		assertDate(result, 2018, 6, 5, 5, 3, 2, 1);

		result = DateUtility.addHours(TEST_DATE, -1);
		assertNotSame(TEST_DATE, result);
		assertDate(TEST_DATE, 2018, 6, 5, 4, 3, 2, 1);
		assertDate(result, 2018, 6, 5, 3, 3, 2, 1);

	}

	@Test
	public void testAddMinutes() throws Exception {
		Date result = DateUtility.addMinutes(TEST_DATE, 0);

		assertNotSame(TEST_DATE, result);
		assertDate(TEST_DATE, 2018, 6, 5, 4, 3, 2, 1);
		assertDate(result, 2018, 6, 5, 4, 3, 2, 1);

		result = DateUtility.addMinutes(TEST_DATE, 1);
		assertNotSame(TEST_DATE, result);
		assertDate(TEST_DATE, 2018, 6, 5, 4, 3, 2, 1);

		assertDate(result, 2018, 6, 5, 4, 4, 2, 1);

		result = DateUtility.addMinutes(TEST_DATE, -1);
		assertNotSame(TEST_DATE, result);
		assertDate(TEST_DATE, 2018, 6, 5, 4, 3, 2, 1);
		assertDate(result, 2018, 6, 5, 4, 2, 2, 1);

	}

	@Test
	public void testAddSeconds() throws Exception {
		Date result = DateUtility.addSeconds(TEST_DATE, 0);
		assertNotSame(TEST_DATE, result);
		assertDate(TEST_DATE, 2018, 6, 5, 4, 3, 2, 1);
		assertDate(result, 2018, 6, 5, 4, 3, 2, 1);

		result = DateUtility.addSeconds(TEST_DATE, 1);
		assertNotSame(TEST_DATE, result);
		assertDate(TEST_DATE, 2018, 6, 5, 4, 3, 2, 1);
		assertDate(result, 2018, 6, 5, 4, 3, 3, 1);

		result = DateUtility.addSeconds(TEST_DATE, -1);
		assertNotSame(TEST_DATE, result);
		assertDate(TEST_DATE, 2018, 6, 5, 4, 3, 2, 1);
		assertDate(result, 2018, 6, 5, 4, 3, 1, 1);
	}

	@Test
	public void testFormatCalender() throws Exception {
		assertEquals(TEST_CALANDER_STRING, DateUtility.formatCalendar(TEST_CALANDER, "yyyyMdH"));
		assertEquals(TEST_CALANDER_STRING, DateUtility.formatCalendar(TEST_CALANDER, "yyyyMdH", Locale.US));
		assertEquals(TEST_CALANDER_STRING, DateUtility.formatCalendar(TEST_CALANDER, "yyyyMdH", TimeZone.getDefault()));
		assertEquals(TEST_CALANDER_STRING,
				DateUtility.formatCalendar(TEST_CALANDER, "yyyyMdH", TimeZone.getDefault(), Locale.US));
	}

	@Test
	public void testFormatDate() throws Exception {
		assertEquals(TEST_CALANDER_STRING, DateUtility.formatDate(TEST_CALANDER.getTime(), "yyyyMdH"));
		assertEquals(TEST_CALANDER_STRING,
				DateUtility.formatDate(TEST_CALANDER.getTime(), "yyyyMdH", TimeZone.getDefault()));
		assertEquals(TEST_CALANDER_STRING,
				DateUtility.formatDate(TEST_CALANDER.getTime(), "yyyyMdH", TimeZone.getDefault(), Locale.US));
	}

	private void assertDate(final Date date, final int year, final int month, final int day, final int hour,
			final int min, final int sec, final int mil) throws Exception {
		final GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		assertEquals(year, cal.get(Calendar.YEAR));
		assertEquals(month, cal.get(Calendar.MONTH));
		assertEquals(day, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(hour, cal.get(Calendar.HOUR_OF_DAY));
		assertEquals(min, cal.get(Calendar.MINUTE));
		assertEquals(sec, cal.get(Calendar.SECOND));
		assertEquals(mil, cal.get(Calendar.MILLISECOND));
	}

	// --------------------------------- Test for after---------------
	private void loadDate() {
		calendar = Calendar.getInstance();
		calendar.setTime(currDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDateAfter() {

		loadDate();
		calendar.add(Calendar.DATE, 1);

		Date nextDate = calendar.getTime();

		assertTrue(DateUtility.after(nextDate, currDate));

		assertFalse(DateUtility.after(currDate, nextDate));

		assertFalse(DateUtility.after(currDate, currDate));
		DateUtility.after(null, new Date());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testDateAfterException() {
		DateUtility.after(null, LocalDateTime.now());
	}

	@Test
	public void testLocalDateTimeAfter() {

		LocalDateTime nextLocalDateTime = currLocalDateTime.plusDays(1);

		assertTrue(DateUtility.after(nextLocalDateTime, currLocalDateTime));

		assertFalse(DateUtility.after(currLocalDateTime, nextLocalDateTime));

		assertFalse(DateUtility.after(currLocalDateTime, currLocalDateTime));
	}

	// --------------------------------- Test for before-------------------
	@Test(expected = IllegalArgumentException.class)
	public void testDateBefore() {

		loadDate();
		calendar.add(Calendar.DATE, -1);
		Date previousDay = calendar.getTime();

		assertTrue(DateUtility.before(previousDay, currDate));

		assertFalse(DateUtility.before(currDate, previousDay));

		assertFalse(DateUtility.before(currDate, currDate));

		DateUtility.before(null, currDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDateBeforeException() {
		DateUtility.before(null, LocalDateTime.now());
	}

	@Test
	public void testLocalDateTimeBefore() {

		LocalDateTime previousLocalDateTime = currLocalDateTime.minusDays(1);

		assertTrue(DateUtility.before(previousLocalDateTime, currLocalDateTime));

		assertFalse(DateUtility.before(currLocalDateTime, previousLocalDateTime));

		assertFalse(DateUtility.before(currLocalDateTime, currLocalDateTime));
	}

	// --------------------------------- Test for equal----------------------
	@Test(expected = IllegalArgumentException.class)
	public void testIsSameDayWithNextLocalDateTime() {
		LocalDateTime nextLocalDateTime = currLocalDateTime.plusDays(1);

		assertTrue(DateUtility.isSameDay(currLocalDateTime, currLocalDateTime));

		assertFalse(DateUtility.isSameDay(currLocalDateTime, nextLocalDateTime));

		assertFalse(DateUtility.isSameDay(nextLocalDateTime, currLocalDateTime));

		DateUtility.isSameDay(null, currLocalDateTime);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsSameDayException() {
		DateUtility.isSameDay(null, new Date());
	}

	@Test
	public void testIsSameDayWithNextDate() {
		loadDate();
		calendar.add(Calendar.DATE, 1);
		Date nextDate = calendar.getTime();

		assertTrue(DateUtility.isSameDay(currDate, currDate));

		assertFalse(DateUtility.isSameDay(currDate, nextDate));

		assertFalse(DateUtility.isSameDay(nextDate, currDate));
	}

	@Test
	public void testIsSameDayWithDifferentTime() {
		loadDate();
		calendar.add(Calendar.MILLISECOND, 1);
		Date nextDate = calendar.getTime();

		assertTrue(DateUtility.isSameDay(currDate, currDate));

		assertTrue(DateUtility.isSameDay(currDate, nextDate));

		assertTrue(DateUtility.isSameDay(nextDate, currDate));
	}

	// @Test
	public void testIsSameDayWithDifferentLocalDateTime() {

		LocalDateTime nextLocalDateTime = currLocalDateTime.plusHours(1);

		assertTrue(DateUtility.isSameDay(currLocalDateTime, currLocalDateTime));

		assertTrue(DateUtility.isSameDay(currLocalDateTime, nextLocalDateTime));

		assertTrue(DateUtility.isSameDay(nextLocalDateTime, currLocalDateTime));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsSameInstantWithDifferentLocalDateTime() {

		LocalDateTime nextLocalDateTime = currLocalDateTime.plusHours(1);

		assertTrue(DateUtility.isSameInstant(currLocalDateTime, currLocalDateTime));

		assertFalse(DateUtility.isSameInstant(currLocalDateTime, nextLocalDateTime));

		assertFalse(DateUtility.isSameInstant(nextLocalDateTime, currLocalDateTime));
		DateUtility.isSameInstant(null, currLocalDateTime);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsSameInstantWithDifferentDate() {
		DateUtility.isSameInstant(null, new Date());
	}

	@Test
	public void testIsSameInstantWithDifferentTime() {
		loadDate();
		calendar.add(Calendar.SECOND, 1);
		calendar.add(Calendar.MILLISECOND, 1);
		Date nextDate = calendar.getTime();

		assertTrue(DateUtility.isSameInstant(currDate, currDate));

		assertFalse(DateUtility.isSameInstant(currDate, nextDate));

		assertFalse(DateUtility.isSameInstant(nextDate, currDate));
	}

	// --------------------------------- Test for exception----------------------
	@Test(expected = IllegalArgumentException.class)
	public void testDateAfterExceptionDateNull() {
		DateUtility.after(null, currDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDateBeforeExceptionDateNull() {
		DateUtility.before(currDate, null);
	}

	// -----------------------------Parsing date test----------------------------

	@Test
	public void testGetUTCCurrentDateTime() {
		assertNotNull(DateUtility.getUTCCurrentDateTime());
	}

	@Test
	public void testParseUTCToDefaultLocalDateTime() {
		assertNotNull(DateUtility.convertUTCToLocalDateTime(DateUtility.getCurrentDateTimeString()));
	}

	@Test
	public void testParseUTCToLocalDateTime() {
		LocalDateTime exp = LocalDateTime.parse("2018/11/20 20:02:39",
				DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		ZonedDateTime z1 = exp.atZone(ZoneId.of(TimeZone.getDefault().getID()));
		LocalDateTime utcDateTime = LocalDateTime.ofInstant(z1.toInstant(), ZoneOffset.UTC);
		LocalDateTime act = DateUtility.parseUTCToLocalDateTime(utcDateTime.toString(), "yyyy-MM-dd'T'HH:mm:ss");
		compareTwoLocalDateTime(exp, act);
	}

	@Test
	public void testParseToDate() throws java.text.ParseException {
		assertNotNull(DateUtility.parseToDate("2018/11/20 20:02:39", "yyyy/MM/dd HH:mm:ss", TimeZone.getDefault()));
	}

	private void compareTwoLocalDateTime(LocalDateTime exp, LocalDateTime act) {
		assertTrue(exp.getDayOfMonth() == act.getDayOfMonth());
		assertTrue(exp.getMonth() == act.getMonth());
		assertTrue(exp.getYear() == act.getYear());
		assertTrue(exp.getHour() == act.getHour());
		assertTrue(exp.getMinute() == act.getMinute());
		assertTrue(exp.getSecond() == act.getSecond());
	}

	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	@Test(expected = NullPointerException.class)
	public void testParseToDateExceptionNullDateString() throws java.text.ParseException {
		DateUtility.parseToDate(null, "dd-MM-yyyy");
	}

	@Test(expected = NullPointerException.class)
	public void testParseToDateExceptionNullPatternString() throws java.text.ParseException {
		DateUtility.parseToDate("2019-01-01", null);
	}

	@Test(expected = ParseException.class)
	public void testParseToDateParseException() throws java.text.ParseException {
		DateUtility.parseToDate("2019-01-01", "dd.MM.yyyy");
	}

	@Test
	public void testParseUtcToDate() throws java.text.ParseException {
		assertNotNull(DateUtility.parseToDate("2018/11/20 20:02:39", "yyyy/MM/dd HH:mm:ss", TimeZone.getTimeZone("UTC")));
	}

	@Test
	public void testGetUTCCurrentDateTimeString() {
		assertNotNull(DateUtility.getUTCCurrentDateTimeString());
	}

	@Test
	public void testFormatUTCCurrentDateTimeString() {
		assertNotNull(DateUtility.getUTCCurrentDateTimeString("yyyy/MM/dd HH:mm:ss"));
	}

	@Test(expected = DateTimeParseException.class)
	public void testParseUTCToDefaultLocalDateTimeException() {
		DateUtility.convertUTCToLocalDateTime("22-01-2108");
	}

	@Test(expected = ParseException.class)
	public void testParseUTCToLocalDateTimeException() {
		DateUtility.parseUTCToLocalDateTime("22-01-2108", "yyyy-MM-dd'T'HH:mm:ss.SSS");
	}

	// New test case added
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void addDaysIllegalArgumentException() {
		DateUtility.addDays(null, 2);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void addHoursIllegalArgumentException() {
		DateUtility.addHours(null, 2);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void addMinutesIllegalArgumentException() {
		DateUtility.addMinutes(null, 2);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void addSecondsIllegalArgumentException() {
		DateUtility.addSeconds(null, 2);
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void formatDateIllegalArgumentException() {
		DateUtility.formatDate(new Date(), null);
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void formatDateWithTimeZoneIllegalArgumentException() {
		DateUtility.formatDate(new Date(), null, null);
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void formatDateWithTimeZoneLocaleIllegalArgumentException() {
		DateUtility.formatDate(new Date(), null, null, null);
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void formatCalendarIllegalArgumentException() {
		DateUtility.formatCalendar(Calendar.getInstance(), null);
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void formatCalendarZoneIllegalArgumentException() {
		DateUtility.formatCalendar(Calendar.getInstance(), null, TimeZone.getDefault());
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void formatCalendarLocaleIllegalArgumentException() {
		DateUtility.formatCalendar(Calendar.getInstance(), null, Locale.getDefault());
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void formatCalendarZoneLocalIllegalArgumentException() {
		DateUtility.formatCalendar(Calendar.getInstance(), null, TimeZone.getDefault(), null);
	}

	@Test
	public void parseToLocalDateTime() {
		DateUtility.parseToLocalDateTime(LocalDateTime.now().toString());
	}

	@Test
	public void formatToISOString() {
		DateUtility.formatToISOString(LocalDateTime.now());
	}

	@Test(expected = ParseException.class)
	public void parseUTCToDate() {
		DateUtility.parseUTCToDate(LocalDateTime.now().toString(), "dd.MM.yyyy");
	}

	@Test(expected = ParseException.class)
	public void parseUTCToDateStirng() {
		DateUtility.parseUTCToDate("2019.01.01");
	}

	@Test(expected = ParseException.class)
	public void parseToDate() {
		DateUtility.parseToDate(LocalDateTime.now().toString(), "dd.MM.yyyy", TimeZone.getDefault());
	}

	@Test
	public void getUTCTimeFromDateTest() {
		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		dateFormatter.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));
		String expectedDate = dateFormatter.format(date);
		String actualDate = DateUtility.getUTCTimeFromDate(date);
		assertTrue(expectedDate.equals(actualDate));
	}
}
