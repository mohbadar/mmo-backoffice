package af.asr.command.annotation;

public enum CommandLogLevel {

    INFO("INFO"), DEBUG("DEBUG"), TRACE("TRACE"), NONE("TRACE");

    private final String strVal;

    CommandLogLevel(final String strVal) {
        this.strVal = strVal;
    }
    public String toString() {
        return strVal;
    }
}