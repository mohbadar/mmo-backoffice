package af.asr.identity.api.v1.events;

import java.util.Objects;


@SuppressWarnings({"WeakerAccess", "unused"})
public class ApplicationCallEndpointSetEvent {
    private String applicationIdentifier;
    private String callEndpointSetIdentifier;

    public ApplicationCallEndpointSetEvent() {
    }

    public ApplicationCallEndpointSetEvent(String applicationIdentifier, String callEndpointSetIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
        this.callEndpointSetIdentifier = callEndpointSetIdentifier;
    }

    public String getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }

    public String getCallEndpointSetIdentifier() {
        return callEndpointSetIdentifier;
    }

    public void setCallEndpointSetIdentifier(String callEndpointSetIdentifier) {
        this.callEndpointSetIdentifier = callEndpointSetIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationCallEndpointSetEvent that = (ApplicationCallEndpointSetEvent) o;
        return Objects.equals(applicationIdentifier, that.applicationIdentifier) &&
                Objects.equals(callEndpointSetIdentifier, that.callEndpointSetIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationIdentifier, callEndpointSetIdentifier);
    }

    @Override
    public String toString() {
        return "ApplicationCallEndpointSetEvent{" +
                "applicationIdentifier='" + applicationIdentifier + '\'' +
                ", callEndpointSetIdentifier='" + callEndpointSetIdentifier + '\'' +
                '}';
    }
}