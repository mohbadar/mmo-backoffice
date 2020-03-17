package af.gov.anar.lib.audit.exception.dataaccess;


import af.gov.anar.lang.infrastructure.exception.common.BaseUncheckedException;

/**
 * Custom class for DataAccessLayerException
 *
 */
public class DataAccessLayerException extends BaseUncheckedException {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 5074628123959874252L;

	/**
	 * Constructor for DataAccessLayerException
	 * 
	 * @param errorCode    The errorcode
	 * @param errorMessage The errormessage
	 * @param cause        The cause
	 */
	public DataAccessLayerException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
	}
}
