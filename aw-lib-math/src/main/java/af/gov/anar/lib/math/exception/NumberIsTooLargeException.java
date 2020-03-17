package af.gov.anar.lib.math.exception;

import af.gov.anar.lang.infrastructure.exception.common.BaseUncheckedException;

/**
 * Exception to be thrown when a number is too large.
 */
public class NumberIsTooLargeException extends BaseUncheckedException {

	/** Serializable version Id. */
	private static final long serialVersionUID = 424322202100630614L;

	/**
	 * @param arg0 Error Code Corresponds to Particular Exception
	 * @param arg1 Message providing the specific context of the error.
	 * @param arg2 Cause of exception
	 */
	public NumberIsTooLargeException(String arg0, String arg1, Throwable arg2) {
		super(arg0, arg1, arg2);
	}

}
