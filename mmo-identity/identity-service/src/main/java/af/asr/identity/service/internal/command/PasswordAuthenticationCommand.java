package af.asr.identity.service.internal.command;

@SuppressWarnings("unused")
public class PasswordAuthenticationCommand {
    private String useridentifier;
    private transient String password;

    PasswordAuthenticationCommand() {}

    public PasswordAuthenticationCommand(
            final String useridentifier,
            final String password) {
        this.useridentifier = useridentifier;
        this.password = password;
    }

    public String getUseridentifier() {
        return useridentifier;
    }

    public void setUseridentifier(String useridentifier) {
        this.useridentifier = useridentifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PasswordAuthenticationCommand{" +
                "useridentifier='" + useridentifier + '\'' +
                '}';
    }
}