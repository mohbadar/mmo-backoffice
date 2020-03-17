
package af.gov.anar.async.util;

import af.gov.anar.api.util.UserContext;
import af.gov.anar.api.util.UserContextHolder;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class UserContextHolderTest {
  @Test
  public void testUserIdentifierCropping()
  {
    final String userIdentifier16 = RandomStringUtils.randomAlphanumeric(16);
    UserContextHolder.setAccessToken(userIdentifier16, "x");

    Assert.assertEquals(UserContextHolder.checkedGetUser(), userIdentifier16);

    final String userIdentifier32 = RandomStringUtils.randomAlphanumeric(32);
    UserContextHolder.setAccessToken(userIdentifier32, "x");

    Assert.assertEquals(UserContextHolder.checkedGetUser(), userIdentifier32);

    final String userIdentifier64 = userIdentifier32 + userIdentifier32;
    UserContextHolder.setAccessToken(userIdentifier64, "x");

    Assert.assertEquals(UserContextHolder.checkedGetUser(), userIdentifier32);
  }

  @Test(expected = IllegalStateException.class)
  public void testUnsetUserIdentifier()
  {
    UserContextHolder.clear();
    UserContextHolder.checkedGetUser();
  }

  @Test(expected = IllegalStateException.class)
  public void testUnsetAccessToken()
  {
    UserContextHolder.clear();
    UserContextHolder.checkedGetAccessToken();
  }

  @Test
  public void testSimpleUnSetAndGet()
  {
    UserContextHolder.clear();
    final Optional<UserContext> userContext = UserContextHolder.getUserContext();
    Assert.assertTrue(!userContext.isPresent());
  }

  @Test
  public void testSimpleSetAndGet()
  {
    final UserContext setUserContext = new UserContext("x", "y");
    UserContextHolder.clear();
    UserContextHolder.setUserContext(setUserContext);
    UserContextHolder.getUserContext().ifPresent(x -> Assert.assertEquals(setUserContext, x));
  }
}
