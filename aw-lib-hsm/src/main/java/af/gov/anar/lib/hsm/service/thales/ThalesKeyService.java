package af.gov.anar.lib.hsm.service.thales;

import af.asr.lib.hsm.constants.*;
import af.asr.lib.hsm.constants.KeyScheme.ANSI;
import af.asr.lib.hsm.constants.KeyScheme.VARIANT;
import af.asr.lib.hsm.model.GenKeyResponse;
import af.asr.lib.hsm.model.HSMResponse;
import af.asr.lib.hsm.service.HSMConfig;
import af.asr.lib.hsm.service.KeyService;
import af.asr.lib.hsm.service.ThalesHSMConnect;
import org.slf4j.Logger;

public final class ThalesKeyService implements KeyService {
	
	private final String MODE_GENERATE         = "0";
	private final String MODE_GENERATE_EXPORT  = "1";
	private final String MODE_DERIVE           = "A";
	private final String MODE_DERIVE_EXPORT    = "B";
	private final String DERIVE_KEY_MODE_DUKPT = "0";

	//@formatter:off
	public final HSMResponse calculateKCV(final HSMConfig hsmConfig, final String key, final KeyLength keyLength, final KeyType keyType, final Logger logger) {
		try {
			final String command = new StringBuilder().append("0000BUFF").append(keyLength.getLength() - 1).append(key).append(";").append(keyType)
								  .append(";001").toString();
			final String      response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final HSMResponse hsmResponse = new HSMResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) hsmResponse.value = response.substring(8, 14);
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return HSMResponse.IO;
	}
	
	
	/**
	 * @param keyType	: Indicates the key type of the key to be generated.
	 * @param keyScheme	: Indicates the scheme for encrypting the output key under the LMK.
	 * @param logger
	 * @return			: The new key, encrypted under the LMK.
	 */
	public final GenKeyResponse generateKey(final HSMConfig hsmConfig, final KeyType keyType, final KeyScheme.VARIANT keyScheme, final Logger logger) {
		try {
			final String         command     = new StringBuilder().append("0000A0").append(MODE_GENERATE).append(keyType).append(keyScheme.getType()).toString();
			final String         response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final GenKeyResponse hsmResponse = new GenKeyResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) {
				final int index = 8 + keyScheme.getLength() * 16 + (keyScheme.getLength() / 2);
				hsmResponse.keyUnderLMK = response.substring(8, index);
				hsmResponse.kcv         = response.substring(index);
			}
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return GenKeyResponse.IO;
	}


	/**
	 * 
	 * @param keyType			: Indicates the key type of the key to be generated.
	 * @param keyScheme			: Indicates the scheme for encrypting the output key under the LMK.
	 * @param keySchemeExport	: Indicates the scheme for encrypting the key under the ZMK (or TMK).
	 * @param masterKeyType		: '0' : ZMK (default value if these fields are not present), '1' : TMK
	 * @param mKeyLmk			: The Zone Master Key (or Terminal Master Key).
	 * @param logger
	 * @return					:  The new key, encrypted under the LMK and provided master key.
	 */
	public final GenKeyResponse generateExportKey(final HSMConfig hsmConfig, final KeyType keyType, final VARIANT keyScheme, final ANSI keySchemeExport,
			final MasterKeyType masterKeyType, final String mKeyLmk, final Logger logger) {
		try {
			final String         mKeyFlag    = masterKeyType == MasterKeyType.ZMK ? "0" : "1";
			final String         command     = new StringBuilder().append("0000A0").append(MODE_GENERATE_EXPORT).append(keyType).append(keyScheme).append(";")
											   .append(mKeyFlag).append(mKeyLmk).append(keySchemeExport).toString();
			final String         response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final GenKeyResponse hsmResponse = new GenKeyResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) {
				final int lKeyIndex = 8 + keyScheme.getLength() * 16 + (keyScheme.getLength() / 2);
				final int mKeyIndex = lKeyIndex + keyScheme.getLength() * 16 + (keyScheme.getLength() / 2);
				hsmResponse.keyUnderLMK = response.substring(8, lKeyIndex);
				hsmResponse.keyUnderMasterKey = response.substring(lKeyIndex, mKeyIndex);
				hsmResponse.kcv         = response.substring(mKeyIndex);
			}
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return GenKeyResponse.IO;
	}

	/**
	 * 
	 * @param iksn		: A Key Set Identifier and Device ID for deriving the Initial Key. Right justified and padded with ”Fs”.
	 * @param bdkType	: This field indicates the DUKPT Master Key Type for deriving the encryption key.
	 * @param bdklmk	: The DUKPT Master Key (BDK).
	 * @param logger	
	 * @return			: The new key, encrypted under the LMK.
	 */
	public final GenKeyResponse deriveIPEK(final HSMConfig hsmConfig, final String iksn, final BDKType bdkType, final String bdklmk, final Logger logger) {
		try {
			final String bdkMode     = bdkType == BDKType.BDK1 ? "1" : "2";
			final String command     = new StringBuilder().append("0000A0").append(MODE_DERIVE).append(KeyType.IPEK)
					.append(KeyScheme.VARIANT.DOUBLE_LEN).append(DERIVE_KEY_MODE_DUKPT).append(bdkMode).append(bdklmk).append(iksn).toString();
			final String response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final GenKeyResponse hsmResponse = new GenKeyResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) {
				hsmResponse.keyUnderLMK = response.substring(8, 41);
				hsmResponse.kcv         = response.substring(41);
			}
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return GenKeyResponse.IO;
	}

	
	/**
	 * 
	 * @param iksn		: A Key Set Identifier and Device ID for deriving the Initial Key. Right justified and padded with ”Fs”.
	 * @param bdkType	: This field indicates the DUKPT Master Key Type for deriving the encryption key.
	 * @param bdklmk	: The DUKPT Master Key (BDK).
	 * @param mKeyType	: '0' : ZMK (default value if these fields are not present), '1' : TMK
	 * @param mKeyLmk	: The Zone Master Key (or Terminal Master Key). 
	 * @param logger
	 * @return			: The new key, encrypted under the LMK and provided master key.
	 */
	public final GenKeyResponse deriveExportIPEK(final HSMConfig hsmConfig, final String iksn, final BDKType bdkType, final String bdklmk, final MasterKeyType mKeyType, final String mKeyLmk, final Logger logger) {
		try {
			final String bdkMode = bdkType == BDKType.BDK1 ? "1" : "2";
			final String mType   = mKeyType == MasterKeyType.ZMK ? "0" : "1";
			final String command = new StringBuilder().append("0000A0").append(MODE_DERIVE_EXPORT).append(KeyType.IPEK).append(VARIANT.DOUBLE_LEN).append(DERIVE_KEY_MODE_DUKPT)
					.append(bdkMode).append(bdklmk).append(iksn).append(";").append(mType).append(mKeyLmk).append(ANSI.DOUBLE_LEN).toString();
			final String         response    = ThalesHSMConnect.send(hsmConfig, command, logger);
			final GenKeyResponse hsmResponse = new GenKeyResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) {
				hsmResponse.keyUnderLMK = response.substring(8, 41);
				hsmResponse.keyUnderMasterKey = response.substring(41, 74);
				hsmResponse.kcv         = response.substring(74);
			}
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return GenKeyResponse.IO;
	}
	

	/**
	 * 
	 * @param keyType	: Indicates the key type of the key to be generated.
	 * @param keyScheme	: Indicates the scheme for encrypting the key under the LMK.
	 * @param logger	
	 * @param keys		: 2-9 Keys
	 * @return
	 */
	public final GenKeyResponse formKeyFromComponents(final HSMConfig hsmConfig, final KeyType keyType, final KeyScheme keyScheme, final Logger logger, final String... keys) {
		try {
			StringBuilder command = new StringBuilder().append("0000A4").append(keys.length).append(keyType).append(keyScheme);
			for (String key : keys) command.append(key);
			final String         response    = ThalesHSMConnect.send(hsmConfig, command.toString(), logger);
			final GenKeyResponse hsmResponse = new GenKeyResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) {
				final int keyLen = keyScheme.getLength() * 16 + (keyScheme.getLength() / 2);
				hsmResponse.keyUnderLMK = response.substring(8, 8 + keyLen);
				hsmResponse.kcv         = response.substring(8 + keyLen);
			}
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return GenKeyResponse.IO;
	}

	/**
	 * 
	 * @param keyType		: Indicates the key type of the key to be imported.
	 * @param importScheme	: Indicates the scheme for encrypting the key under the LMK.
	 * @param keyzmk		: The Key to be imported, encrypted under the ZMK.
	 * @param zmklmk		: The ZMK encrypted under the LMK.
	 * @param logger
	 * @return				: Key will be encrypted under the appropriate LMK pair/variant, determined by the 'Key Type'.
	 */
	public final GenKeyResponse importKey(final HSMConfig hsmConfig, final KeyType keyType, final VARIANT importScheme, final String keyzmk, final String zmklmk, final Logger logger) {
		try {
			String command = new StringBuilder().append("0000A6").append(keyType).append(zmklmk).append(keyzmk).append(importScheme.getType()).toString();
			final String         response    = ThalesHSMConnect.send(hsmConfig, command.toString(), logger);
			final GenKeyResponse hsmResponse = new GenKeyResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) {
				final int keyLen = importScheme.getLength() * 16 + (importScheme.getLength() / 2);
				hsmResponse.keyUnderLMK = response.substring(8, 8 + keyLen);
				hsmResponse.kcv         = response.substring(8 + keyLen);
			}
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return GenKeyResponse.IO;
	}
	
	/**
	 * 
	 * @param sourceKeyType	: Indicates the key type of the key to be exported.
	 * @param keylmk		: Key to be exported; encrypted under the LMK.
	 * @param exportScheme	: Indicates the scheme for encrypting the key under the ZMK (or TMK).
	 * @param mKeyType		: '0' : ZMK (default value if these fields are not present) '1' : TMK
	 * @param mKeylmk		: The Zone Master Key (or Terminal Master Key).
	 * @param logger		
	 * @return				: The key encrypted under ZMK or TMK.
	 */
	public final GenKeyResponse exportKey(final HSMConfig hsmConfig, final KeyType sourceKeyType, final String keylmk, final ANSI exportScheme, final MasterKeyType mKeyType, final String mKeylmk,	final Logger logger) {
		try {
			final String mKeyFlag = mKeyType == MasterKeyType.ZMK ? "0" : "1";
			final String command  = new StringBuilder().append("0000A8").append(sourceKeyType).append(";").append(mKeyFlag).append(mKeylmk).append(keylmk)
									.append(exportScheme).toString();
			final String response = ThalesHSMConnect.send(hsmConfig, command.toString(), logger);
			final GenKeyResponse hsmResponse = new GenKeyResponse(response.substring(6, 8));
			if (hsmResponse.isSuccess) {
				final int keyLen = exportScheme.getLength() * 16 + (exportScheme.getLength() / 2);
				hsmResponse.keyUnderLMK = response.substring(8, 8 + keyLen);
				hsmResponse.kcv         = response.substring(8 + keyLen);
			}
			return hsmResponse;
		} catch (Exception e) {logger.error(e.getMessage());}
		return GenKeyResponse.IO;
	}

}
