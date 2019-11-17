package af.asr.crypto;


import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.annotation.Nonnull;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public final class SaltGenerator {

    public SaltGenerator() {
        super();
    }

    @Nonnull
    public byte[] createRandomSalt() {
        try {
            final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            final byte[] salt = new byte[32];
            secureRandom.nextBytes(salt);
            return Base64Utils.encode(salt);
        } catch (final NoSuchAlgorithmException nsaex) {
            throw new IllegalStateException(nsaex);
        }
    }
}