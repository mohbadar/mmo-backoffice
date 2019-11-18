package af.asr.identity.service.internal.repository;


import af.asr.cassandra.core.CassandraSessionProvider;
import af.asr.cassandra.core.TenantAwareCassandraMapperProvider;
import af.asr.cassandra.core.TenantAwareEntityTemplate;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.schemabuilder.Create;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;
import com.datastax.driver.mapping.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;


@Component
public class ApplicationSignatures {
    static final java.lang.String TABLE_NAME = "isis_application_signatures";
    static final String APPLICATION_IDENTIFIER_COLUMN = "application_identifier";
    static final String KEY_TIMESTAMP_COLUMN = "key_timestamp";
    static final String PUBLIC_KEY_MOD_COLUMN = "public_key_mod";
    static final String PUBLIC_KEY_EXP_COLUMN = "public_key_exp";

    private final CassandraSessionProvider cassandraSessionProvider;
    private final TenantAwareEntityTemplate tenantAwareEntityTemplate;
    private final TenantAwareCassandraMapperProvider tenantAwareCassandraMapperProvider;

    @Autowired
    public ApplicationSignatures(final CassandraSessionProvider cassandraSessionProvider,
                                 final TenantAwareEntityTemplate tenantAwareEntityTemplate,
                                 final TenantAwareCassandraMapperProvider tenantAwareCassandraMapperProvider) {
        this.cassandraSessionProvider = cassandraSessionProvider;
        this.tenantAwareEntityTemplate = tenantAwareEntityTemplate;
        this.tenantAwareCassandraMapperProvider = tenantAwareCassandraMapperProvider;
    }

    public void buildTable() {
        final Create create = SchemaBuilder.createTable(TABLE_NAME)
                .ifNotExists()
                .addPartitionKey(APPLICATION_IDENTIFIER_COLUMN, DataType.text())
                .addClusteringColumn(KEY_TIMESTAMP_COLUMN, DataType.text())
                .addColumn(PUBLIC_KEY_MOD_COLUMN, DataType.varint())
                .addColumn(PUBLIC_KEY_EXP_COLUMN, DataType.varint());

        cassandraSessionProvider.getTenantSession().execute(create);
    }

    public void add(final ApplicationSignatureEntity entity) {
        tenantAwareEntityTemplate.save(entity);
    }

    public Optional<ApplicationSignatureEntity> get(final String applicationIdentifier, final String keyTimestamp)
    {
        final ApplicationSignatureEntity entity =
                tenantAwareCassandraMapperProvider.getMapper(ApplicationSignatureEntity.class).get(applicationIdentifier, keyTimestamp);

        if (entity != null) {
            Assert.notNull(entity.getApplicationIdentifier());
            Assert.notNull(entity.getKeyTimestamp());
            Assert.notNull(entity.getPublicKeyMod());
            Assert.notNull(entity.getPublicKeyExp());
        }

        return Optional.ofNullable(entity);
    }

    public List<ApplicationSignatureEntity> getAll() {
        final Mapper<ApplicationSignatureEntity> entityMapper = tenantAwareCassandraMapperProvider.getMapper(ApplicationSignatureEntity.class);
        final Session tenantSession = cassandraSessionProvider.getTenantSession();

        final Statement statement = QueryBuilder.select().all().from(TABLE_NAME);

        return entityMapper.map(tenantSession.execute(statement)).all();
    }

    public void delete(final String applicationIdentifier) {
        final Delete.Where deleteStatement = QueryBuilder.delete().from(TABLE_NAME)
                .where(QueryBuilder.eq(APPLICATION_IDENTIFIER_COLUMN, applicationIdentifier));
        cassandraSessionProvider.getTenantSession().execute(deleteStatement);
    }

    public boolean signaturesExistForApplication(final String applicationIdentifier) {
        final Select.Where selectStatement = QueryBuilder.select().from(TABLE_NAME)
                .where(QueryBuilder.eq(APPLICATION_IDENTIFIER_COLUMN, applicationIdentifier));
        final ResultSet selected = cassandraSessionProvider.getTenantSession().execute(selectStatement);
        final int count = selected.getAvailableWithoutFetching();
        return count > 0;
    }
}