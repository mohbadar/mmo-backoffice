package af.gov.anar.lib.cryptojce.util;

public enum SecurityExceptionCodeConstant {
    ANAR_INVALID_KEY_EXCEPTION("ANAR-FSE-001",
            "invalid Key (key is null or empty or has invalid encoding, wronglength, and uninitialized, etc)."),
    ANAR_INVALID_DATA_LENGTH_EXCEPTION("ANAR-FSE-002", "check input data length"),
    ANAR_INVALID_DATA_EXCEPTION("ANAR-FSE-003", "data not valid (currupted,length is not valid etc.)"),
    ANAR_INVALID_ENCRYPTED_DATA_CORRUPT_EXCEPTION("ANAR-FSE-004", "encrypted data is corrupted"),
    ANAR_INVALID_DATA_SIZE_EXCEPTION("ANAR-FSE-005", "ecrypted data size is not valid"),
    ANAR_NULL_DATA_EXCEPTION("ANAR-FSE-006", "data is null or length is 0"),
    ANAR_NULL_METHOD_EXCEPTION("ANAR-FSE-007", "ANAR security method is null"),
    ANAR_NO_SUCH_ALGORITHM_EXCEPTION("ANAR-FSE-008", "no such algorithm"),
    ANAR_INVALID_PARAM_SPEC_EXCEPTION("ANAR-FSE-009", "invalid param spec"),
    ANAR_SIGNATURE_EXCEPTION("ANAR-FSE-010", "invalid signature, maybe null or empty"),
    SALT_PROVIDED_IS_NULL_OR_EMPTY("ANAR-FSE-011", "salt provided is null or empty");

    /**
     * Constant {@link Enum} errorCode
     */
    private final String errorCode;

    /**
     * Getter for errorMessage
     *
     * @return get errorMessage value
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Constant {@link Enum} errorMessage
     */
    private final String errorMessage;

    /**
     * Constructor for this class
     *
     * @param value set {@link Enum} value
     */
    private SecurityExceptionCodeConstant(final String errorCode, final String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Getter for errorCode
     *
     * @return get errorCode value
     */
    public String getErrorCode() {
        return errorCode;
    }
}
