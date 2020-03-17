package af.gov.anar.lib.hsm.service.thales;

import af.asr.lib.hsm.constants.KSNDescriptor;
import af.asr.lib.hsm.constants.PinBlockFormat;
import af.asr.lib.hsm.constants.PinKeyType;
import af.asr.lib.hsm.model.HSMResponse;
import af.asr.lib.hsm.service.HSMConfig;
import af.asr.lib.hsm.service.HSMService;
import af.asr.lib.hsm.service.PVVService;
import af.asr.lib.hsm.service.ThalesHSMConnect;
import org.slf4j.Logger;

public final class ThalesPVVService implements PVVService {

	private final HSMService hsmService;

	private static final String DUKPT_MODE = "0";

	//@formatter:off
	public ThalesPVVService(final HSMService hsmService) {
		this.hsmService = hsmService;
	}

	public final HSMResponse calculatePVVUsingPin(final HSMConfig hsmConfig, final String pan12, final String pin, final String pvk, final String pvki, final Logger logger) {
		try {
			final HSMResponse BAResponse = hsmService.thales().encryptPinUnderLMK(hsmConfig, pan12, pin, logger);
			if (!BAResponse.isSuccess) return BAResponse;
			final String      command     = new StringBuilder("0000DG").append(pvk).append(BAResponse.value).append(pan12).append(pvki).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(8);
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}
	
	//@formatter:on
	public final HSMResponse calculatePVVUsingPinblock(final HSMConfig hsmConfig, final String pan12, final String pinblock, final PinBlockFormat format,
			final String pvk, final String pvki, final PinKeyType pinKeyType, final String pinKey, final Logger logger) {
		try {
			final String      command     = new StringBuilder("0000FW").append(pinKeyType).append(pinKey).append(pvk).append(pinblock).append(format)
					.append(pan12).append(pvki).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(8);
			return hsmResponse;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return HSMResponse.IO;
	}

	//@formatter:off
	public final HSMResponse validateDUKPTPin(final HSMConfig hsmConfig, final String pan12, final String pvv, final String pinBlock, final PinBlockFormat format,
			final String ksn, final KSNDescriptor ksnDescriptor, final String pvk, final String pvki, final String bdk, final Logger logger) {
		try {
			final String command  = new StringBuilder(128).append("0000GQ").append(DUKPT_MODE).append(bdk).append(pvk).append(ksnDescriptor)
									.append(ksn).append(pinBlock).append(format).append(pan12).append(pvki).append(pvv).toString();
			final String response = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}

	
	public final HSMResponse validateInterchangePin(final HSMConfig hsmConfig, final String pan12, final String pvv, final String pinBlock, final PinBlockFormat format,
			final String pvk, final String pvki, final String zpk, final Logger logger) {
		try {
			final String command = new StringBuilder("0000EC").append(zpk).append(pvk).append(pinBlock).append(format).append(pan12).append(pvki)
								  .append(pvv).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}


	public final HSMResponse validateTerminalPin(final HSMConfig hsmConfig, final String pan12, final String pvv, final String pinBlock, final PinBlockFormat format, final String pvk,
			final String pvki, final String tpk, final Logger logger) {
		try {
			final String command = new StringBuilder("0000DC").append(tpk).append(pvk).append(pinBlock).append(format).append(pan12).append(pvki)
									.append(pvv).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}

	public final HSMResponse changePVV(final HSMConfig hsmConfig, final String pan12, final String pvv, final String pinBlock, final String newPinBlock, final String pvk,
			final PinBlockFormat format, final String pvki, final PinKeyType pinKeyType, final String pinKey, final Logger logger) {
		try {
			final String      command     = new StringBuilder(128).append("0000CU").append(pinKeyType).append(pinKey).append(pvk).append(pinBlock)
											.append(format).append(pan12).append(pvki).append(pvv).append(newPinBlock).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if(hsmResponse.isSuccess) hsmResponse.value = response.substring(8,12);
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}




}
