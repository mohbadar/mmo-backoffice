package af.asr.vault.api.domain;


import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Objects;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Signature {
    @NotNull
    private BigInteger publicKeyMod;

    @NotNull
    private BigInteger publicKeyExp;

    public Signature() {
    }

    public Signature(final BigInteger publicKeyMod, final BigInteger publicKeyExp) {
        this.publicKeyMod = publicKeyMod;
        this.publicKeyExp = publicKeyExp;
    }

    public BigInteger getPublicKeyMod() {
        return publicKeyMod;
    }

    public void setPublicKeyMod(BigInteger publicKeyMod) {
        this.publicKeyMod = publicKeyMod;
    }

    public BigInteger getPublicKeyExp() {
        return publicKeyExp;
    }

    public void setPublicKeyExp(BigInteger publicKeyExp) {
        this.publicKeyExp = publicKeyExp;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Signature))
            return false;
        Signature signature = (Signature) o;
        return Objects.equals(publicKeyMod, signature.publicKeyMod) && Objects
                .equals(publicKeyExp, signature.publicKeyExp);
    }

    @Override public int hashCode() {
        return Objects.hash(publicKeyMod, publicKeyExp);
    }

    @Override public String toString() {
        return "Signature{" +
                "publicKeyMod=" + publicKeyMod +
                ", publicKeyExp=" + publicKeyExp +
                '}';
    }
}