package af.gov.anar.lib.str.exception;

public class DataFormatException extends IOException {

	private static final long serialVersionUID = -1762806620894866489L;

	public DataFormatException(String errorCode, String errorMessage, Throwable rootCause) {
		super(errorCode, errorMessage, rootCause);

	}

	public DataFormatException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);

	}

}
