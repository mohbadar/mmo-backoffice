package af.gov.anar.lib.audit.util;

public enum AuditEventType {


    USER_EVENT("USER"),
    SYSTEM_EVENT("SYSTEM");

    /**
     * @param code
     */
    private AuditEventType(String code) {
        this.code = code;
    }

    private final String code;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

}
