package af.asr.identity.service.internal.repository;


import af.asr.cassandra.core.CassandraSessionProvider;
import af.asr.cassandra.core.TenantAwareEntityTemplate;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.schemabuilder.Create;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ApplicationPermissionUsers {
    static final String TABLE_NAME = "isis_application_permission_users";
    static final String APPLICATION_IDENTIFIER_COLUMN = "application_identifier";
    static final String PERMITTABLE_GROUP_IDENTIFIER_COLUMN = "permittable_group_identifier";
    static final String USER_IDENTIFIER_COLUMN = "user_identifier";
    static final String ENABLED_COLUMN = "enabled";
    private final CassandraSessionProvider cassandraSessionProvider;
    private final TenantAwareEntityTemplate tenantAwareEntityTemplate;

    @Autowired
    public ApplicationPermissionUsers(final CassandraSessionProvider cassandraSessionProvider,
                                      final TenantAwareEntityTemplate tenantAwareEntityTemplate) {
        this.cassandraSessionProvider = cassandraSessionProvider;
        this.tenantAwareEntityTemplate = tenantAwareEntityTemplate;
    }

    public void buildTable() {
        final Create create = SchemaBuilder.createTable(TABLE_NAME)
                .ifNotExists()
                .addPartitionKey(APPLICATION_IDENTIFIER_COLUMN, DataType.text())
                .addClusteringColumn(PERMITTABLE_GROUP_IDENTIFIER_COLUMN, DataType.text())
                .addClusteringColumn(USER_IDENTIFIER_COLUMN, DataType.text())
                .addColumn(ENABLED_COLUMN, DataType.cboolean());

        cassandraSessionProvider.getTenantSession().execute(create);
    }

    public boolean enabled(final String applicationIdentifier,
                           final String permittableEndpointGroupIdentifier,
                           final String userIdentifier) {
        return tenantAwareEntityTemplate.findById(
                ApplicationPermissionUsersEntity.class, applicationIdentifier, permittableEndpointGroupIdentifier, userIdentifier)
                .map(ApplicationPermissionUsersEntity::getEnabled)
                .orElse(false);
    }

    public void setEnabled(final String applicationIdentifier,
                           final String permittableGroupIdentifier,
                           final String userIdentifier,
                           final boolean enabled) {
        tenantAwareEntityTemplate.save(new ApplicationPermissionUsersEntity(applicationIdentifier, permittableGroupIdentifier, userIdentifier, enabled));
    }
}