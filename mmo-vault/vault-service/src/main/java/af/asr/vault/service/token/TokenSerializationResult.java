package af.asr.vault.service.token;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@SuppressWarnings("WeakerAccess")
public class TokenSerializationResult {
    final private String token;
    final private LocalDateTime expiration;

    public TokenSerializationResult(final String token, final LocalDateTime expiration) {
        this.token = token;
        this.expiration = expiration;
    }

    public TokenSerializationResult(final String token, final Date expiration) {
        this(token, LocalDateTime.ofInstant(expiration.toInstant(), ZoneId.of("UTC")));
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenSerializationResult that = (TokenSerializationResult) o;

        return token.equals(that.token) && expiration.equals(that.expiration);

    }

    @Override
    public int hashCode() {
        int result = token.hashCode();
        result = 31 * result + expiration.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TokenSerializationResult{" +
                "token='" + token + '\'' +
                ", expiration=" + expiration +
                '}';
    }
}