package af.gov.anar.api.handler.enumeration;



public enum ErrorCodes {

    Init_001("Init_001", "Error while establishing connection with Resources");

    private final String code;
    private final String description;

    private ErrorCodes(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
