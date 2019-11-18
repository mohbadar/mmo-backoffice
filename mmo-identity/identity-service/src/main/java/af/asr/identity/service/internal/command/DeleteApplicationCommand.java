package af.asr.identity.service.internal.command;

@SuppressWarnings("unused")
public class DeleteApplicationCommand {
    private String applicationIdentifier;

    public DeleteApplicationCommand() {
    }

    public DeleteApplicationCommand(String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }

    public String getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }

    @Override
    public String toString() {
        return "DeleteApplicationCommand{" +
                "applicationIdentifier='" + applicationIdentifier + '\'' +
                '}';
    }
}