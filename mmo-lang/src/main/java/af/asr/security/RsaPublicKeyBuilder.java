package af.asr.security;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

@SuppressWarnings("WeakerAccess")
public class RsaPublicKeyBuilder {

    private BigInteger publicKeyMod;
    private BigInteger publicKeyExp;

    public RsaPublicKeyBuilder setPublicKeyMod(final BigInteger publicKeyMod) {
        this.publicKeyMod = publicKeyMod;
        return this;
    }

    public RsaPublicKeyBuilder setPublicKeyExp(final BigInteger publicKeyExp) {
        this.publicKeyExp = publicKeyExp;
        return this;
    }

    public PublicKey build() {
        try {
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            final RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(publicKeyMod, publicKeyExp);
            return keyFactory.generatePublic(rsaPublicKeySpec);
        } catch (final NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("Could not read public RSA key pair!", e);
        }
    }
}