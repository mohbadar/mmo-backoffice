package af.gov.anar.lib.str.util;

/**
 * Defines constants used in StringUtil.class
 */
public enum StringUtilConstants {

	ANAR_ARRAY_INDEX_OUT_OF_BOUNDS_ERROR_CODE("CORE-UTL-501", "Array Index out of bounds"),
	ANAR_PATTERN_SYNTAX_ERROR_CODE("CORE-UTL-503", "Pattern Syntax Exception"),
	ANAR_ILLEGAL_ARGUMENT_ERROR_CODE("CORE-UTL-502", "Illegal Argument Exception");
	public final String errorCode;
	public final String errorMessage;

	StringUtilConstants(String string1, String string2) {
		this.errorCode = string1;
		this.errorMessage = string2;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
