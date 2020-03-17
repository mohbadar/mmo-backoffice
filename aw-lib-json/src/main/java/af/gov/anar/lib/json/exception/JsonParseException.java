package af.gov.anar.lib.json.exception;


import af.gov.anar.lang.infrastructure.exception.common.BaseCheckedException;

public class JsonParseException extends BaseCheckedException {
	private static final long serialVersionUID = 7469054823823721387L;

	public JsonParseException(String errorCode, String errorMessage, Throwable rootCause) {
		super(errorCode, errorMessage, rootCause);

	}

}
