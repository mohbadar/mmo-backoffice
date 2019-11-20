package af.asr.provisioner.service.internal.util;


import af.asr.vault.api.RoleConstants;
import af.asr.vault.service.token.SystemAccessTokenSerializer;
import af.asr.vault.service.token.TokenSerializationResult;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.concurrent.TimeUnit;

public class TokenProvider {
    private final String keyTimestamp;
    private final PrivateKey privateKey;
    private final SystemAccessTokenSerializer tokenSerializer;

    public TokenProvider(
            final String keyTimestamp,
            final BigInteger privateKeyModulus,
            final BigInteger privateKeyExponent,
            final SystemAccessTokenSerializer tokenSerializer) {
        super();
        this.tokenSerializer = tokenSerializer;

        try {
            this.keyTimestamp = keyTimestamp;
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            final RSAPrivateKeySpec rsaPrivateKeySpec
                    = new RSAPrivateKeySpec(privateKeyModulus, privateKeyExponent);
            this.privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);

        } catch (final Exception ex) {
            throw new IllegalStateException("Could not read RSA key pair!", ex);
        }
    }

    public TokenSerializationResult createToken(
            final String subject,
            final String audience,
            final long ttl,
            final TimeUnit timeUnit) {
        SystemAccessTokenSerializer.Specification specification = new SystemAccessTokenSerializer.Specification();
        specification.setKeyTimestamp(keyTimestamp);
        specification.setTenant(subject);
        specification.setTargetApplicationName(audience);
        specification.setSecondsToLive(timeUnit.toSeconds(ttl));
        specification.setRole(RoleConstants.SYSTEM_ADMIN_ROLE_IDENTIFIER);
        specification.setPrivateKey(privateKey);

        return this.tokenSerializer.build(specification);
    }
}