
package af.gov.anar.lib.cryptojce.exception;


import af.gov.anar.lang.infrastructure.exception.common.BaseUncheckedException;

/**
 * {@link Exception} to be thrown when key is null
 */
public class NullMethodException extends BaseUncheckedException {

	/**
	 * Unique id for serialization
	 */
	private static final long serialVersionUID = -7652091398077351394L;

	/**
	 * Constructor with errorCode and errorMessage
	 * 
	 * @param errorCode    The error code for this exception
	 * @param errorMessage The error message for this exception
	 */

	public NullMethodException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

}