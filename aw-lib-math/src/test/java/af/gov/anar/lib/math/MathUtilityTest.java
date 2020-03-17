package af.gov.anar.lib.math;

import af.gov.anar.lang.infrastructure.exception.common.ArithmeticException;
import af.gov.anar.lang.infrastructure.exception.common.IllegalArgumentException;
import af.gov.anar.lib.math.exception.NotANumberException;
import af.gov.anar.lib.math.exception.NotFiniteNumberException;
import af.gov.anar.lib.math.exception.NotPositiveException;
import af.gov.anar.lib.math.exception.NumberIsTooLargeException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MathUtilityTest {
	int a[] = new int[3];
	double b[] = new double[3];
	List<Integer> list = new ArrayList<Integer>();

	@Before
	public void setUp() {

		a[0] = 1;
		a[1] = 2;
		a[2] = 3;
		b[0] = 1.0;
		b[1] = 2.0;
		b[2] = 3.0;
		list = Arrays.asList(2, 3, 3);
	}

	@Test
	public void getCopyOfArrayTest1() {
		assertThat(MathUtility.getCopyOfArray(a), is(a));
	}

	@Test
	public void getCopyOfArrayTest2() {

		assertThat(MathUtility.getCopyOfArray(a, 4).length, is(4));
		assertThat(MathUtility.getCopyOfArray(a, 4)[3], is(0));
		assertThat(MathUtility.getCopyOfArray(a, 4)[2], is(a[2]));
	}

	@Test
	public void getCopyOfArrayTest3() {
		assertThat(MathUtility.getCopyOfArray(b), is(b));
	}

	@Test
	public void getCopyOfArrayTest4() {

		assertThat(MathUtility.getCopyOfArray(b, 4).length, is(4));
		assertThat(MathUtility.getCopyOfArray(b, 4)[3], is(0.0));
		assertThat(MathUtility.getCopyOfArray(b, 4)[2], is(b[2]));
	}

	@Test
	public void getPowTest1() {

		assertThat(MathUtility.getPow(2.0, 3.0), is(8.0));
	}

	@Test
	public void getPowTest2() {
		assertThat(MathUtility.getPow(2, 3), is(8));
	}

	@Test(expected = NotPositiveException.class)
	public void getPowExceptionTest1() {
		MathUtility.getPow(2, -2);

	}

	@Test(expected = ArithmeticException.class)
	public void getPowExceptionTest2() {
		MathUtility.getPow(2, 9999999);
	}

	@Test
	public void getPowTest3() {
		assertThat(MathUtility.getPow(9L, 2), is(81L));
	}

	@Test(expected = NotPositiveException.class)
	public void getPowExceptionTest3() {
		MathUtility.getPow(9L, -2);
	}

	@Test(expected = ArithmeticException.class)
	public void getPowExceptionTest4() {
		MathUtility.getPow(99999999L, 9);
	}

	@Test
	public void getPowTest4() {
		BigInteger bi = new BigInteger("2");
		assertThat(MathUtility.getPow(bi, 2), is(bi.pow(2)));
	}

	@Test(expected = NotPositiveException.class)
	public void getPowExceptionTest5() {
		BigInteger bi = new BigInteger("2");
		MathUtility.getPow(bi, -2);
	}

	@Test
	public void getPowTest5() {
		BigInteger bi = new BigInteger("2");
		BigInteger bint = new BigInteger("3");
		BigInteger bi2 = new BigInteger("8");
		assertThat(MathUtility.getPow(bi, bint), is(bi2));
	}

	@Test(expected = NotPositiveException.class)
	public void getPowExceptionTest6() {
		BigInteger bi = new BigInteger("2");
		BigInteger bint = new BigInteger("3");
		bint = BigInteger.ZERO.subtract(bint);
		MathUtility.getPow(bi, bint);
	}

	@Test(expected = NullPointerException.class)
	public void getPowExceptionTest7() {
		MathUtility.getPow(null, null);

	}

	@Test(expected = NullPointerException.class)
	public void getPowExceptionTest8() {
		MathUtility.getPow(null, 2);

	}

	@Test
	public void getSqrttest() {
		assertThat(MathUtility.getSqrt(4.0), is(2.0));
	}

	@Test
	public void getRoundtest() {
		assertThat(MathUtility.getRound(8.897, 1), is(8.9));
	}

	@Test
	public void getAbstest1() {
		assertThat(MathUtility.getAbs(-999), is(999));
	}

	@Test
	public void getAbstest2() {
		assertThat(MathUtility.getAbs(-999.0), is(999.0));
	}

	@Test
	public void getAbstest3() {
		assertThat(MathUtility.getAbs(-999L), is(999L));
	}

	@Test
	public void getAbstest4() {
		assertThat(MathUtility.getAbs(-999F), is(999F));
	}

	@Test
	public void getRoundtest2() {
		assertThat(MathUtility.getRound(4.5), is(5L));
	}

	@Test
	public void getRoundtest3() {
		assertThat(MathUtility.getRound(4.888888F, 0), is(5.0F));
	}

	@Test
	public void getLog10test() {
		assertThat(MathUtility.getLog10(10.0), is(1.0));
	}

	@Test
	public void getLogtest() {
		assertThat(MathUtility.getLog(2.7), is(0.9932517730102834));
	}

	@Test
	public void getFactorialtest() {
		assertThat(MathUtility.getFactorial(3), is(6L));
	}

	@Test(expected = NotPositiveException.class)
	public void getFactorialExceptionTest1() {
		MathUtility.getFactorial(-1);
	}

	@Test(expected = ArithmeticException.class)
	public void getFactorialExceptionTest2() {
		MathUtility.getFactorial(999999);
	}

	@Test
	public void getMaxtest() {
		assertThat(MathUtility.getMax(2.0, 3.0), is(3.0));
	}

	@Test
	public void getMaxtest2() {
		assertThat(MathUtility.getMax(2, 3), is(3));
	}

	@Test
	public void getGcdtest() {
		assertThat(MathUtility.getGcd(2, 3), is(1));
	}

	@Test(expected = ArithmeticException.class)
	public void getGcdExceptionTest1() {
		MathUtility.getGcd(Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	@Test
	public void getGcdtest2() {
		assertThat(MathUtility.getGcd(2L, 3L), is(1L));
	}

	@Test(expected = ArithmeticException.class)
	public void getGcdExceptionTest2() {
		MathUtility.getGcd(Long.MIN_VALUE, Long.MIN_VALUE);
	}

	@Test
	public void getArrayMaxValueTest1() {
		assertThat(MathUtility.getArrayMaxValue(b), is(3.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getArrayMaxValueExceptionTest1() {
		MathUtility.getArrayMaxValue(null);
	}

	@Test
	public void getArrayMaxValueTest2() {
		assertThat(MathUtility.getArrayMaxValue(b, 1, 2), is(3.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getArrayMaxValueExceptionTest2() {
		MathUtility.getArrayMaxValue(b, -1, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getArrayMaxValueExceptionTest3() {
		MathUtility.getArrayMaxValue(null, 0, 2);
	}

	@Test
	public void getArrayMinValueTest1() {
		assertThat(MathUtility.getArrayMinValue(b), is(1.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getArrayMinValueExceptionTest1() {
		MathUtility.getArrayMinValue(null);
	}

	@Test
	public void getArrayMinValueTest2() {
		assertThat(MathUtility.getArrayMinValue(b, 1, 2), is(2.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getArrayMinValueExceptionTest2() {
		MathUtility.getArrayMinValue(b, -1, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getArrayMinValueExceptionTest3() {
		MathUtility.getArrayMinValue(null, 0, 2);
	}

	@Test
	public void getArrayProductTest1() {
		assertThat(MathUtility.getArrayValuesProduct(b), is(6.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getArrayProductExceptionTest1() {
		MathUtility.getArrayValuesProduct(null);
	}

	@Test
	public void getArrayProductTest2() {
		assertThat(MathUtility.getArrayValuesProduct(b, 0, 2), is(2.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getArrayProductExceptionTest2() {
		MathUtility.getArrayValuesProduct(b, -1, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getArrayProductExceptionTest3() {
		MathUtility.getArrayValuesProduct(null, 1, 2);
	}

	@Test
	public void getArraySumTest1() {
		assertThat(MathUtility.getArrayValuesSum(b), is(6.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getArraySumExceptionTest1() {
		MathUtility.getArrayValuesSum(null);
	}

	@Test
	public void getarraySumTest2() {
		assertThat(MathUtility.getArrayValuesSum(b, 1, 2), is(5.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getarraySumExceptionTest2() {
		MathUtility.getArrayValuesSum(b, -1, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getarraySumExceptionTest3() {
		MathUtility.getArrayValuesSum(null, 1, 2);
	}

	@Test
	public void getRandomtest1() {
		assertThat(MathUtility.getRandom(3.8F, 9.8F), is(instanceOf(Float.class)));
		Float randomFloat = MathUtility.getRandom(3.8F, 9.8F);
		assertEquals(true, 3.8F <= randomFloat && randomFloat <= 9.8F);

	}

	@Test
	public void getRandomtest2() {
		assertThat(MathUtility.getRandom(3, 9), is(instanceOf(Integer.class)));
		int randomFloat = MathUtility.getRandom(3, 9);
		assertEquals(true, 3 <= randomFloat && randomFloat <= 9);
	}

	@Test(expected = NumberIsTooLargeException.class)
	public void getRandomExceptionTest2() {
		MathUtility.getRandom(4, 1);
	}

	@Test
	public void getRandomtest3() {
		assertThat(MathUtility.getRandom(8L, 9L), is(instanceOf(Long.class)));
		long randomFloat = MathUtility.getRandom(8L, 9L);
		assertEquals(true, 8L <= randomFloat && randomFloat <= 9L);
	}

	@Test(expected = NumberIsTooLargeException.class)
	public void getRandomExceptionTest3() {
		MathUtility.getRandom(4L, 1L);
	}

	@Test
	public void getRandomtest4() {
		assertThat(MathUtility.getRandom(8.0, 9.0), is(instanceOf(Double.class)));
		double randomFloat = MathUtility.getRandom(8.0, 9.0);
		assertTrue(8.0 <= randomFloat && randomFloat <= 9.0);
	}

	@Test(expected = NumberIsTooLargeException.class)
	public void getRandomExceptionTest4() {
		MathUtility.getRandom(6.0, 1.0);
	}

	@Test(expected = NotFiniteNumberException.class)
	public void getRandomExceptionTest5() {
		MathUtility.getRandom(6, Double.POSITIVE_INFINITY);
	}

	@Test(expected = NotANumberException.class)
	public void getRandomExceptionTest6() {
		MathUtility.getRandom(6, Double.NaN);
	}

	@Test
	public void isPrimeTest() {
		assertThat(MathUtility.isPrimeOrNot(2), is(true));
	}

	@Test
	public void nextPrimeTest() {
		assertThat(MathUtility.nextPrimeNumber(1), is(2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nextPrimeExceptionTest() {
		MathUtility.nextPrimeNumber(-1);
	}

	@Test
	public void getArrayValueMeanTest() {
		assertThat(MathUtility.getArrayValuesMean(b), is(2.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getArrayValueMeanExceptionTest1() {
		MathUtility.getArrayValuesMean(null);
	}

	@Test
	public void getArrayValueMeanTest2() {
		assertThat(MathUtility.getArrayValuesMean(b, 0, 3), is(2.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getArrayValueMeanExceptionTest2() {
		MathUtility.getArrayValuesMean(b, -1, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getArrayValueMeanExceptionTest3() {
		MathUtility.getArrayValuesMean(null, 0, 3);
	}

	@Test
	public void getPrimeFactorTest() {
		assertThat(MathUtility.getPrimeFactors(18), is(list));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getPrimeFactorExceptionTest() {
		MathUtility.getPrimeFactors(1);
	}

	@Test
	public void ceilTest() {
		assertThat(MathUtility.ceil(9.2), is(10.0));
	}

	@Test
	public void floorTest() {
		assertThat(MathUtility.floor(9.2), is(9.0));
	}
}
