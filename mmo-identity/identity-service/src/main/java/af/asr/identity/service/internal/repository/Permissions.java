package af.asr.identity.service.internal.repository;


import af.asr.cassandra.core.CassandraSessionProvider;
import af.asr.cassandra.util.CodecRegistry;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class Permissions {

    static final String TYPE_NAME = "isis_permission";
    static final String PERMITTABLE_GROUP_IDENTIFIER_FIELD = "permittable_group_identifier";
    static final String ALLOWED_OPERATIONS_FIELD = "allowed_operations";

    private final CassandraSessionProvider cassandraSessionProvider;

    @Autowired
    public Permissions(final CassandraSessionProvider cassandraSessionProvider) {
        this.cassandraSessionProvider = cassandraSessionProvider;
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void initialize()
    {
        CodecRegistry.register(AllowedOperationType.getCodec());
    }

    public void buildType() {
        final String type_statement =
                SchemaBuilder.createType(TYPE_NAME)
                        .ifNotExists()
                        .addColumn(PERMITTABLE_GROUP_IDENTIFIER_FIELD, DataType.text())
                        .addColumn(ALLOWED_OPERATIONS_FIELD, DataType.set(DataType.text()))
                        .buildInternal();
        cassandraSessionProvider.getTenantSession().execute(type_statement);
    }
}