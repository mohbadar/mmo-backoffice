package af.asr.vault.service.token;


import java.util.Date;

@SuppressWarnings("WeakerAccess")
public class TokenDeserializationResult {
    final private String userIdentifier;
    final private Date expiration;
    final private String sourceApplication;
    final private String endpointSet;

    public TokenDeserializationResult(
            final String userIdentifier,
            final Date expiration,
            final String sourceApplication,
            final String endpointSet) {
        this.userIdentifier = userIdentifier;
        this.expiration = expiration;
        this.sourceApplication = sourceApplication;
        this.endpointSet = endpointSet;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public Date getExpiration() {
        return expiration;
    }

    public String getSourceApplication() {
        return sourceApplication;
    }

    public String getEndpointSet() {
        return endpointSet;
    }
}