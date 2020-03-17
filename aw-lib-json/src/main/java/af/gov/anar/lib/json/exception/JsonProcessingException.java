package af.gov.anar.lib.json.exception;


import af.gov.anar.lang.infrastructure.exception.common.BaseCheckedException;

public class JsonProcessingException extends BaseCheckedException {
	private static final long serialVersionUID = 7784354823823721387L;

	public JsonProcessingException(String errorCode, String errorMessage, Throwable rootCause) {
		super(errorCode, errorMessage, rootCause);

	}

	public JsonProcessingException(String string) {

	}

}
