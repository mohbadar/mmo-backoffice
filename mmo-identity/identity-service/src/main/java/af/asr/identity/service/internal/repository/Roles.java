package af.asr.identity.service.internal.repository;


import af.asr.cassandra.core.CassandraSessionProvider;
import af.asr.cassandra.core.TenantAwareCassandraMapperProvider;
import af.asr.cassandra.core.TenantAwareEntityTemplate;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.schemabuilder.Create;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;
import com.datastax.driver.mapping.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Roles {
    static final String TABLE_NAME = "isis_roles";
    static final String IDENTIFIER_COLUMN = "identifier";
    static final String PERMISSIONS_COLUMN = "permissions";

    private final CassandraSessionProvider cassandraSessionProvider;
    private final TenantAwareEntityTemplate tenantAwareEntityTemplate;
    private final TenantAwareCassandraMapperProvider tenantAwareCassandraMapperProvider;

    @Autowired
    Roles(
            final CassandraSessionProvider cassandraSessionProvider,
            final TenantAwareEntityTemplate tenantAwareEntityTemplate,
            final TenantAwareCassandraMapperProvider tenantAwareCassandraMapperProvider) {
        this.cassandraSessionProvider = cassandraSessionProvider;
        this.tenantAwareEntityTemplate = tenantAwareEntityTemplate;
        this.tenantAwareCassandraMapperProvider = tenantAwareCassandraMapperProvider;
    }

    public void buildTable() {
        final Create create = SchemaBuilder.createTable(TABLE_NAME)
                .ifNotExists()
                .addPartitionKey(IDENTIFIER_COLUMN, DataType.text())
                .addUDTListColumn(PERMISSIONS_COLUMN, SchemaBuilder.frozen(Permissions.TYPE_NAME));

        cassandraSessionProvider.getTenantSession().execute(create);

    }

    public void add(final RoleEntity instance) {
        tenantAwareEntityTemplate.save(instance);
    }

    public void change(final RoleEntity instance) {
        tenantAwareEntityTemplate.save(instance);
    }

    public Optional<RoleEntity> get(final String identifier)
    {
        final RoleEntity instance =
                tenantAwareCassandraMapperProvider.getMapper(RoleEntity.class).get(identifier);

        if (instance != null) {
            Assert.notNull(instance.getIdentifier());
            Assert.notNull(instance.getPermissions());
        }

        return Optional.ofNullable(instance);
    }

    public void delete(final RoleEntity instance) {
        tenantAwareEntityTemplate.delete(instance);
    }

    public List<RoleEntity> getAll()
    {
        final Session tenantSession = cassandraSessionProvider.getTenantSession();
        final Mapper<RoleEntity> entityMapper = tenantAwareCassandraMapperProvider.getMapper(RoleEntity.class);

        final Statement statement = QueryBuilder.select().all().from(TABLE_NAME);

        return new ArrayList<>(entityMapper.map(tenantSession.execute(statement)).all());
    }
}