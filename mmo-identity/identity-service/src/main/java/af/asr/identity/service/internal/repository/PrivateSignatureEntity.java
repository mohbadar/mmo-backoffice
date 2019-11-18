package af.asr.identity.service.internal.repository;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import java.math.BigInteger;

@SuppressWarnings("WeakerAccess")
@Table(name = Signatures.TABLE_NAME)
public class PrivateSignatureEntity {
    @PartitionKey
    @Column(name = Signatures.KEY_TIMESTAMP_COLUMN)
    private String keyTimestamp;

    @Column(name = Signatures.VALID_COLUMN)
    private Boolean valid;

    @Column(name = Signatures.PRIVATE_KEY_MOD_COLUMN)
    private BigInteger privateKeyMod;
    @Column(name = Signatures.PRIVATE_KEY_EXP_COLUMN)
    private BigInteger privateKeyExp;

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

    public BigInteger getPrivateKeyMod() {
        return privateKeyMod;
    }

    public void setPrivateKeyMod(BigInteger privateKeyMod) {
        this.privateKeyMod = privateKeyMod;
    }

    public BigInteger getPrivateKeyExp() {
        return privateKeyExp;
    }

    public void setPrivateKeyExp(BigInteger privateKeyExp) {
        this.privateKeyExp = privateKeyExp;
    }
}