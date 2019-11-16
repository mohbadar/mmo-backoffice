package af.asr.security;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;

@SuppressWarnings({"WeakerAccess"})
public class RsaPrivateKeyBuilder {

    private BigInteger privateKeyMod;
    private BigInteger privateKeyExp;

    public RsaPrivateKeyBuilder setPrivateKeyMod(final BigInteger privateKeyMod) {
        this.privateKeyMod = privateKeyMod;
        return this;
    }

    public RsaPrivateKeyBuilder setPrivateKeyExp(final BigInteger privateKeyExp) {
        this.privateKeyExp = privateKeyExp;
        return this;
    }

    public PrivateKey build() {
        try {
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            final RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(privateKeyMod, privateKeyExp);
            return keyFactory.generatePrivate(rsaPrivateKeySpec);
        } catch (final NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException("Could not read private RSA key pair!", e);
        }
    }
}