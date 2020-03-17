package af.gov.anar.lib.hsm.service.thales;

import af.asr.lib.hsm.constants.PinBlockFormat;
import af.asr.lib.hsm.model.HSMResponse;
import af.asr.lib.hsm.service.HSMConfig;
import af.asr.lib.hsm.service.ThalesHSMConnect;
import af.asr.lib.hsm.service.ThalesService;
import af.asr.lib.hsm.util.Strings;
import org.slf4j.Logger;

//@formatter:off
public final class ThalesServiceImpl implements ThalesService {

	@Override
	public final HSMResponse encryptPinUnderLMK(final HSMConfig hsmConfig, String pan12, String pin, Logger logger) {
		try {
			String            pinPadded   = Strings.padRight(new StringBuilder(pin), 'F', hsmConfig.lengthOfPinLMK);
			String            command     = new StringBuilder(23).append("0000BA").append(pinPadded).append(pan12).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(8);
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}

	@Override
	public final HSMResponse encryptPinblockUnderLMK(HSMConfig hsmConfig, String pan12, String pinblock, String tpk, PinBlockFormat format, Logger logger) {
		try {
			String            command     = new StringBuilder(23).append("0000JC").append(tpk).append(pinblock).append(format).append(pan12).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(8, 8 + hsmConfig.lengthOfPinLMK);
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}

	@Override
	public final HSMResponse decryptPinUnderLMK(HSMConfig hsmConfig, String pan12, String pinlmk, Logger logger) {
		try {
			String            command     = new StringBuilder(23).append("0000NG").append(pan12).append(pinlmk).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(8, 8 + hsmConfig.lengthOfPinLMK).replaceAll("F", "");
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}

}
