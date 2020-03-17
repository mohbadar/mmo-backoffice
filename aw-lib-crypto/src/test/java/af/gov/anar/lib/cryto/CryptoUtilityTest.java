package af.gov.anar.lib.cryto;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

public class CryptoUtilityTest {

	@Test
	public void testCombineByteArray() {
		assertThat(CryptoUtility.combineByteArray("data".getBytes(), "key".getBytes(), "#KEY_SPLITTER#"),
				isA(byte[].class));
	}

	@Test
	public void testGetSplitterIndex() {
		assertThat(CryptoUtility.getSplitterIndex("data#KEY_SPLITTER#data".getBytes(), 0, "#KEY_SPLITTER#"),
				isA(int.class));
	}

	@Test
	public void testEncodeBase64() {
		assertThat(CryptoUtility.encodeBase64("data".getBytes()), isA(String.class));
	}

	@Test
	public void testDecodeBase64() {
		assertThat(CryptoUtility.decodeBase64("data"), isA(byte[].class));
	}

}
