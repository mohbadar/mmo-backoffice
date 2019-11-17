package af.asr.cassandra.core;

import af.asr.cassandra.util.CassandraConnectorConstants;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.mapping.Mapper;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.HashMap;

@SuppressWarnings("WeakerAccess")
public final class OptionProvider {

    private static final HashMap<String, Mapper.Option> CACHED_OPTIONS = new HashMap<>();

    private OptionProvider() {
        super();
    }

    @Nonnull
    public static Mapper.Option deleteConsistencyLevel(@Nonnull final Environment env) {
        Assert.notNull(env, "An environment must be given!");
        if (OptionProvider.CACHED_OPTIONS.isEmpty()) {
            OptionProvider.lazyOptionInit(env);
        }
        return OptionProvider.CACHED_OPTIONS.get(CassandraConnectorConstants.CONSISTENCY_LEVEL_DELETE_PROP);
    }

    @Nonnull
    public static Mapper.Option readConsistencyLevel(@Nonnull final Environment env) {
        Assert.notNull(env, "An environment must be given!");
        if (OptionProvider.CACHED_OPTIONS.isEmpty()) {
            OptionProvider.lazyOptionInit(env);
        }
        return OptionProvider.CACHED_OPTIONS.get(CassandraConnectorConstants.CONSISTENCY_LEVEL_READ_PROP);
    }

    @Nonnull
    public static Mapper.Option writeConsistencyLevel(@Nonnull final Environment env) {
        Assert.notNull(env, "An environment must be given!");
        if (OptionProvider.CACHED_OPTIONS.isEmpty()) {
            OptionProvider.lazyOptionInit(env);
        }
        return OptionProvider.CACHED_OPTIONS.get(CassandraConnectorConstants.CONSISTENCY_LEVEL_WRITE_PROP);
    }

    private static void lazyOptionInit(final Environment env) {
        OptionProvider.CACHED_OPTIONS.put(CassandraConnectorConstants.CONSISTENCY_LEVEL_DELETE_PROP,
                Mapper.Option.consistencyLevel(
                        ConsistencyLevel.valueOf(
                                env.getProperty(CassandraConnectorConstants.CONSISTENCY_LEVEL_DELETE_PROP,
                                        CassandraConnectorConstants.CONSISTENCY_LEVEL_PROP_DEFAULT))));

        OptionProvider.CACHED_OPTIONS.put(CassandraConnectorConstants.CONSISTENCY_LEVEL_READ_PROP,
                Mapper.Option.consistencyLevel(
                        ConsistencyLevel.valueOf(
                                env.getProperty(CassandraConnectorConstants.CONSISTENCY_LEVEL_READ_PROP,
                                        CassandraConnectorConstants.CONSISTENCY_LEVEL_PROP_DEFAULT))));

        OptionProvider.CACHED_OPTIONS.put(CassandraConnectorConstants.CONSISTENCY_LEVEL_WRITE_PROP,
                Mapper.Option.consistencyLevel(
                        ConsistencyLevel.valueOf(
                                env.getProperty(CassandraConnectorConstants.CONSISTENCY_LEVEL_WRITE_PROP,
                                        CassandraConnectorConstants.CONSISTENCY_LEVEL_PROP_DEFAULT))));
    }
}