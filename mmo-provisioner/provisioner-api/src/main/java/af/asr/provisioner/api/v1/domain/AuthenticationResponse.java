package af.asr.provisioner.api.v1.domain;

@SuppressWarnings("unused")
public class AuthenticationResponse {

    private final String token;
    private final String accessTokenExpiration;

    public AuthenticationResponse(final String token, final String accessTokenExpiration) {
        super();
        this.token = token;
        this.accessTokenExpiration = accessTokenExpiration;
    }

    public String getToken() {
        return token;
    }

    public String getAccessTokenExpiration() {
        return accessTokenExpiration;
    }
}