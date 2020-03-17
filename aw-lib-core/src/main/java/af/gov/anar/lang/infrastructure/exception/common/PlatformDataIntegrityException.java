
package af.gov.anar.lang.infrastructure.exception.common;

/**
 * A {@link RuntimeException} thrown when data integrity problems happen due to
 * state modifying actions.
 */
public class PlatformDataIntegrityException extends RuntimeException {

    private final String globalisationMessageCode;
    private final String defaultUserMessage;
    private final String parameterName;
    private final Object[] defaultUserMessageArgs;

    public PlatformDataIntegrityException(final String globalisationMessageCode, final String defaultUserMessage,
            final Object... defaultUserMessageArgs) {
        this.globalisationMessageCode = globalisationMessageCode;
        this.defaultUserMessage = defaultUserMessage;
        this.parameterName = null;
        this.defaultUserMessageArgs = defaultUserMessageArgs;
    }

    public PlatformDataIntegrityException(final String globalisationMessageCode, final String defaultUserMessage,
            final String parameterName, final Object... defaultUserMessageArgs) {
        this.globalisationMessageCode = globalisationMessageCode;
        this.defaultUserMessage = defaultUserMessage;
        this.parameterName = parameterName;
        this.defaultUserMessageArgs = defaultUserMessageArgs;
    }

    public String getGlobalisationMessageCode() {
        return this.globalisationMessageCode;
    }

    public String getDefaultUserMessage() {
        return this.defaultUserMessage;
    }

    public Object[] getDefaultUserMessageArgs() {
        return this.defaultUserMessageArgs;
    }

    public String getParameterName() {
        return this.parameterName;
    }
}