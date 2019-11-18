package af.asr.identity.api.v1.events;

import java.util.Objects;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ApplicationSignatureEvent {
    private String applicationIdentifier;
    private String keyTimestamp;

    public ApplicationSignatureEvent() {
    }

    public ApplicationSignatureEvent(String applicationIdentifier, String keyTimestamp) {
        this.applicationIdentifier = applicationIdentifier;
        this.keyTimestamp = keyTimestamp;
    }

    public String getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }

    public String getKeyTimestamp() {
        return keyTimestamp;
    }

    public void setKeyTimestamp(String keyTimestamp) {
        this.keyTimestamp = keyTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationSignatureEvent that = (ApplicationSignatureEvent) o;
        return Objects.equals(applicationIdentifier, that.applicationIdentifier) &&
                Objects.equals(keyTimestamp, that.keyTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationIdentifier, keyTimestamp);
    }

    @Override
    public String toString() {
        return "ApplicationSignatureEvent{" +
                "applicationIdentifier='" + applicationIdentifier + '\'' +
                ", keyTimestamp='" + keyTimestamp + '\'' +
                '}';
    }
}