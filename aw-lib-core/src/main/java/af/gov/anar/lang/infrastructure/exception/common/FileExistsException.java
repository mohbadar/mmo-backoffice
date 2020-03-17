package af.gov.anar.lang.infrastructure.exception.common;


public class FileExistsException extends IOException {

	private static final long serialVersionUID = 2842522173494167519L;

	public FileExistsException(String errorCode, String errorMessage, Throwable rootCause) {
		super(errorCode, errorMessage, rootCause);

	}

	public FileExistsException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);

	}

}
