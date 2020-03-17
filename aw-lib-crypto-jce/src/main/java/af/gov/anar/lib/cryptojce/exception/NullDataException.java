
package af.gov.anar.lib.cryptojce.exception;


import af.gov.anar.lang.infrastructure.exception.common.BaseUncheckedException;

/**
 * {@link Exception} to be thrown when data is null
 */
public class NullDataException extends BaseUncheckedException {

	/**
	 * Unique id for serialization
	 */
	private static final long serialVersionUID = 5282175344975485527L;

	/**
	 * Constructor with errorCode and errorMessage
	 * 
	 * @param errorCode    The error code for this exception
	 * @param errorMessage The error message for this exception
	 */
	public NullDataException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

}
