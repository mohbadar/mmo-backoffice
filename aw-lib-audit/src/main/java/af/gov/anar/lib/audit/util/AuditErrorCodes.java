package af.gov.anar.lib.audit.util;

/**
 * Constants for Audit Manager
 */
public enum AuditErrorCodes {
	HANDLEREXCEPTION("ANAR-AUD-001", "Invalid Audit Request. Required parameters must be present"),

	INVALIDFORMAT("ANAR-AUD-002", "Invalid Audit Request. Format is incorrect. (For timestamp, use UTC format)");

	private final String errorCode;
	private final String errorMessage;

	private AuditErrorCodes(final String errorCode, final String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
