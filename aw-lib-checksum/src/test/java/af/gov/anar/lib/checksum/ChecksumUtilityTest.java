
package af.gov.anar.lib.checksum;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class ChecksumUtilityTest {

	@Test
	public void checksumTest() {
		String id = "1234567890";
		String checkSum = ChecksumUtility.generateChecksumDigit(id);
		String finalId = id + checkSum;
		boolean res = ChecksumUtility.validateChecksum(finalId);
		assertThat(res, is(true));
	}

	@Test
	public void checksumFailTest() {
		String id = "1234567891";
		boolean res = ChecksumUtility.validateChecksum(id);
		assertThat(res, is(false));
	}

}
