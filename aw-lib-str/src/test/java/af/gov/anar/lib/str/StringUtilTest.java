package af.gov.anar.lib.str;

import af.gov.anar.lib.str.exception.PatternSyntaxException;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StringUtilTest {
	String testString = "test";
	String testMixString = "t@#ef1s%t";
	String[] testArray = { "test", "abc" };
	String[] testMixArray = { "t", "@#", "ef", "1", "s", "%", "t" };
	String joinedTestArr = "1;2;3";

	@Test
	public void capitalizeFirstLetterTest() {
		assertThat(StringUtility.capitalizeFirstLetter(testString), is("Test"));
	}

	@Test
	public void compareTest() {
		assertThat(StringUtility.compare("test", testString), is(0));
	}

	@Test
	public void compareTestWithBoolean() {
		assertThat(StringUtility.compare("test", testString, true), is(0));

	}

	@Test
	public void compareIgnoreCaseTest() {
		assertThat(StringUtility.compareIgnoreCase("TeSt", testString), is(0));
	}

	@Test
	public void compareIgnoreCaseTestWithBoolean() {
		assertThat(StringUtility.compareIgnoreCase("tEsT", testString, true), is(0));

	}

	@Test
	public void containsTest() {

		assertThat(StringUtility.contains("abctestabc", testString), is(true));

	}

	@Test
	public void containsTestWithIntSearch() {
		assertThat(StringUtility.contains(testString, 101), is(true));
	}

	@Test
	public void containsIgnoreCaseTest() {
		assertThat(StringUtility.containsIgnoreCase(testString, "es"), is(true));
	}

	@Test
	public void containsWhiteSpaceTest() {
		assertThat(StringUtility.containsWhitespace(testString), is(false));
	}

	@Test
	public void isEmptyTest() {
		assertThat(StringUtility.isEmpty(testString), is(false));
	}

	@Test
	public void isNotEmptyTest() {
		assertThat(StringUtility.isNotEmpty(testString), is(true));
	}

	@Test
	public void isBlankTest() {
		assertThat(StringUtility.isBlank(""), is(true));
	}

	@Test
	public void isNotBlankTest() {
		assertThat(StringUtility.isNotBlank(testString), is(true));
	}

	@Test
	public void trimTest() {
		assertThat(StringUtility.trim("    test   "), is(testString));
	}

	@Test
	public void truncateTest() {
		assertThat(StringUtility.truncate("test1234   ", 4), is(testString));
	}

	@Test
	public void truncateTestWithOffset() {
		assertThat(StringUtility.truncate("abctest124 ", 3, 4), is(testString));
	}

	@Test
	public void stripTest() {
		assertThat(StringUtility.strip("    test    "), is(testString));
	}

	@Test
	public void stripTestWithChar() {
		assertThat(StringUtility.strip("abtest", "ab"), is(testString));
	}

	@Test
	public void equalsTest() {
		assertThat(StringUtility.equals("test", testString), is(true));
	}

	@Test
	public void equalsIgnoreCaseTest() {
		assertThat(StringUtility.equalsIgnoreCase("Test", testString), is(true));
	}

	@Test
	public void indexOfTest() {
		assertThat(StringUtility.indexOf(testString, 'e'), is(1));
	}

	@Test
	public void indexOfTestWithStartPos() {
		assertThat(StringUtility.indexOf(testString, 't', 1), is(3));
	}

	@Test
	public void indexOfTestWithSearchString() {
		assertThat(StringUtility.indexOf(testString, "es"), is(1));
	}

	@Test
	public void indexOfTestWithSearchStringAndStartPos() {
		assertThat(StringUtility.indexOf(testString, "t", 2), is(3));
	}

	@Test
	public void lastIndexOfTest() {
		assertThat(StringUtility.lastIndexOf(testString, 115), is(2));
	}

	@Test
	public void lastIndexOfTestWithStartPos() {
		assertThat(StringUtility.lastIndexOf(testString, 116, -1), is(-1));
	}

	@Test
	public void lastIndexOfTestWithSearchString() {
		assertThat(StringUtility.lastIndexOf("testabctest", testString), is(7));
	}

	@Test
	public void lastIndexOfTestWithSearchStringAndStartPos() {
		assertThat(StringUtility.lastIndexOf("testabctestabc", testString, -1), is(-1));
	}

	@Test
	public void substringTest() {
		assertThat(StringUtility.substring(testString, 1), is("est"));
	}

	@Test
	public void substringTestWithEndPos() {
		assertThat(StringUtility.substring(testString, 1, 3), is("es"));
	}

	@Test
	public void removeLeftCharTest() {
		assertThat(StringUtility.removeLeftChar(testString, 2), is("te"));
	}

	@Test
	public void removeRightCharTest() {
		assertThat(StringUtility.removeRightChar(testString, 2), is("st"));
	}

	@Test
	public void removeMidCharTest() {
		assertThat(StringUtility.removeMidChar(testString, 1, 2), is("es"));

	}

	@Test
	public void substringBeforeTest() {
		assertThat(StringUtility.substringBefore(testString, "s"), is("te"));
	}

	@Test
	public void substringAfterTest() {
		assertThat(StringUtility.substringAfter(testString, "t"), is("est"));
	}

	@Test
	public void splitTest() {
		assertThat(StringUtility.split("test abc"), is(testArray));
	}

	@Test
	public void splitTestWithSperatorChar() {
		assertThat(StringUtility.split("testxabc", 'x'), is(testArray));
	}

	@Test
	public void splitByCharacterTypeTest() {
		assertThat(StringUtility.splitByCharacterType(testMixString), is(testMixArray));
	}

	@Test
	public void splitByCharacterTypeWithCamelCaseTest() {
		String[] testSplitArray = { "MIND", "Tree" };
		assertThat(StringUtility.splitByCharacterTypeCamelCase("MINDTree"), is(testSplitArray));
	}

	@Test
	public void joinObjectTest() {
		assertThat(StringUtility.join(testArray, ';'), is("test;abc"));
	}

	@Test
	public void joinLongArrayTest() {
		long[] testArr = { 1, 2, 3 };
		assertThat(StringUtility.join(testArr, ';'), is(joinedTestArr));

	}

	@Test
	public void joinIntArrayTest() {
		int[] testArr = { 1, 2, 3 };
		assertThat(StringUtility.join(testArr, ';'), is(joinedTestArr));
	}

	@Test
	public void joinShortArrayTest() {
		short[] testArr = { 1, 2, 3 };
		assertThat(StringUtility.join(testArr, ';'), is(joinedTestArr));
	}

	@Test
	public void joinByteArrayTest() {
		byte[] testArr = { 1, 2, 3 };
		assertThat(StringUtility.join(testArr, ';'), is(joinedTestArr));
	}

	@Test
	public void joinCharArrayTest() {
		char[] testArr = { 'a', 'b', 'c' };
		assertThat(StringUtility.join(testArr, ';'), is("a;b;c"));
	}

	@Test
	public void joinFloatArrayTest() {
		float[] testArr = { 1, 2, 3 };
		assertThat(StringUtility.join(testArr, ';'), is("1.0;2.0;3.0"));
	}

	@Test
	public void joinDoubleArrayTest() {
		double[] testArr = { 1, 2, 3 };
		assertThat(StringUtility.join(testArr, ';'), is("1.0;2.0;3.0"));
	}

	@Test
	public void joinObjectArrayTestWithIndex() {
		assertThat(StringUtility.join(testArray, ';', 0, 2), is("test;abc"));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void joinObjectArrayTestWithArrayIndexException() {
		StringUtility.join(testArray, ';', -1, 2);
	}

	@Test
	public void joinLongArrayTestWithIndex() {
		long[] testArr = { 1, 2, 3 };
		assertThat(StringUtility.join(testArr, ';', 0, 3), is(joinedTestArr));

	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void joinLongArrayTestWithArrayIndexException() {
		StringUtility.join(testArray, ';', -1, 2);
	}

	@Test
	public void joinIntArrayTestWithIndex() {
		int[] testArr = { 1, 2, 3 };
		assertThat(StringUtility.join(testArr, ';', 0, 3), is(joinedTestArr));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void joinIntArrayTestWithArrayIndexException() {
		StringUtility.join(testArray, ';', -1, 2);
	}

	@Test
	public void joinShortArrayTestWithIndex() {
		short[] testArr = { 1, 2, 3 };
		assertThat(StringUtility.join(testArr, ';', 0, 3), is(joinedTestArr));
	}

	@Test
	public void joinByteArrayTestWithIndex() {
		byte[] testArr = { 1, 2, 3 };
		assertThat(StringUtility.join(testArr, ';', 0, 3), is(joinedTestArr));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void joinByteArrayTestWithArrayIndexException() {
		StringUtility.join(testArray, ';', -1, 2);
	}

	@Test
	public void joinCharArrayTestWithIndex() {
		char[] testArr = { 'a', 'b', 'c' };
		assertThat(StringUtility.join(testArr, ';', 0, 3), is("a;b;c"));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void joinCharArrayTestWithArrayIndexException() {
		StringUtility.join(testArray, ';', -1, 2);
	}

	@Test
	public void joinFloatArrayTestWithIndex() {
		float[] testArr = { 1, 2, 3 };
		assertThat(StringUtility.join(testArr, ';', 0, 3), is("1.0;2.0;3.0"));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void joinFloatArrayTestWithArrayIndexException() {
		StringUtility.join(testArray, ';', -1, 2);
	}

	@Test
	public void joinDoubleArrayTestWithIndex() {
		double[] testArr = { 1, 2, 3 };
		assertThat(StringUtility.join(testArr, ';', 0, 3), is("1.0;2.0;3.0"));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void joinDoubleArrayTestWithArrayIndexException() {
		StringUtility.join(testArray, ';', -1, 2);
	}

	@Test
	public void deleteWhitespaceTest() {
		assertThat(StringUtility.deleteWhitespace("  a  b c    "), is("abc"));
	}

	@Test
	public void removeTest() {
		assertThat(StringUtility.remove(testString, "e"), is("tst"));
	}

	@Test
	public void removeIgnoreCaseTest() {
		assertThat(StringUtility.removeIgnoreCase(testString, "E"), is("tst"));
	}

	@Test
	public void removeTestWithCharRemove() {
		assertThat(StringUtility.remove(testString, 'e'), is("tst"));
	}

	@Test
	public void removeAllTest() {
		assertThat(StringUtility.removeAll(testString, "t"), is("es"));
	}

	@Test(expected = PatternSyntaxException.class)
	public void removeAllTestWithPatternException() {
		StringUtility.removeAll(testString, "[");
	}

	@Test
	public void replaceOnceTest() {
		assertThat(StringUtility.replaceOnce(testString, "t", "for"), is("forest"));
	}

	@Test
	public void replaceOnceIgnoreCaseTest() {
		assertThat(StringUtility.replaceOnceIgnoreCase(testString, "T", "For"), is("Forest"));
	}

	@Test
	public void replacePatternTest() {
		assertThat(StringUtility.replacePattern("ABCabc123", "[a-z]", "_"), is("ABC___123"));
	}

	@Test(expected = PatternSyntaxException.class)
	public void replacePatternTestWithException() {
		StringUtility.replacePattern("ABCabc123", "[", "_");
	}

	@Test
	public void removePatternTest() {
		assertThat(StringUtility.removePattern("ABCabc123", "[a-z]"), is("ABC123"));
	}

	@Test(expected = PatternSyntaxException.class)
	public void removePatternTestWithException() {
		StringUtility.removePattern("ABCabc123", "[");
	}

	@Test
	public void replaceAllTest() {
		assertThat(StringUtility.replaceAll("abc", "", "ZZZ"), is("ZZZaZZZbZZZcZZZ"));
	}

	@Test(expected = PatternSyntaxException.class)
	public void replaceAllTestWithPatternException() {
		StringUtility.replaceAll("abc", "[", "ZZ");
	}

	@Test
	public void replaceTest() {
		assertThat(StringUtility.replace(testString, "t", "y"), is("yesy"));
	}

	@Test
	public void replaceIgnoreCaseTest() {
		assertThat(StringUtility.replaceIgnoreCase(testString, "T", "y"), is("yesy"));
	}

	@Test
	public void replaceTestWithMaxPos() {
		assertThat(StringUtility.replace(testString, "t", "For", 1), is("Forest"));
	}

	@Test
	public void replaceIgnoreCaseTestWithMaxPos() {
		assertThat(StringUtility.replaceIgnoreCase(testString, "T", "For", 1), is("Forest"));
	}

	@Test
	public void replaceCharsTest() {
		assertThat(StringUtility.replaceChars(testString, 't', 'f'), is("fesf"));
	}

	@Test
	public void replaceCharsTestWithStringParameters() {
		assertThat(StringUtility.replace(testString, "es", "xz"), is("txzt"));
	}

	@Test
	public void overlayTest() {
		assertThat(StringUtility.overlay(testMixString, "12345", 3, 7), is("t@#12345%t"));
	}

	@Test
	public void chompTest() {
		assertThat(StringUtility.chomp("abc\n"), is("abc"));
	}

	@Test
	public void chopTest() {
		assertThat(StringUtility.chop(testString), is("tes"));
	}

	@Test
	public void repeatTestWithString() {
		assertThat(StringUtility.repeat("12", 3), is("121212"));
	}

	@Test
	public void repeatTestWithSeperator() {
		assertThat(StringUtility.repeat("Hello", "-", 3), is("Hello-Hello-Hello"));
	}

	@Test
	public void repeattestWithChar() {
		assertThat(StringUtility.repeat('a', 5), is("aaaaa"));
	}

	@Test
	public void lengthTest() {
		assertThat(StringUtility.length(testString), is(4));
	}

	@Test
	public void upperCaseTest() {
		assertThat(StringUtility.upperCase(testString), is("TEST"));
	}

	@Test
	public void upperCaseTestWithLocale() {
		assertThat(StringUtility.upperCase(testString, Locale.ENGLISH), is("TEST"));
	}

	@Test
	public void lowerCaseTest() {
		assertThat(StringUtility.lowerCase("TEST"), is(testString));
	}

	@Test
	public void lowerCaseTestWithLocale() {
		assertThat(StringUtility.lowerCase("TEST", Locale.ENGLISH), is(testString));
	}

	@Test
	public void uncapitalizeTest() {
		assertThat(StringUtility.uncapitalize("Test"), is(testString));
	}

	@Test
	public void swapCaseTest() {
		assertThat(StringUtility.swapCase("TeSt"), is("tEsT"));
	}

	@Test
	public void countMatchesTestWithCharSequence() {
		assertThat(StringUtility.countMatches(testString, "t"), is(2));

	}

	@Test
	public void countMatchesTestWithChar() {
		assertThat(StringUtility.countMatches(testString, 'e'), is(1));
	}

	@Test
	public void isAlphaTest() {
		assertThat(StringUtility.isAlpha(testString), is(true));
	}

	@Test
	public void isAlphanumericTest() {
		assertThat(StringUtility.isAlphanumeric("test123"), is(true));
	}

	@Test
	public void isAlphanumericSpaceTest() {
		assertThat(StringUtility.isAlphanumericSpace("test123#"), is(false));
	}

	@Test
	public void isNumericTest() {
		assertThat(StringUtility.isNumeric(testString), is(false));
	}

	@Test
	public void isNumericSpaceTest() {
		assertThat(StringUtility.isNumericSpace(testString), is(false));
	}

	@Test
	public void getDigitsTest() {
		assertThat(StringUtility.getDigits("test123"), is("123"));
	}

	@Test
	public void isWhitespaceTest() {
		assertThat(StringUtility.isWhitespace(" "), is(true));
	}

	@Test
	public void isAllLowerCaseTest() {
		assertThat(StringUtility.isAllLowerCase(testString), is(true));
	}

	@Test
	public void isAllUpperCase() {
		assertThat(StringUtility.isAllUpperCase("TEST"), is(true));
	}

	@Test
	public void isMixedCaseTest() {
		assertThat(StringUtility.isMixedCase("Test"), is(true));
	}

	@Test
	public void rotateTest() {
		assertThat(StringUtility.rotate(testString, 2), is("stte"));
	}

	@Test
	public void reverseTest() {
		assertThat(StringUtility.reverse(testString), is("tset"));
	}

	@Test
	public void reverseDelimitedTest() {
		assertThat(StringUtility.reverseDelimited(testString, 'e'), is("stet"));
	}

	@Test
	public void abbreviateTest() {
		assertThat(StringUtility.abbreviate("abcdefghijkl", 6), is("abc..."));
	}

	@Test(expected = IllegalArgumentException.class)
	public void abbreviateTestWithIllegalArgumentException() {
		StringUtility.abbreviate("aa", -1);
	}

	@Test
	public void abbreviateTestWithOffset() {
		assertThat(StringUtility.abbreviate("abcdefghijkl", 10, 7), is("...ijkl"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void abbreviateTestWithOffsetWithIllegalArgumentException() {
		StringUtility.abbreviate("aa", 10, -1);
	}

	@Test
	public void abbreviateTestWithAbbrevMarker() {
		assertThat(StringUtility.abbreviate("abcdefghijkl", "@", 3), is("ab@"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void abbreviateTestWithMarkerWithIllegalArgumentException() {
		StringUtility.abbreviate("aa", "@", -1);
	}

	@Test
	public void abbreviateTestWithAbbrevMarkerAndOffset() {
		assertThat(StringUtility.abbreviate("abcdefghijkl", "*", 6, 4), is("*gh*"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void abbreviateTestWithMarkerAndOffsetWithIllegalArgumentException() {
		StringUtility.abbreviate("abcdefghijkl", "*", 4, -1);
	}

	@Test
	public void abbreviateMiddleTest() {
		assertThat(StringUtility.abbreviateMiddle("abcdefghijkl", "#", 5), is("ab#kl"));
	}

	@Test
	public void differenceTest() {
		assertThat(StringUtility.difference("ab", "abxyz"), is("xyz"));
	}

	@Test
	public void startsWithTest() {
		assertThat(StringUtility.startsWith(testString, "te"), is(true));
	}

	@Test
	public void startsWithIgnoreCaseTest() {
		assertThat(StringUtility.startsWithIgnoreCase(testString, "TE"), is(true));
	}

	@Test
	public void endsWithTest() {
		assertThat(StringUtility.endsWith(testString, "st"), is(true));
	}

	@Test
	public void endsWithIgnoreCaseTest() {
		assertThat(StringUtility.endsWithIgnoreCase(testString, "ST"), is(true));
	}

	@Test
	public void normalizeSpaceTest() {
		assertThat(StringUtility.normalizeSpace("  te     st  "), is("te st"));
	}

	@Test
	public void appendifMissingTest() {
		assertThat(StringUtility.appendIfMissing(testString, "abc"), is("testabc"));
	}

	@Test
	public void appendIfMissingIgnoreCaseTest() {
		assertThat(StringUtility.appendIfMissingIgnoreCase(testString, "sT"), is("test"));
	}

	@Test
	public void prependIfMissingTest() {
		assertThat(StringUtility.prependIfMissing(testString, "AT"), is("ATtest"));
	}

	@Test
	public void prependIfMissingIgnoreCaseTest() {
		assertThat(StringUtility.prependIfMissingIgnoreCase(testString, "AT"), is("ATtest"));
	}

	@Test
	public void wrapTestWithChar() {
		assertThat(StringUtility.wrap(testString, 'S'), is("StestS"));
	}

	@Test
	public void wrapTestWithString() {
		assertThat(StringUtility.wrap(testString, "XX"), is("XXtestXX"));
	}

	@Test
	public void wrapIfMissingTestWithChar() {
		assertThat(StringUtility.wrapIfMissing(testString, 'T'), is("TtestT"));
	}

	@Test
	public void wrapIfMissingTestWithString() {
		assertThat(StringUtility.wrapIfMissing(testString, "te"), is("testte"));
	}

	@Test
	public void unwrapTestWithString() {
		assertThat(StringUtility.unwrap("AABabcBAA", "AA"), is("BabcB"));
	}

	@Test
	public void unwrapTestWithChar() {
		assertThat(StringUtility.unwrap(testString, 't'), is("es"));
	}

}
