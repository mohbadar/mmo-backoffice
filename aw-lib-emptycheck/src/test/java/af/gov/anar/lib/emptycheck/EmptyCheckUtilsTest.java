package af.gov.anar.lib.emptycheck;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class EmptyCheckUtilsTest {

	@BeforeClass
	public static void setup() {

	}

	@Test
	public void isNullEmptyObjectTest() {
		Date d = null;
		assertTrue(EmptyCheckUtility.isNullEmpty(d));
	}

	@Test
	public void isNullEmptyStringTest() {
		String str = null;
		assertTrue(EmptyCheckUtility.isNullEmpty(""));
		assertTrue(EmptyCheckUtility.isNullEmpty("   "));
		assertTrue(EmptyCheckUtility.isNullEmpty(str));
		assertFalse(EmptyCheckUtility.isNullEmpty(" jfashd kjasdkjf"));
	}

	@Test
	public void isNullEmptyCollectionTest() {
		List<String> strings = null;
		Set<String> stringSet = new HashSet<>();
		List<String> names = new ArrayList<>();
		IntStream.of(10).forEach(i -> names.add("name : " + i));
		assertTrue(EmptyCheckUtility.isNullEmpty(strings));
		assertTrue(EmptyCheckUtility.isNullEmpty(stringSet));
		assertFalse(EmptyCheckUtility.isNullEmpty(names));
	}

	@Test
	public void isNullEmptyMapTest() {
		Map<Integer, String> nameRollMapp = null;
		Map<Integer, String> nameNumberMapp = new HashMap<>();
		Map<Integer, String> nameStateMapp = new HashMap<>();
		IntStream.of(10).forEach(i -> nameStateMapp.put(i, "State code : " + i));

		assertTrue(EmptyCheckUtility.isNullEmpty(nameRollMapp));
		assertTrue(EmptyCheckUtility.isNullEmpty(nameNumberMapp));
		assertFalse(EmptyCheckUtility.isNullEmpty(nameStateMapp));
	}

}
