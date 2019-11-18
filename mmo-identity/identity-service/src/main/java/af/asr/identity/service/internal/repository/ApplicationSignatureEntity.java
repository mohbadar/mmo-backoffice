package af.asr.identity.service.internal.repository;


import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.math.BigInteger;

@Table(name = ApplicationSignatures.TABLE_NAME)
public class ApplicationSignatureEntity {
    @PartitionKey
    @Column(name = ApplicationSignatures.APPLICATION_IDENTIFIER_COLUMN)
    private String applicationIdentifier;

    @ClusteringColumn
    @Column(name = ApplicationSignatures.KEY_TIMESTAMP_COLUMN)
    private String keyTimestamp;

    @Column(name = ApplicationSignatures.PUBLIC_KEY_MOD_COLUMN)
    private BigInteger publicKeyMod;

    @Column(name = ApplicationSignatures.PUBLIC_KEY_EXP_COLUMN)
    private BigInteger publicKeyExp;

    public String getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }

    public String getKeyTimestamp() {
        return keyTimestamp;
    }

    public void setKeyTimestamp(String keyTimestamp) {
        this.keyTimestamp = keyTimestamp;
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