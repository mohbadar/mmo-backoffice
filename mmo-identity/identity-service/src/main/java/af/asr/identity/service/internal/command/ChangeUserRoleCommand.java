package af.asr.identity.service.internal.command;

@SuppressWarnings("unused")
public class ChangeUserRoleCommand {
    private String identifier;
    private String role;

    public ChangeUserRoleCommand() {
    }

    public ChangeUserRoleCommand(final String identifier, final String role) {
        this.identifier = identifier;
        this.role = role;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ChangeUserRoleCommand{" +
                "identifier='" + identifier + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}