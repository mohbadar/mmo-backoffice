package af.asr.crypto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.util.EncodingUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class HashGenerator {

    private final Environment environment;

    @Autowired
    public HashGenerator(final Environment environment) {
        super();
        this.environment = environment;
    }

    @Nonnull
    public byte[] hash(@Nonnull final String password, @Nonnull final byte[] salt,
                       @Nonnegative final int iterationCount, @Nonnegative final int length) {
        Assert.notNull(password, "Password must be given!");
        Assert.notNull(salt, "Salt must be given!");
        Assert.isTrue(iterationCount > 0, "Iteration count must be greater than zero!");
        Assert.isTrue(length > 0, "Length must be greater than zero!");

        try {
            final PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, length);
            final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            final byte[] encodedHash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
            return encodedHash;
        } catch (final NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public boolean isEqual(@Nonnull final byte[] knownHash, @Nonnull final byte[] password, @Nonnull final byte[] secret,
                           @Nonnull final byte[] salt, @Nonnegative final int iterationCount, @Nonnegative final int length) {
        Assert.notNull(knownHash, "Known hash must be given!");
        Assert.notNull(password, "Password must be given!");
        Assert.notNull(salt, "Salt must be given!");
        Assert.isTrue(iterationCount > 0, "Iteration count must be greater than zero!");
        Assert.isTrue(length > 0, "Length must be greater than zero!");
        final byte[] internalSalt = EncodingUtils.concatenate(salt, secret);

        final byte[] computedHash = this.hash(Base64Utils.encodeToString(password), internalSalt, iterationCount, length);

        return MessageDigest.isEqual(knownHash, computedHash);
    }
}
