package af.gov.anar.lib.json.util;

/**
 * This enum contains all the error codes and messages of Jsonutil class
 */
public enum JsonUtilConstants {

	ANAR_IO_EXCEPTION_ERROR_CODE("ANAR-UTL-101", "File not found"),
	ANAR_JSON_GENERATION_ERROR_CODE("ANAR-UTL-102", "Json not generated successfully"),
	ANAR_JSON_MAPPING_ERROR_CODE("ANAR-UTL-103", "Json mapping Exception"),
	ANAR_JSON_PARSE_ERROR_CODE("ANAR-UTL-104", "Json not parsed successfully"),
	ANAR_JSON_PROCESSING_EXCEPTION("ANAR-UTL-105", "json not processed successfully");
	public final String errorCode;
	public final String errorMessage;

	JsonUtilConstants(String string1, String string2) {
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
