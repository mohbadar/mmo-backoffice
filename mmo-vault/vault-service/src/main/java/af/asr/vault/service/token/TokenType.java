package af.asr.vault.service.token;


import java.util.Optional;

public enum TokenType {
    SYSTEM("system"), TENANT("tenant"), ;

    private final String issuer;

    TokenType(final String issuer) {
        this.issuer = issuer;
    }

    public static Optional<TokenType> valueOfIssuer(final String issuer)
    {
        if (issuer.equals(SYSTEM.issuer))
            return Optional.of(SYSTEM);
        else if (issuer.equals(TENANT.issuer))
            return Optional.of(TENANT);
        else
            return Optional.empty();
    }

    public String getIssuer() {
        return issuer;
    }
}