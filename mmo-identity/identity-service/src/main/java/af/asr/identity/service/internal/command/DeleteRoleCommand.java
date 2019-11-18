package af.asr.identity.service.internal.command;

@SuppressWarnings("unused")
public class DeleteRoleCommand {
    private String identifier;

    public DeleteRoleCommand() {
    }

    public DeleteRoleCommand(final String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "DeleteRoleCommand{" +
                "identifier='" + identifier + '\'' +
                '}';
    }
}