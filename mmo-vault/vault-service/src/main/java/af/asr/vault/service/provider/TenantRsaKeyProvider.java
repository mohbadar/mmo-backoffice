package af.asr.vault.service.provider;


import af.asr.security.RsaPublicKeyBuilder;
import af.asr.vault.api.domain.Signature;
import af.asr.vault.service.config.TenantSignatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.util.Optional;


@Component
public class TenantRsaKeyProvider {

    private final TenantSignatureRepository tenantSignatureRepository;

    @Autowired
    public TenantRsaKeyProvider(final TenantSignatureRepository tenantSignatureRepository)
    {
        this.tenantSignatureRepository = tenantSignatureRepository;
    }

    public PublicKey getPublicKey(final String keyTimestamp) throws InvalidKeyTimestampException {
        final Optional<Signature> tenantAuthorizationData =
                tenantSignatureRepository.getIdentityManagerSignature(keyTimestamp);

        return
                tenantAuthorizationData.map(x -> new RsaPublicKeyBuilder()
                        .setPublicKeyMod(x.getPublicKeyMod())
                        .setPublicKeyExp(x.getPublicKeyExp())
                        .build()).orElseThrow(() -> new InvalidKeyTimestampException(keyTimestamp + " + not initialized."));
    }
}