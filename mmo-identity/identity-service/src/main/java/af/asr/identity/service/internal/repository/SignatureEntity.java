package af.asr.identity.service.internal.repository;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.math.BigInteger;


@SuppressWarnings({"unused", "WeakerAccess"})
@Table(name = Signatures.TABLE_NAME)
public class SignatureEntity {
    @PartitionKey
    @Column(name = Signatures.KEY_TIMESTAMP_COLUMN)
    private String keyTimestamp;

    @Column(name = Signatures.VALID_COLUMN)
    private Boolean valid;

    @Column(name = Signatures.PUBLIC_KEY_MOD_COLUMN)
    private BigInteger publicKeyMod;
    @Column(name = Signatures.PUBLIC_KEY_EXP_COLUMN)
    private BigInteger publicKeyExp;

    public SignatureEntity() { }

    public String getKeyTimestamp() {
        return keyTimestamp;
    }

    public void setKeyTimestamp(String keyTimestamp) {
        this.keyTimestamp = keyTimestamp;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public BigInteger getPublicKeyMod() {
        return publicKeyMod;
    }

    public void setPublicKeyMod(BigInteger publicKeyMod) {
        this.publicKeyMod = publicKeyMod;
    }

    public BigInteger getPublicKeyExp() {
        return publicKeyExp;
    }

    public void setPublicKeyExp(BigInteger publicKeyExp) {
        this.publicKeyExp = publicKeyExp;
    }
}