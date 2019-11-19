package af.asr.vault.api.domain;


import java.util.Objects;
import java.util.Set;

@SuppressWarnings({"WeakerAccess", "unused"})
public class TokenPermission {
    private String path;
    private Set<AllowedOperation> allowedOperations;

    public TokenPermission() {
    }

    public TokenPermission(String path, Set<AllowedOperation> allowedOperations) {
        this.path = path;
        this.allowedOperations = allowedOperations;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<AllowedOperation> getAllowedOperations() {
        return allowedOperations;
    }

    public void setAllowedOperations(Set<AllowedOperation> allowedOperations) {
        this.allowedOperations = allowedOperations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenPermission that = (TokenPermission) o;
        return Objects.equals(path, that.path) &&
                Objects.equals(allowedOperations, that.allowedOperations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, allowedOperations);
    }

    @Override
    public String toString() {
        return "TokenPermission{" +
                "path='" + path + '\'' +
                ", allowedOperations=" + allowedOperations +
                '}';
    }
}