package af.asr.vault.service.security;


import java.util.Objects;


@SuppressWarnings("unused")
public class AnubisPrincipal {
    private final String user;
    private final String forApplicationName;
    private final String sourceApplicationName;

    AnubisPrincipal(String user, String forApplicationName, String sourceApplicationName) {
        this.user = user;
        this.forApplicationName = forApplicationName;
        this.sourceApplicationName = sourceApplicationName;
    }

    public String getUser() {
        return user;
    }

    public String getForApplicationName() {
        return forApplicationName;
    }

    public String getSourceApplicationName() {
        return sourceApplicationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnubisPrincipal that = (AnubisPrincipal) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(forApplicationName, that.forApplicationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, forApplicationName);
    }

    @Override
    public String toString() {
        return "AnubisPrincipal{" +
                "user='" + user + '\'' +
                ", forApplicationName='" + forApplicationName + '\'' +
                '}';
    }
}