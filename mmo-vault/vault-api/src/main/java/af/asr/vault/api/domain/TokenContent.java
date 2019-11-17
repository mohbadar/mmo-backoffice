package af.asr.vault.api.domain;


import java.util.List;
import java.util.Objects;

@SuppressWarnings({"unused", "WeakerAccess"})
public class TokenContent {

    private List<TokenPermission> tokenPermissions;

    public TokenContent() {
    }

    public TokenContent(List<TokenPermission> tokenPermissions) {
        this.tokenPermissions = tokenPermissions;
    }

    public List<TokenPermission> getTokenPermissions() {
        return tokenPermissions;
    }

    public void setTokenPermissions(List<TokenPermission> tokenPermissions) {
        this.tokenPermissions = tokenPermissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenContent that = (TokenContent) o;
        return Objects.equals(tokenPermissions, that.tokenPermissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenPermissions);
    }

    @Override
    public String toString() {
        return "TokenContent{" +
                "tokenPermissions=" + tokenPermissions +
                '}';
    }
}