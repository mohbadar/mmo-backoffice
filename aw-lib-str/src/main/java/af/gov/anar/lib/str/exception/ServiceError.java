package af.gov.anar.lib.str.exception;

import lombok.Data;

@Data
public class ServiceError {

	/**
	 * The error code.
	 */
	private String errorCode;
	/**
	 * The error message.
	 */
	private String message;

	/**
	 * Constructor for ErrorBean.
	 * 
	 * @param errorCode    The error code.
	 * @param errorMessage The error message.
	 */
	public ServiceError(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.message = errorMessage;
	}

	public ServiceError() {

	}

}
