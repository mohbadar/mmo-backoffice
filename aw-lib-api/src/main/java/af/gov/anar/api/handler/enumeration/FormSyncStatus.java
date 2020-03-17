package af.gov.anar.api.handler.enumeration;


public enum FormSyncStatus {

    SUCCESSFUL("SUCCESSFUL"),
    FAILED("FAILED");

    private String text;

    FormSyncStatus(final String text)
    {
        this.text =text;
    }

    @Override
    public String toString() {
        return text;
    }
}