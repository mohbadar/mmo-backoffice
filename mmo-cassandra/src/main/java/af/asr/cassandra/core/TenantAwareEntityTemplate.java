package af.asr.cassandra.core;


import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Statement;
import com.datastax.driver.mapping.Mapper;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class TenantAwareEntityTemplate {

    private final CassandraSessionProvider cassandraSessionProvider;
    private final TenantAwareCassandraMapperProvider tenantAwareCassandraMapperProvider;

    public TenantAwareEntityTemplate(final CassandraSessionProvider cassandraSessionProvider,
                                     final TenantAwareCassandraMapperProvider tenantAwareCassandraMapperProvider) {
        super();
        this.cassandraSessionProvider = cassandraSessionProvider;
        this.tenantAwareCassandraMapperProvider = tenantAwareCassandraMapperProvider;
    }

    @SuppressWarnings("unchecked")
    public <T> void save(final T entity) {
        final Mapper<T> mapper = this.tenantAwareCassandraMapperProvider.getMapper((Class<T>) entity.getClass());
        mapper.save(entity);
    }

    @Nonnull
    public <T> Optional<T> findById(final Class<T> type, final Object... identifier) {
        final Mapper<T> mapper = this.tenantAwareCassandraMapperProvider.getMapper(type);
        return Optional.ofNullable(mapper.get(identifier));
    }


    @Nonnull
    public <T> List<T> fetchByKeys(final Class<T> type, final Object... keys) {
        final Mapper<T> mapper = this.tenantAwareCassandraMapperProvider.getMapper(type);
        final Statement query = mapper.getQuery(keys);
        final ResultSet resultSet = this.cassandraSessionProvider.getTenantSession().execute(query);
        return mapper.map(resultSet).all();
    }

    @SuppressWarnings("unchecked")
    public <T> void delete(final T entity) {
        final Mapper<T> mapper = this.tenantAwareCassandraMapperProvider.getMapper((Class<T>) entity.getClass());
        mapper.delete(entity);
    }
}