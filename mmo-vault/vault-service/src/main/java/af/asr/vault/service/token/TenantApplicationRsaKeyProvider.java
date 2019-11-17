package af.asr.vault.service.token;

import af.asr.vault.service.provider.InvalidKeyTimestampException;

import java.security.PublicKey;

@SuppressWarnings("WeakerAccess")
public interface TenantApplicationRsaKeyProvider {
    PublicKey getApplicationPublicKey(String issuingApplication, String timestamp) throws InvalidKeyTimestampException;
}