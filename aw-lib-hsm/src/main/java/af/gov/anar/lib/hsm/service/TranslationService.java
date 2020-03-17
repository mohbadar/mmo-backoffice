package af.gov.anar.lib.hsm.service;

import af.asr.lib.hsm.constants.BDKType;
import af.asr.lib.hsm.constants.KSNDescriptor;
import af.asr.lib.hsm.constants.PinBlockFormat;
import af.asr.lib.hsm.model.HSMResponse;
import org.slf4j.Logger;

public interface TranslationService {

	//@formatter:off
	/**
	 * 
	 * @param pan12			: The 12 right-most digits of the PAN/Token, excluding the check digit.
	 * @param pinblock		: The source PIN block encrypted under the source ZPK.
	 * @param sourceFormat	: The format code for the source PIN block.
	 * @param sourceZPK		: The ZPK under which the PIN block is currently encrypted.
	 * @param targetFormat	: The format code for the destination PIN block.
	 * @param targetZPK		: Destination ZPK under which the PIN block is to be encrypted.
	 * @param logger
	 * @return				: The destination PIN block encrypted under the destination ZPK.
	 */
	public abstract HSMResponse fromZPKToZPK(final HSMConfig hsmConfig, final String pan12, final String pinblock, final PinBlockFormat sourceFormat, final String sourceZPK,
                                             final PinBlockFormat targetFormat, final String targetZPK, final Logger logger) ;

	/**
	 * 
	 * @param pan12				: The 12 right-most digits of the PAN/Token, excluding the check digit.
	 * @param sourcePinBlock	: The source PIN block encrypted under the source TPK.
	 * @param sourceFormat		: The format code for the source PIN block.
	 * @param sourceTPK			: The TPK under which the PIN block is currently encrypted.
	 * @param targetFormat		: The format code for the destination PIN block.
	 * @param targetZPK			: Destination ZPK under which the PIN block is to be encrypted.
	 * @param logger
	 * @return					: The destination PIN block encrypted under the destination ZPK.
	 */
	public abstract HSMResponse fromTPKToZPK(final HSMConfig hsmConfig, final String pan12, final String sourcePinBlock, final PinBlockFormat sourceFormat, final String sourceTPK,
                                             final PinBlockFormat targetFormat, final String targetZPK, final Logger logger);

	
	/**
	 * 
	 * @param pan12				: The 12 right-most digits of the PAN/Token, excluding the check digit.
	 * @param sourcePinBlock	: The source PIN block encrypted under the source TPK.
	 * @param sourceFormat		: The format code for the source PIN block.
	 * @param sourceTPK			: The TPK under which the PIN block is currently encrypted.
	 * @param sourceBDKType		: BDK1 Value '*' (X'2A). BDK2 Value '~' (X'7E).
	 * @param targetDescriptor	: 1st digit: Destination BDK Identifier Length (‘0’ – ‘F’) 2nd digit: Reserved. Should be ‘0’. 3rd digit: Device Identifier Length (‘0’ – ‘F’)
	 * @param targetKSN			: The Destination Key Serial Number.
	 * @param targetBDK			: BDK under which the PIN block is to be encrypted.
	 * @param targetFormat		: The format code for the destination PIN block.
	 * @param logger
	 * @return					: The destination PIN block encrypted under the destination BDK.
	 */
	public abstract HSMResponse fromTPKToBDK(final HSMConfig hsmConfig, final String pan12, final String sourcePinBlock, final PinBlockFormat sourceFormat, final String sourceTPK,
                                             final BDKType sourceBDKType, final KSNDescriptor targetDescriptor, final String targetKSN, final String targetBDK,
                                             final PinBlockFormat targetFormat, final Logger logger) ;
	
	/**
	 * 
	 * @param pan12				: The 12 right-most digits of the PAN/Token, excluding the check digit.
	 * @param sourcePinBlock	: The source PIN block encrypted under the source TPK.
	 * @param sourceFormat		: The format code for the source PIN block.
	 * @param sourceKSN			: The KSN supplied by the PIN pad.
	 * @param sourceBDKType		: BDK1 Value '*' (X'2A). BDK2 Value '~' (X'7E).
	 * @param sourceDescriptor	: 1st digit: Destination BDK Identifier Length (‘0’ – ‘F’) 2nd digit: Reserved. Should be ‘0’. 3rd digit: Device Identifier Length (‘0’ – ‘F’)
	 * @param sourceBDK			: Two types of BDK are supported: type-1 BDK and type-2 BDK.
	 * @param targetKSN			: The Destination Key Serial Number, supplied by the host.
	 * @param targeFormat		: The format code for the ‘Destination PIN Block’.
	 * @param targetBDKType		: BDK1 Value '*' (X'2A). BDK2 Value '~' (X'7E).
	 * @param targetDescriptor	: 1st digit: Destination BDK Identifier Length (‘0’ – ‘F’) 2nd digit: Reserved. Should be ‘0’. 3rd digit: Device Identifier Length (‘0’ – ‘F’)
	 * @param targetBDK			: Two types of BDK are supported: type-1 BDK and type-2 BDK.
	 * @param logger
	 * @return					: The PIN block encrypted under BDK.
	 */
	public abstract HSMResponse fromBDKToBDK(final HSMConfig hsmConfig, final String pan12, final String sourcePinBlock, final PinBlockFormat sourceFormat, final String sourceKSN,
                                             final BDKType sourceBDKType, final KSNDescriptor sourceDescriptor, final String sourceBDK, final String targetKSN, final PinBlockFormat targeFormat,
                                             final BDKType targetBDKType, final KSNDescriptor targetDescriptor, final String targetBDK, final Logger logger);

	
	/**
	 * 
	 * @param pan12				: The 12 right-most digits of the PAN/Token, excluding the check digit.
	 * @param sourcePinBlock	: The source PIN block encrypted under the source TPK.
	 * @param sourceFormat		: The format code for the source PIN block.
	 * @param sourceKSN			: The KSN supplied by the PIN pad.
	 * @param sourceDescriptor	: 1st digit: Destination BDK Identifier Length (‘0’ – ‘F’) 2nd digit: Reserved. Should be ‘0’. 3rd digit: Device Identifier Length (‘0’ – ‘F’)
	 * @param sourceBDKType		: BDK1 Value '*' (X'2A). BDK2 Value '~' (X'7E).
	 * @param sourceBDK			: Two types of BDK are supported: type-1 BDK and type-2 BDK.
	 * @param targetFormat		: The format code for the ‘Destination PIN Block’.
	 * @param targetZPK			: Destination ZPK under which the PIN block is to be encrypted.
	 * @param logger
	 * @return					: The destination PIN block encrypted under the destination ZPK.
	 */
	public abstract HSMResponse fromBDKToZPK(final HSMConfig hsmConfig, final String pan12, final String sourcePinBlock, final PinBlockFormat sourceFormat, final String sourceKSN,
                                             final KSNDescriptor sourceDescriptor, final BDKType sourceBDKType, final String sourceBDK, final PinBlockFormat targetFormat, final String targetZPK, final Logger logger);

	
	/**
	 * 
	 * @param pan12				: The 12 right-most digits of the PAN/Token, excluding the check digit.
	 * @param sourcePinblock	: The source PIN block encrypted under the ZPK.
	 * @param sourceFormat		: The format code for the source PIN block.
	 * @param sourceZPK			: The ZPK under which the PIN block is currently encrypted.
	 * @param logger
	 * @return					: (L N) The PIN encrypted under the LMK.
	 */
	public abstract HSMResponse fromZPKToLMK(final HSMConfig hsmConfig, final String pan12, final String sourcePinblock, final PinBlockFormat sourceFormat, final String sourceZPK, final Logger logger) ;
	
	/**
	 * 
	 * @param pan12				: The 12 right-most digits of the PAN/Token, excluding the check digit.
	 * @param sourcePinblock	: The source PIN block encrypted under the TPK.
	 * @param sourceFormat		: One of the valid PIN block format codes.
	 * @param sourceTPK			: The TPK under which the PIN block is currently encrypted.
	 * @param logger
	 * @return					: (L N) The PIN encrypted under the LMK.
	 */
	public abstract HSMResponse fromTPKToLMK(final HSMConfig hsmConfig, final String pan12, final String sourcePinblock, final PinBlockFormat sourceFormat, final String sourceTPK, final Logger logger) ;
	/**
	 * 
	 * @param pan12				: The 12 right-most digits of the PAN/Token, excluding the check digit.
	 * @param pinlmk			: The PIN encrypted under the LMK.
	 * @param targetFormat		: One of the valid PIN block format codes.
	 * @param targetZPK			: The ZPK under which the PIN block is to be encrypted.
	 * @param logger
	 * @return					: The PIN block encrypted under the ZPK.
	 */
	public abstract HSMResponse fromLMKToZPK(final HSMConfig hsmConfig, final String pan12, final String pinlmk, final PinBlockFormat targetFormat, final String targetZPK, final Logger logger) ;

}
