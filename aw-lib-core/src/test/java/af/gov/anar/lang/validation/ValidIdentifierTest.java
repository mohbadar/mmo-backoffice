
package af.gov.anar.lang.validation;

import af.gov.anar.lang.validation.constraints.ValidIdentifier;
import org.junit.Assert;
import org.junit.Test;

import static af.gov.anar.lang.validation.TestHelper.isValid;


public class ValidIdentifierTest {
  @Test
  public void validIdentifierWithTilda()
  {
    final AnnotatedClass annotatedInstance = new AnnotatedClass("xy~mn");
    Assert.assertFalse(isValid(annotatedInstance));
  }

  @Test
  public void validIdentifierWithUnderscore()
  {
    final AnnotatedClass annotatedInstance = new AnnotatedClass("xy_mn");
    Assert.assertTrue(isValid(annotatedInstance));
  }

  @Test
  public void validIdentifierWithDot()
  {
    final AnnotatedClass annotatedInstance = new AnnotatedClass("xy.mn");
    Assert.assertTrue(isValid(annotatedInstance));
  }

  @Test
  public void validIdentifierWithDash()
  {
    final AnnotatedClass annotatedInstance = new AnnotatedClass("xy-mn");
    Assert.assertTrue(isValid(annotatedInstance));
  }

  @Test
  public void validIdentifier()
  {
    final AnnotatedClass annotatedInstance = new AnnotatedClass("xxxx");
    Assert.assertTrue(isValid(annotatedInstance));
  }

  @Test
  public void nullIdentifier()
  {
    final AnnotatedClass annotatedInstance = new AnnotatedClass(null);
    Assert.assertFalse(isValid(annotatedInstance));
  }

  @Test
  public void emptyStringIdentifier()
  {
    final AnnotatedClass annotatedInstance = new AnnotatedClass("");
    Assert.assertFalse(isValid(annotatedInstance));
  }

  @Test
  public void tooShortStringIdentifier()
  {
    final AnnotatedClass annotatedInstance = new AnnotatedClass("x");
    Assert.assertFalse(isValid(annotatedInstance));
  }

  @Test
  public void tooLongStringIdentifier()
  {
    final AnnotatedClass annotatedInstance = new AnnotatedClass("012345");
    Assert.assertFalse(isValid(annotatedInstance));
  }

  @Test
  public void notEncodableStringIdentifier()
  {
    final AnnotatedClass annotatedInstance = new AnnotatedClass("x/y/z");
    Assert.assertFalse(isValid(annotatedInstance));
  }

  private static class AnnotatedClass {
    @ValidIdentifier(maxLength = 5)
    String applicationName;

    AnnotatedClass(final String applicationName) {
      this.applicationName = applicationName;
    }
  }
}
