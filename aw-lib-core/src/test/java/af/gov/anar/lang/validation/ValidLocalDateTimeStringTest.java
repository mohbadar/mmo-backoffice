
package af.gov.anar.lang.validation;

import af.gov.anar.lang.validation.constraints.ValidLocalDateTimeString;
import af.gov.anar.lang.validation.date.DateConverter;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static af.gov.anar.lang.validation.TestHelper.isValid;


public class ValidLocalDateTimeStringTest {

  @Test
  public void now()
  {
    final AnnotatedClassNullable annotatedInstance = new AnnotatedClassNullable(DateConverter.toIsoString(LocalDateTime.now()));
    Assert.assertTrue(isValid(annotatedInstance));
  }

  @Test
  public void invalidString()
  {
    final AnnotatedClassNullable annotatedInstance = new AnnotatedClassNullable("This is not a date time.");
    Assert.assertFalse(isValid(annotatedInstance));
  }

  @Test
  public void nullLocalDateTimeStringAllowed()
  {
    final AnnotatedClassNullable annotatedInstance = new AnnotatedClassNullable(null);
    Assert.assertTrue(isValid(annotatedInstance));
  }

  @Test
  public void nullLocalDateTimeStringNotAllowed()
  {
    final AnnotatedClassNotNull annotatedInstance = new AnnotatedClassNotNull(null);
    Assert.assertFalse(isValid(annotatedInstance));
  }

  private static class AnnotatedClassNullable {
    @Nullable
    @ValidLocalDateTimeString()
    String localDateTimeString;

    AnnotatedClassNullable(final String localDateTimeString) {
      this.localDateTimeString = localDateTimeString;
    }
  }

  private static class AnnotatedClassNotNull {
    @NotNull
    @ValidLocalDateTimeString()
    String localDateTimeString;

    AnnotatedClassNotNull(final String localDateTimeString) {
      this.localDateTimeString = localDateTimeString;
    }
  }
}
