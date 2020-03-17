package af.gov.anar.lang.infrastructure.exception.common;

public class FileNotFoundException extends IOException {

	private static final long serialVersionUID = -1762806620894866489L;

	public FileNotFoundException(String errorCode, String errorMessage, Throwable rootCause) {
		super(errorCode, errorMessage, rootCause);

	}

	public FileNotFoundException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);

	}

}
