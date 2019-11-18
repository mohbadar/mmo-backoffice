package af.asr.identity.service.internal.command;

@SuppressWarnings("unused")
public class DeleteApplicationCallEndpointSetCommand {
    private String applicationIdentifier;
    private String callEndpointSetIdentifier;

    public DeleteApplicationCallEndpointSetCommand() {
    }

    public DeleteApplicationCallEndpointSetCommand(String applicationIdentifier, String callEndpointSetIdentifier) {
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
    public String toString() {
        return "DeleteApplicationCallEndpointSetCommand{" +
                "applicationIdentifier='" + applicationIdentifier + '\'' +
                ", callEndpointSetIdentifier='" + callEndpointSetIdentifier + '\'' +
                '}';
    }
}
