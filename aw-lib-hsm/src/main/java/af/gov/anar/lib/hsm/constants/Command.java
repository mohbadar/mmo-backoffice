package af.gov.anar.lib.hsm.constants;

/**
 * This class contains all command implementated for Thales Payshield 9000 HSM.
 */
public class Command {
	
	public static final String ENCRYPT_PIN_LMK 							= "BA";
	public static final String DECRYPT_PIN_LMK 							= "NG";
	
	
	public static final String GENERATE_IBM_OFFSET_USING_PIN			= "DE";
	public static final String GENERATE_IBM_OFFSET_USING_PINBLOCK 		= "BK";
	public static final String VALIDATE_AND_GENERATE_IBM_OFFSET 		= "DU";
	public static final String VALIDATE_TERMINAL_PIN_IBM_OFFSET 		= "DA";
	public static final String VALIDATE_INTERCHANGE_PIN_IBM_OFFSET 		= "EA";
	public static final String VALIDATE_DUKPT_PIN_IBM_OFFSET 			= "GO";
	
}
