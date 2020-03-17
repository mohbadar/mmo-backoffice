
package af.gov.anar.lang.infrastructure.exception.common;

public class UnrecognizedQueryParamException extends RuntimeException {

    private final String queryParamKey;
    private final String queryParamValue;
    private final Object[] supportedParams;

    public UnrecognizedQueryParamException(final String queryParamKey, final String queryParamValue, final Object... supportedParams) {
        this.queryParamKey = queryParamKey;
        this.queryParamValue = queryParamValue;
        this.supportedParams = supportedParams;
    }

    public String getQueryParamKey() {
        return this.queryParamKey;
    }

    public String getQueryParamValue() {
        return this.queryParamValue;
    }

    public Object[] getSupportedParams() {
        return this.supportedParams;
    }
}