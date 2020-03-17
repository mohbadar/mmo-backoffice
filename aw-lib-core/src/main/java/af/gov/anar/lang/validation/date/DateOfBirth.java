
package af.gov.anar.lang.validation.date;

import java.time.LocalDate;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class DateOfBirth {

  private Integer year;
  private Integer month;
  private Integer day;

  public DateOfBirth() {
    super();
  }

  public static DateOfBirth fromLocalDate(final LocalDate localDate) {
    final DateOfBirth dateOfBirth = new DateOfBirth();
    dateOfBirth.setYear(localDate.getYear());
    dateOfBirth.setMonth(localDate.getMonthValue());
    dateOfBirth.setDay(localDate.getDayOfMonth());
    return dateOfBirth;
  }

  public Integer getYear() {
    return this.year;
  }

  public void setYear(final Integer year) {
    this.year = year;
  }

  public Integer getMonth() {
    return this.month;
  }

  public void setMonth(final Integer month) {
    this.month = month;
  }

  public Integer getDay() {
    return this.day;
  }

  public void setDay(final Integer day) {
    this.day = day;
  }

  public LocalDate toLocalDate() {
    return LocalDate.of(
        this.year,
        this.month != null ? this.month : 1,
        this.day != null ? this.day : 1
    );
  }
}
