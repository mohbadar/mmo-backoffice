package af.gov.anar.lib.hsm.service.thales;

import af.asr.lib.hsm.constants.BDKType;
import af.asr.lib.hsm.constants.KSNDescriptor;
import af.asr.lib.hsm.constants.PinBlockFormat;
import af.asr.lib.hsm.model.HSMResponse;
import af.asr.lib.hsm.service.HSMConfig;
import af.asr.lib.hsm.service.ThalesHSMConnect;
import af.asr.lib.hsm.service.TranslationService;
import org.slf4j.Logger;

public final class ThalesTranslationService implements TranslationService {

	public final HSMResponse fromZPKToZPK(final HSMConfig hsmConfig, final String pan12, final String pinblock, final PinBlockFormat sourceFormat, final String sourceZPK,
			final PinBlockFormat targetFormat, final String targetZPK, final Logger logger) {
		try {
			final String      command     = new StringBuilder().append("0000CC").append(sourceZPK).append(targetZPK).append(hsmConfig.maximumPinLength)
											.append(pinblock).append(sourceFormat).append(targetFormat).append(pan12).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(10, 26);
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}

	public final HSMResponse fromTPKToZPK(final HSMConfig hsmConfig, final String pan12, final String sourcePinBlock, final PinBlockFormat sourceFormat, final String sourceTPK,
			final PinBlockFormat targetFormat, final String targetZPK, final Logger logger) {
		try {
			final String      command     = new StringBuilder().append("0000CA").append(sourceTPK).append(targetZPK).append(hsmConfig.maximumPinLength)
											.append(sourcePinBlock).append(sourceFormat).append(targetFormat).append(pan12).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(10, 26);
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}

	public final HSMResponse fromTPKToBDK(final HSMConfig hsmConfig, final String pan12, final String sourcePinBlock, final PinBlockFormat sourceFormat, final String sourceTPK,
			final BDKType sourceBDKType, final KSNDescriptor targetDescriptor, final String targetKSN, final String targetBDK,
			final PinBlockFormat targetFormat, final Logger logger) {
		try {
			final String      command     = new StringBuilder().append("0000CA").append(sourceTPK).append(sourceBDKType).append(targetBDK)
											.append(targetDescriptor).append(targetKSN).append(hsmConfig.maximumPinLength).append(sourcePinBlock)
											.append(sourceFormat).append(targetFormat).append(pan12).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(10, 26);
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}

	
	public final HSMResponse fromBDKToBDK(final HSMConfig hsmConfig, final String pan12, final String sourcePinBlock, final PinBlockFormat sourceFormat,final String sourceKSN, 
			final BDKType sourceBDKType, final KSNDescriptor sourceDescriptor, final String sourceBDK, final String targetKSN, final PinBlockFormat targeFormat,
			final BDKType targetBDKType, final KSNDescriptor targetDescriptor, final String targetBDK, final Logger logger) {
		try {
			final String      bdkTypeFlag = (sourceBDKType == BDKType.BDK2) ? sourceBDKType.toString() : "";
			final String      command     = new StringBuilder().append("0000G0").append(bdkTypeFlag).append(sourceBDK).append(targetBDKType).append(targetBDK)
					.append(sourceDescriptor).append(sourceKSN).append(targetDescriptor).append(targetKSN).append(sourcePinBlock).append(sourceFormat)
					.append(targeFormat).append(pan12).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(10, 26);
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}

	
	public final HSMResponse fromBDKToZPK(final HSMConfig hsmConfig, final String pan12, final String sourcePinBlock, final PinBlockFormat sourceFormat, final String sourceKSN,
			final KSNDescriptor sourceDescriptor, final BDKType sourceBDKType, final String sourceBDK, final PinBlockFormat targetFormat, final String targetZPK, final Logger logger) {
		try {
			final String      command     = new StringBuilder().append("0000G0").append(sourceBDKType).append(sourceBDK).append(targetZPK)
					.append(sourceDescriptor).append(sourceKSN).append(sourcePinBlock).append(sourceFormat).append(targetFormat).append(pan12).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(10, 26);
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}

	
	public final HSMResponse fromZPKToLMK(final HSMConfig hsmConfig, final String pan12, final String sourcePinblock, final PinBlockFormat sourceFormat, final String sourceZPK, final Logger logger) {
		try {
			final StringBuilder command     = new StringBuilder(70).append("0000JE").append(sourceZPK).append(sourcePinblock).append(sourceFormat).append(pan12);
			final String      response    	= ThalesHSMConnect.send(hsmConfig, command.toString(), logger);
			final HSMResponse hsmResponse 	= new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(8, 8 + hsmConfig.lengthOfPinLMK);
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}

	
	public final HSMResponse fromTPKToLMK(final HSMConfig hsmConfig, final String pan12, final String sourcePinblock, final PinBlockFormat sourceFormat, final String sourceTPK, final Logger logger) {
		try {
			final String      command     = new StringBuilder().append("0000JC").append(sourceTPK).append(sourcePinblock).append(sourceFormat).append(pan12).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(8, 8 + hsmConfig.lengthOfPinLMK);
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}

	public final HSMResponse fromLMKToZPK(final HSMConfig hsmConfig, final String pan12, final String pinlmk, final PinBlockFormat targetFormat, final String targetZPK, final Logger logger) {
		try {
			final String      command     = new StringBuilder().append("0000JG").append(targetZPK).append(targetFormat).append(pan12).append(pinlmk).toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(10, 26);
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}


}
