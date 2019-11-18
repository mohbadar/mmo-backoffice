package af.asr.identity.service.internal.command;

import af.asr.identity.api.v1.domain.UserWithPassword;

@SuppressWarnings("unused")
public class CreateUserCommand {
    private String identifier;
    private String role;

    //transient to ensure this field doesn't land in the audit log.
    private transient String password;

    public CreateUserCommand() {
    }

    public CreateUserCommand(final UserWithPassword instance) {
        this.identifier = instance.getIdentifier();
        this.role = instance.getRole();
        this.password = instance.getPassword();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CreateUserCommand{" +
                "identifier='" + identifier + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}