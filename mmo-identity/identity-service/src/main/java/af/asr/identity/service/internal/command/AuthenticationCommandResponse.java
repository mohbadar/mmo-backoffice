package af.asr.identity.service.internal.command;
import java.util.Objects;

public class AuthenticationCommandResponse {
    private String accessToken;
    private String accessTokenExpiration;
    private String refreshToken;
    private String refreshTokenExpiration;
    private String passwordExpiration;

    public AuthenticationCommandResponse() {
    }

    public AuthenticationCommandResponse(String accessToken, String accessTokenExpiration, String refreshToken, String refreshTokenExpiration, String passwordExpiration) {
        this.accessToken = accessToken;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiration = refreshTokenExpiration;
        this.passwordExpiration = passwordExpiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    public void setAccessTokenExpiration(String accessTokenExpiration) {
        this.accessTokenExpiration = accessTokenExpiration;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshTokenExpiration() {
        return refreshTokenExpiration;
    }

    public void setRefreshTokenExpiration(String refreshTokenExpiration) {
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    public String getPasswordExpiration() {
        return passwordExpiration;
    }

    public void setPasswordExpiration(String passwordExpiration) {
        this.passwordExpiration = passwordExpiration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationCommandResponse that = (AuthenticationCommandResponse) o;
        return Objects.equals(accessToken, that.accessToken) &&
                Objects.equals(accessTokenExpiration, that.accessTokenExpiration) &&
                Objects.equals(refreshToken, that.refreshToken) &&
                Objects.equals(refreshTokenExpiration, that.refreshTokenExpiration) &&
                Objects.equals(passwordExpiration, that.passwordExpiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, accessTokenExpiration, refreshToken, refreshTokenExpiration, passwordExpiration);
    }

    @Override
    public String toString() {
        return "AuthenticationCommandResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", accessTokenExpiration='" + accessTokenExpiration + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", refreshTokenExpiration='" + refreshTokenExpiration + '\'' +
                ", passwordExpiration='" + passwordExpiration + '\'' +
                '}';
    }
}
