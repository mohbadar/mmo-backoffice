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

import java.util.List;
import java.util.Optional;


@Component
public class ApplicationCallEndpointSets {
    static final String TABLE_NAME = "isis_application_callendpointsets";
    static final String APPLICATION_IDENTIFIER_COLUMN = "application_identifier";
    static final String CALLENDPOINTSET_IDENTIFIER_COLUMN = "call_endpoint_set_identifier";
    static final String CALLENDPOINT_GROUP_IDENTIFIERS_COLUMN = "call_endpoint_group_identifiers";

    private final CassandraSessionProvider cassandraSessionProvider;
    private final TenantAwareEntityTemplate tenantAwareEntityTemplate;
    private final TenantAwareCassandraMapperProvider tenantAwareCassandraMapperProvider;

    @Autowired
    public ApplicationCallEndpointSets(
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
                .addPartitionKey(APPLICATION_IDENTIFIER_COLUMN, DataType.text())
                .addClusteringColumn(CALLENDPOINTSET_IDENTIFIER_COLUMN, DataType.text())
                .addColumn(CALLENDPOINT_GROUP_IDENTIFIERS_COLUMN, DataType.list(DataType.text()));

        cassandraSessionProvider.getTenantSession().execute(create);
    }

    public void add(final ApplicationCallEndpointSetEntity entity) {
        tenantAwareEntityTemplate.save(entity);
    }

    public void change(final ApplicationCallEndpointSetEntity instance) {
        tenantAwareEntityTemplate.save(instance);
    }

    public Optional<ApplicationCallEndpointSetEntity> get(final String applicationIdentifier, final String callEndpointSetIdentifier)
    {
        final ApplicationCallEndpointSetEntity entity =
                tenantAwareCassandraMapperProvider.getMapper(ApplicationCallEndpointSetEntity.class).get(applicationIdentifier, callEndpointSetIdentifier);

        if (entity != null) {
            Assert.notNull(entity.getApplicationIdentifier());
            Assert.notNull(entity.getCallEndpointSetIdentifier());
        }

        return Optional.ofNullable(entity);
    }

    public List<ApplicationCallEndpointSetEntity> getAllForApplication(final String applicationIdentifier) {
        final Mapper<ApplicationCallEndpointSetEntity> entityMapper = tenantAwareCassandraMapperProvider.getMapper(ApplicationCallEndpointSetEntity.class);
        final Session tenantSession = cassandraSessionProvider.getTenantSession();

        final Statement statement = QueryBuilder.select().from(TABLE_NAME).where(QueryBuilder.eq(APPLICATION_IDENTIFIER_COLUMN, applicationIdentifier));

        return entityMapper.map(tenantSession.execute(statement)).all();
    }

    public void delete(final String applicationIdentifier, final String callEndpointSetIdentifier) {
        final Optional<ApplicationCallEndpointSetEntity> toDelete = tenantAwareEntityTemplate.findById(ApplicationCallEndpointSetEntity.class, applicationIdentifier, callEndpointSetIdentifier);
        toDelete.ifPresent(tenantAwareEntityTemplate::delete);
    }
}