package af.asr.identity.service.internal.repository;


import af.asr.cassandra.core.CassandraSessionProvider;
import af.asr.cassandra.core.TenantAwareEntityTemplate;
import af.asr.identity.service.internal.util.IdentityConstants;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.schemabuilder.Create;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;
import java.nio.ByteBuffer;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Tenants {
    static final String TABLE_NAME = "isis_tenant";
    static final String VERSION_COLUMN = "version";
    static final String FIXED_SALT_COLUMN = "fixed_salt";
    static final String PASSWORD_EXPIRES_IN_DAYS_COLUMN = "password_expires_in_days";
    static final String TIME_TO_CHANGE_PASSWORD_AFTER_EXPIRATION_IN_DAYS = "time_to_change_password_after_expiration_in_days";

    private final CassandraSessionProvider cassandraSessionProvider;
    private final TenantAwareEntityTemplate tenantAwareEntityTemplate;

    @Autowired
    Tenants(final CassandraSessionProvider cassandraSessionProvider,
            final TenantAwareEntityTemplate tenantAwareEntityTemplate)
    {
        this.cassandraSessionProvider = cassandraSessionProvider;
        this.tenantAwareEntityTemplate = tenantAwareEntityTemplate;
    }

    public void buildTable() {
        final Create create = SchemaBuilder.createTable(TABLE_NAME)
                .ifNotExists()
                .addPartitionKey(VERSION_COLUMN, DataType.cint())
                .addColumn(FIXED_SALT_COLUMN, DataType.blob())
                .addColumn(PASSWORD_EXPIRES_IN_DAYS_COLUMN, DataType.cint())
                .addColumn(TIME_TO_CHANGE_PASSWORD_AFTER_EXPIRATION_IN_DAYS, DataType.cint());

        cassandraSessionProvider.getTenantSession().execute(create);
    }

    public void add(
            final byte[] fixedSalt,
            final int passwordExpiresInDays,
            final int timeToChangePasswordAfterExpirationInDays)
    {
        //There will only be one entry in this table.
        final BoundStatement tenantCreationStatement =
                cassandraSessionProvider.getTenantSession().prepare("INSERT INTO " + Tenants.TABLE_NAME + " ("
                        + VERSION_COLUMN + ", "
                        + FIXED_SALT_COLUMN + ", "
                        + PASSWORD_EXPIRES_IN_DAYS_COLUMN + ", "
                        + TIME_TO_CHANGE_PASSWORD_AFTER_EXPIRATION_IN_DAYS + ")"
                        + "VALUES (?, ?, ?, ?)").bind();

        tenantCreationStatement.setInt(VERSION_COLUMN, IdentityConstants.CURRENT_VERSION);

        tenantCreationStatement.setBytes(FIXED_SALT_COLUMN, ByteBuffer.wrap(fixedSalt));
        tenantCreationStatement.setInt(PASSWORD_EXPIRES_IN_DAYS_COLUMN, passwordExpiresInDays);
        tenantCreationStatement.setInt(TIME_TO_CHANGE_PASSWORD_AFTER_EXPIRATION_IN_DAYS, timeToChangePasswordAfterExpirationInDays);


        cassandraSessionProvider.getTenantSession().execute(tenantCreationStatement);
    }

    public Optional<PrivateTenantInfoEntity> getPrivateTenantInfo()
    {
        return tenantAwareEntityTemplate
                .findById(PrivateTenantInfoEntity.class, IdentityConstants.CURRENT_VERSION);
    }
}