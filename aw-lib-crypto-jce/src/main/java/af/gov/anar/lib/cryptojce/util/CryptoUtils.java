package af.gov.anar.lib.cryptojce.util;

import af.gov.anar.lib.cryptojce.exception.InvalidDataException;
import af.gov.anar.lib.cryptojce.exception.NullDataException;

public class CryptoUtils {

    /**
     * Constructor for this class
     */
    private CryptoUtils() {

    }

    /**
     * Verify if data is null or empty
     *
     * @param      <T>
     *
     * @param data data provided by user
     */
    public static void verifyData(byte[] data) {
        if (data == null) {
            throw new NullDataException(SecurityExceptionCodeConstant.ANAR_NULL_DATA_EXCEPTION.getErrorCode(),
                    SecurityExceptionCodeConstant.ANAR_NULL_DATA_EXCEPTION.getErrorMessage());
        } else if (data.length == 0) {
            throw new InvalidDataException(SecurityExceptionCodeConstant.ANAR_NULL_DATA_EXCEPTION.getErrorCode(),
                    SecurityExceptionCodeConstant.ANAR_NULL_DATA_EXCEPTION.getErrorMessage());
        }
    }

    /**
     * Verify if data is null or empty
     *
     * @param data data provided by user
     */
    public static void verifyData(byte[] data, String errorCode, String message) {
        if (data == null) {
            throw new NullDataException(errorCode, message);
        } else if (data.length == 0) {
            throw new InvalidDataException(errorCode, message);
        }
    }
}
