package af.gov.anar.lib.hmac;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class HMACUtilityTest {

	@Test
	public void testGenerateHash() {
		String name = "Badar Hashimi";
		assertNotNull(HMACUtility.generateHash(name.getBytes()));
	}

	@Test
	public void testUpdate() {
		String name = "Badar Hashimi";
		HMACUtility.update(name.getBytes());
	}

	@Test
	public void testUpdatedHash() {
		assertNotNull(HMACUtility.generateHash(HMACUtility.updatedHash()));
	}

	@Test
	public void testDigestAsPlainText() {
		assertNotNull(HMACUtility.digestAsPlainText("Badar Hashimi".getBytes()));
	}
	
	@Test
	public void testGenerateRandomIV(){
		assertThat(HMACUtility.generateSalt(),isA(byte[].class));
	}
	
	@Test
	public void testGenerateRandomIVInputBytes(){
		assertThat(HMACUtility.generateSalt(16),isA(byte[].class));
	}

}
