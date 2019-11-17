package af.asr.cassandra.core;


import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.concurrent.ConcurrentHashMap;

public class TenantAwareCassandraMapperProvider {

    private final Environment env;
    private final Logger logger;
    private final CassandraSessionProvider cassandraSessionProvider;
    private final ConcurrentHashMap<String, MappingManager> managerCache;

    public TenantAwareCassandraMapperProvider(@Nonnull final Environment env, @Nonnull final Logger logger,
                                              @Nonnull final CassandraSessionProvider cassandraSessionProvider) {
        super();
        Assert.notNull(env, "An environment must be given.");
        Assert.notNull(logger, "A logger must be given.");
        Assert.notNull(cassandraSessionProvider, "A Cassandra session provider must be given.");
        this.env = env;
        this.logger = logger;
        this.cassandraSessionProvider = cassandraSessionProvider;
        this.managerCache = new ConcurrentHashMap<>();
    }

    @SuppressWarnings("WeakerAccess")
    @Nonnull
    public <T> Mapper<T> getMapper(@Nonnull final Class<T> type) {
        Assert.notNull(type, "A type must be given.");
        if (TenantContextHolder.identifier().isPresent()) {
            final String identifier = TenantContextHolder.checkedGetIdentifier();
            return this.getMapper(identifier, type);
        } else {
            throw new IllegalArgumentException("Could not find tenant identifier, make sure you set an identifier using TenantContextHolder.");
        }
    }

    @SuppressWarnings("WeakerAccess")
    @Nonnull
    public <T> Mapper<T> getMapper(@Nonnull final String identifier, @Nonnull final Class<T> type) {
        Assert.notNull(identifier, "A tenant identifier must be given.");
        Assert.hasText(identifier, "A tenant identifier must be given.");
        Assert.notNull(type, "A type must be given.");

        this.managerCache.computeIfAbsent(identifier, (key) -> {
            this.logger.info("Create new mapping mapper for tenant [" + identifier + "] and type [" + type.getSimpleName() + "].");
            final Session session = this.cassandraSessionProvider.getTenantSession(identifier);

            final MappingManager mappingManager = new MappingManager(session);

            final Mapper<T> typedMapper = mappingManager.mapper(type);
            typedMapper.setDefaultDeleteOptions(OptionProvider.deleteConsistencyLevel(this.env));
            typedMapper.setDefaultGetOptions(OptionProvider.readConsistencyLevel(this.env));
            typedMapper.setDefaultSaveOptions(OptionProvider.writeConsistencyLevel(this.env));

            return mappingManager;
        });

        return this.managerCache.get(identifier).mapper(type);
    }
}