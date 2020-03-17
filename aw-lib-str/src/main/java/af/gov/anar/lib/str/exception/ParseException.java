package af.gov.anar.lib.str.exception;

/**
 * Signals that an error has been reached unexpectedly while parsing.
 */
public class ParseException extends BaseUncheckedException {
	private static final long serialVersionUID = 924722202110630628L;

	public ParseException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);

	}

	public ParseException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);

	}

}
