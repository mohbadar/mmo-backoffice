package af.gov.anar.lang.infrastructure.exception.common;

/**
 * Base class for all preconditions violation exceptions.
 */
public class IllegalArgumentException extends BaseUncheckedException {
	/** Serializable version Id. */
	private static final long serialVersionUID = 924722202110630628L;

	public IllegalArgumentException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);

	}

	public IllegalArgumentException(String errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);

	}

}
