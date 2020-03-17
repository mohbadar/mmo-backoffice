package af.gov.anar.lib.audit.util;

/**
 * Constants for Applicant API related errors.
 *
 */
public enum ApplicantTypeErrorCode {

	INVALID_QUERY_EXCEPTION("KER-MSD-147", "Invalid query passed for applicant type"),
	INVALID_DATE_STRING_EXCEPTION("KER-MSD-148", "Date string can not be parsed");

	private final String errorCode;
	private final String errorMessage;

	private ApplicantTypeErrorCode(final String errorCode, final String errorMessage) {
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