package af.asr.provisioner.api.v1.domain;

@SuppressWarnings("unused")
public final class ClientCredentials {

    private final String id;
    private final String secret;

    public ClientCredentials(String id, String secret) {
        super();
        this.id = id;
        this.secret = secret;
    }

    public String getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }
}