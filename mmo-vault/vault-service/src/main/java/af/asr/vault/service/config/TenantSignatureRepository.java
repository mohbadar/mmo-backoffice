package af.asr.vault.service.config;



import af.asr.security.RsaKeyPairFactory;

import java.util.List;
import java.util.Optional;

public interface TenantSignatureRepository {
    /**
     *
     * @param timestamp The timestamp of the signature to get.
     * @return The public keys that the identity service uses for signing tokens.
     * @throws IllegalArgumentException if the tenant context is not set.
     */
    Optional<Signature> getIdentityManagerSignature(String timestamp) throws IllegalArgumentException;

    List<String> getAllSignatureSetKeyTimestamps();

    Optional<ApplicationSignatureSet> getSignatureSet(String timestamp);

    Optional<ApplicationSignatureSet> getLatestSignatureSet();

    void deleteSignatureSet(String timestamp);

    Optional<Signature> getApplicationSignature(String timestamp);

    Optional<Signature> getLatestApplicationSignature();

    Optional<RsaKeyPairFactory.KeyPairHolder> getLatestApplicationSigningKeyPair();
}