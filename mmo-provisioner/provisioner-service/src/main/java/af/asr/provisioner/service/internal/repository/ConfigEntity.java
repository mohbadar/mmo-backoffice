package af.asr.provisioner.service.internal.repository;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@SuppressWarnings("unused")
@Table(name = ConfigEntity.TABLE_NAME)
public class ConfigEntity {

    static final String TABLE_NAME = "config";
    static final String NAME_COLUMN = "name";
    static final String SECRET_COLUMN = "secret";

    @PartitionKey
    @Column(name = NAME_COLUMN)
    private String name;
    @Column(name = SECRET_COLUMN)
    private byte[] secret;

    public ConfigEntity() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getSecret() {
        return secret;
    }

    public void setSecret(byte[] secret) {
        this.secret = secret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigEntity that = (ConfigEntity) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}