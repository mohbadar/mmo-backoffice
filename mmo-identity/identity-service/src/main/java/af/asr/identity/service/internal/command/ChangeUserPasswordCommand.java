package af.asr.identity.service.internal.command;

@SuppressWarnings("unused")
public class ChangeUserPasswordCommand {
    private String identifier;

    //transient to ensure this field doesn't land in the audit log.
    private transient String password;

    public ChangeUserPasswordCommand() {
    }

    public ChangeUserPasswordCommand(final String identifier, final String password) {
        this.identifier = identifier;
        this.password = password;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ChangeUserPasswordCommand{" +
                "identifier='" + identifier + '\'' +
                '}';
    }
}