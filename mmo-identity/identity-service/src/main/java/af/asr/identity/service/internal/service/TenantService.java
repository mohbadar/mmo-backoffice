package af.asr.identity.service.internal.service;

import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.List;
import java.util.Optional;

import af.asr.identity.service.internal.mapper.SignatureMapper;
import af.asr.identity.service.internal.repository.PrivateSignatureEntity;
import af.asr.identity.service.internal.repository.SignatureEntity;
import af.asr.identity.service.internal.repository.Signatures;
import af.asr.security.RsaKeyPairFactory;
import af.asr.security.RsaPrivateKeyBuilder;
import af.asr.vault.api.domain.ApplicationSignatureSet;
import af.asr.vault.api.domain.Signature;
import af.asr.vault.service.config.TenantSignatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService implements TenantSignatureRepository {
    private final Signatures signatures;

    @Autowired
    TenantService(final Signatures signatures)
    {
        this.signatures = signatures;
    }

    public Optional<Signature> getIdentityManagerSignature(final String keyTimestamp) {
        final Optional<SignatureEntity> signature = signatures.getSignature(keyTimestamp);
        return signature.map(x -> new Signature(x.getPublicKeyMod(), x.getPublicKeyExp()));
    }

    @Override
    public List<String> getAllSignatureSetKeyTimestamps() {
        return signatures.getAllKeyTimestamps();
    }

    @Override
    public Optional<ApplicationSignatureSet> getSignatureSet(final String keyTimestamp) {
        final Optional<SignatureEntity> signatureEntity = signatures.getSignature(keyTimestamp);
        return signatureEntity.map(SignatureMapper::mapToApplicationSignatureSet);
    }

    @Override
    public void deleteSignatureSet(final String keyTimestamp) {
        signatures.invalidateEntry(keyTimestamp);
    }

    @Override
    public Optional<Signature> getApplicationSignature(final String keyTimestamp) {
        final Optional<SignatureEntity> signatureEntity = signatures.getSignature(keyTimestamp);
        return signatureEntity.map(x -> new Signature(x.getPublicKeyMod(), x.getPublicKeyExp()));
    }

    public ApplicationSignatureSet createSignatureSet() {
        final RsaKeyPairFactory.KeyPairHolder keys = RsaKeyPairFactory.createKeyPair();
        final SignatureEntity signatureEntity = signatures.add(keys);
        return SignatureMapper.mapToApplicationSignatureSet(signatureEntity);
    }

    @Override
    public Optional<ApplicationSignatureSet> getLatestSignatureSet() {
        Optional<String> timestamp = getMostRecentTimestamp();
        return timestamp.flatMap(this::getSignatureSet);
    }

    @Override
    public Optional<Signature> getLatestApplicationSignature() {
        Optional<String> timestamp = getMostRecentTimestamp();
        return timestamp.flatMap(this::getApplicationSignature);
    }

    @Override
    public Optional<RsaKeyPairFactory.KeyPairHolder> getLatestApplicationSigningKeyPair() {
        final Optional<PrivateSignatureEntity> privateSignatureEntity = signatures.getPrivateSignature();
        return privateSignatureEntity.map(x -> {
            final String timestamp = x.getKeyTimestamp();
            final PrivateKey privateKey = new RsaPrivateKeyBuilder()
                    .setPrivateKeyExp(x.getPrivateKeyExp())
                    .setPrivateKeyMod(x.getPrivateKeyMod())
                    .build();
            return new RsaKeyPairFactory.KeyPairHolder(timestamp, null, (RSAPrivateKey)privateKey);
        });
    }

    private Optional<String> getMostRecentTimestamp() {
        return getAllSignatureSetKeyTimestamps().stream()
                .max(String::compareTo);
    }
}