package af.gov.anar.lib.hsm.service;

import af.asr.lib.hsm.constants.PinBlockFormat;
import af.asr.lib.hsm.model.HSMResponse;
import org.slf4j.Logger;

public interface ThalesService {

	/**
	 * 
	 * @param pan12		: The 12 right-most digits of the account number, excluding the check digit.
	 * @param pin		: The clear text PIN left-justified and padded with X'F to the maximum PIN length L. 
	 * 					  Value set with the CS (Configure Security) console command (range 5 – 13).
	 * @param logger
	 * @return
	 */
	public abstract HSMResponse encryptPinUnderLMK(final HSMConfig hsmConfig, final String pan12, final String pin, final Logger logger);

	public abstract HSMResponse encryptPinblockUnderLMK(final HSMConfig hsmConfig, final String pan12, final String pinblock, final String tpk, final PinBlockFormat format, final Logger logger);

	public abstract HSMResponse decryptPinUnderLMK(final HSMConfig hsmConfig, final String pan12, final String pinlmk, final Logger logger);
}
