package af.gov.anar.lib.hsm.service;

import af.asr.lib.hsm.constants.*;
import af.asr.lib.hsm.constants.KeyScheme.ANSI;
import af.asr.lib.hsm.constants.KeyScheme.VARIANT;
import af.asr.lib.hsm.model.GenKeyResponse;
import af.asr.lib.hsm.model.HSMResponse;
import org.slf4j.Logger;

public interface KeyService {

	//@formatter:off
	public abstract HSMResponse calculateKCV(final HSMConfig hsmConfig, final String key, final KeyLength keyLength, final KeyType keyType, final Logger logger);
	
	
	/**
	 * @param keyType	: Indicates the key type of the key to be generated.
	 * @param keyScheme	: Indicates the scheme for encrypting the output key under the LMK.
	 * @param logger
	 * @return			: The new key, encrypted under the LMK.
	 */
	public abstract GenKeyResponse generateKey(final HSMConfig hsmConfig, final KeyType keyType, final KeyScheme.VARIANT keyScheme, final Logger logger);

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
	public abstract GenKeyResponse generateExportKey(final HSMConfig hsmConfig, final KeyType keyType, final VARIANT keyScheme, final ANSI keySchemeExport,
                                                     final MasterKeyType masterKeyType, final String mKeyLmk, final Logger logger);

	/**
	 * 
	 * @param iksn		: A Key Set Identifier and Device ID for deriving the Initial Key. Right justified and padded with ”Fs”.
	 * @param bdkType	: This field indicates the DUKPT Master Key Type for deriving the encryption key.
	 * @param bdklmk	: The DUKPT Master Key (BDK).
	 * @param logger	
	 * @return			: The new key, encrypted under the LMK.
	 */
	public abstract GenKeyResponse deriveIPEK(final HSMConfig hsmConfig, final String iksn, final BDKType bdkType, final String bdklmk, final Logger logger) ;

	
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
	public abstract GenKeyResponse deriveExportIPEK(final HSMConfig hsmConfig, final String iksn, final BDKType bdkType, final String bdklmk, final MasterKeyType mKeyType, final String mKeyLmk, final Logger logger); 	

	/**
	 * 
	 * @param keyType	: Indicates the key type of the key to be generated.
	 * @param keyScheme	: Indicates the scheme for encrypting the key under the LMK.
	 * @param logger	
	 * @param keys		: 2-9 Keys
	 * @return
	 */
	public abstract GenKeyResponse formKeyFromComponents(final HSMConfig hsmConfig, final KeyType keyType, final KeyScheme keyScheme, final Logger logger, final String... keys);

	/**
	 * 
	 * @param keyType		: Indicates the key type of the key to be imported.
	 * @param importScheme	: Indicates the scheme for encrypting the key under the LMK.
	 * @param keyzmk		: The Key to be imported, encrypted under the ZMK.
	 * @param zmklmk		: The ZMK encrypted under the LMK.
	 * @param logger
	 * @return				: Key will be encrypted under the appropriate LMK pair/variant, determined by the 'Key Type'.
	 */
	public abstract GenKeyResponse importKey(final HSMConfig hsmConfig, final KeyType keyType, final VARIANT importScheme, final String keyzmk, final String zmklmk, final Logger logger) ;
	
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
	public abstract GenKeyResponse exportKey(final HSMConfig hsmConfig, final KeyType sourceKeyType, final String keylmk, final ANSI exportScheme, final MasterKeyType mKeyType, final String mKeylmk, final Logger logger);
		

}
