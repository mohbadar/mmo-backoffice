package af.asr.identity.service.internal.mapper;


import af.asr.identity.service.internal.repository.ApplicationSignatureEntity;
import af.asr.identity.service.internal.repository.SignatureEntity;
import af.asr.vault.api.domain.ApplicationSignatureSet;
import af.asr.vault.api.domain.Signature;

public interface SignatureMapper {
    static ApplicationSignatureSet mapToApplicationSignatureSet(final SignatureEntity signatureEntity) {
        return new ApplicationSignatureSet(
                signatureEntity.getKeyTimestamp(),
                new Signature(signatureEntity.getPublicKeyMod(), signatureEntity.getPublicKeyExp()),
                new Signature(signatureEntity.getPublicKeyMod(), signatureEntity.getPublicKeyExp()));
    }

    static Signature mapToSignature(final ApplicationSignatureEntity entity) {
        final Signature ret = new Signature();
        ret.setPublicKeyExp(entity.getPublicKeyExp());
        ret.setPublicKeyMod(entity.getPublicKeyMod());
        return ret;
    }
}