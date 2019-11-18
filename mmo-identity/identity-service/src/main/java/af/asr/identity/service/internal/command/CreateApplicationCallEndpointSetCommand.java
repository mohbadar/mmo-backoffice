package af.asr.identity.service.internal.command;

import af.asr.identity.api.v1.domain.CallEndpointSet;

@SuppressWarnings("unused")
public class CreateApplicationCallEndpointSetCommand {
    private String applicationIdentifier;
    private CallEndpointSet callEndpointSet;

    public CreateApplicationCallEndpointSetCommand() {
    }

    public CreateApplicationCallEndpointSetCommand(String applicationIdentifier, CallEndpointSet callEndpointSet) {
        this.applicationIdentifier = applicationIdentifier;
        this.callEndpointSet = callEndpointSet;
    }

    public String getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }

    public CallEndpointSet getCallEndpointSet() {
        return callEndpointSet;
    }

    public void setEndpointSet(CallEndpointSet callEndpointSet) {
        this.callEndpointSet = callEndpointSet;
    }

    @Override
    public String toString() {
        return "CreateApplicationCallEndpointSetCommand{" +
                "applicationIdentifier='" + applicationIdentifier + '\'' +
                ", callEndpointSet=" + callEndpointSet.getIdentifier() +
                '}';
    }
}