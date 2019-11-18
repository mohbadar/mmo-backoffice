package af.asr.identity.service.internal.command;

import af.asr.identity.api.v1.domain.CallEndpointSet;

@SuppressWarnings("unused")
public class ChangeApplicationCallEndpointSetCommand {
    private String applicationIdentifier;
    private String callEndpointSetIdentifier;
    private CallEndpointSet callEndpointSet;

    public ChangeApplicationCallEndpointSetCommand() {
    }

    public ChangeApplicationCallEndpointSetCommand(
            String applicationIdentifier,
            String callEndpointSetIdentifier,
            CallEndpointSet callEndpointSet) {
        this.applicationIdentifier = applicationIdentifier;
        this.callEndpointSetIdentifier = callEndpointSetIdentifier;
        this.callEndpointSet = callEndpointSet;
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

    public CallEndpointSet getCallEndpointSet() {
        return callEndpointSet;
    }

    public void setCallEndpointSet(CallEndpointSet callEndpointSet) {
        this.callEndpointSet = callEndpointSet;
    }

    @Override
    public String toString() {
        return "ChangeApplicationCallEndpointSetCommand{" +
                "applicationIdentifier='" + applicationIdentifier + '\'' +
                ", callEndpointSetIdentifier='" + callEndpointSetIdentifier + '\'' +
                '}';
    }
}