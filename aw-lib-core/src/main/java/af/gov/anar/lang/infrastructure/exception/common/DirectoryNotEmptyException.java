package af.gov.anar.lang.infrastructure.exception.common;

/**
 * Exception to be thrown when a directory exist which is not Empty
 */
public class DirectoryNotEmptyException extends BaseUncheckedException {

	/**
	 * Unique id for serialization
	 */
	private static final long serialVersionUID = -381238520404127950L;

	/**
	 * Constructor with errorCode, errorMessage, and rootCause
	 * 
	 * @param errorCode    The error code for this exception
	 * @param errorMessage The error message for this exception
	 * @param cause        Cause of this exception
	 */
	public DirectoryNotEmptyException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
	}

}
