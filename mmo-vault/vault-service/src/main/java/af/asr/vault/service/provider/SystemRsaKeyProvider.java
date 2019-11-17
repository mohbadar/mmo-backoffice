package af.asr.vault.service.provider;

import af.asr.security.RsaPublicKeyBuilder;
import af.asr.vault.service.config.AnubisConstants;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.security.PublicKey;


@Component
public class SystemRsaKeyProvider {
    private final String keyTimestamp;
    private final String systemPublicKeyMod;
    private final String systemPublicKeyExp;
    private final Logger logger;

    private PublicKey systemPublicKey;

    @Autowired
    public SystemRsaKeyProvider(
            final @Value("${" + AnubisConstants.PUBLIC_KEY_TIMESTAMP_PROPERTY + "}") String keyTimestamp,
            final @Value("${" + AnubisConstants.PUBLIC_KEY_MOD_PROPERTY + "}") String systemPublicKeyMod,
            final @Value("${" + AnubisConstants.PUBLIC_KEY_EXP_PROPERTY + "}") String systemPublicKeyExp,
            final @Qualifier(AnubisConstants.LOGGER_NAME) Logger logger)
    {
        this.keyTimestamp = keyTimestamp;
        this.systemPublicKeyMod = systemPublicKeyMod;
        this.systemPublicKeyExp = systemPublicKeyExp;
        this.logger = logger;
        logger.info("System Key registered for timestamp: " + keyTimestamp);
    }

    @PostConstruct
    public void init() {
        this.systemPublicKey =
                new RsaPublicKeyBuilder()
                        .setPublicKeyMod(new BigInteger(systemPublicKeyMod))
                        .setPublicKeyExp(new BigInteger(systemPublicKeyExp))
                        .build();
    }

    public PublicKey getPublicKey(final String timestamp) throws InvalidKeyTimestampException {
        if (!timestamp.equals(keyTimestamp)) {
            logger.info("System Key requested for non-existant key timestamp: " + timestamp);
            throw new InvalidKeyTimestampException(timestamp);
        }
        return systemPublicKey;
    }
}