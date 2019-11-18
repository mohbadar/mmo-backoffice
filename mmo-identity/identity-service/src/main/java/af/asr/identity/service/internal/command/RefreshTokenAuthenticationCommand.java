package af.asr.identity.service.internal.command;

@SuppressWarnings("unused")
public class RefreshTokenAuthenticationCommand {
    private String refreshToken;

    public RefreshTokenAuthenticationCommand() {
    }

    public RefreshTokenAuthenticationCommand(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "RefreshTokenAuthenticationCommand{" +
                "refreshToken='" + refreshToken + '\'' +
                '}';
    }
}