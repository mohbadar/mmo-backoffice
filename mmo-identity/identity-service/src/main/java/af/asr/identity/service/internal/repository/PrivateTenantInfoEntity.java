package af.asr.identity.service.internal.repository;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import java.nio.ByteBuffer;


@SuppressWarnings("unused")
@Table(name = Tenants.TABLE_NAME)
public class PrivateTenantInfoEntity {
    @PartitionKey
    @Column(name = Tenants.VERSION_COLUMN)
    private int version;

    @Column(name = Tenants.FIXED_SALT_COLUMN)
    private ByteBuffer fixedSalt;

    @Column(name = Tenants.PASSWORD_EXPIRES_IN_DAYS_COLUMN)
    private int passwordExpiresInDays;

    @Column(name = Tenants.TIME_TO_CHANGE_PASSWORD_AFTER_EXPIRATION_IN_DAYS)
    private int timeToChangePasswordAfterExpirationInDays;

    public PrivateTenantInfoEntity() { }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public ByteBuffer getFixedSalt() {
        return fixedSalt;
    }

    public void setFixedSalt(ByteBuffer fixedSalt) {
        this.fixedSalt = fixedSalt;
    }

    public int getPasswordExpiresInDays() {
        return passwordExpiresInDays;
    }

    public void setPasswordExpiresInDays(final int passwordExpiresInDays) {
        this.passwordExpiresInDays = passwordExpiresInDays;
    }

    public int getTimeToChangePasswordAfterExpirationInDays() {
        return timeToChangePasswordAfterExpirationInDays;
    }

    public void setTimeToChangePasswordAfterExpirationInDays(int timeToChangePasswordAfterExpirationInDays) {
        this.timeToChangePasswordAfterExpirationInDays = timeToChangePasswordAfterExpirationInDays;
    }
}