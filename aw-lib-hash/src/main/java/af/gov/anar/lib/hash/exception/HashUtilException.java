package af.gov.anar.lib.hash.exception;


import af.gov.anar.lang.infrastructure.exception.common.BaseCheckedException;

/**
 * HashUtilException class wraps the Java BaseCheckedException class to provide
 * checkedexception code for HashUtil
 */
public class HashUtilException extends BaseCheckedException {
	/** Serializable version Id. */
	private static final long serialVersionUID = 924722202110630628L;

	/**
	 * @param arg0 Error Code Corresponds to Particular Exception
	 * @param arg1 Message providing the specific context of the error.
	 */
	public HashUtilException(String arg0, String arg1) {
		super(arg0, arg1);

	}
}
