package af.gov.anar.lib.hash.util;

public enum HashUtilConstants {
	ANAR_ILLEGAL_ARGUMENT_INITIALODDNUMBER_ERROR_CODE("ANAR-UTL-201", "Entered initialOddNumber is even"),
	ANAR_ILLEGAL_ARGUMENT_MULTIPLIERODDNUMBER_ERROR_CODE("ANAR-UTL-202", "Entered multiplierOddNumber is even");

	public final String errorCode;
	public final String errorMessage;

	HashUtilConstants(String string1, String string2) {
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
