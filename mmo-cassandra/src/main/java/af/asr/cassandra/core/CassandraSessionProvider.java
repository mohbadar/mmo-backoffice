package af.asr.cassandra.core;

import af.asr.cassandra.domain.Tenant;
import af.asr.cassandra.util.CassandraConnectorConstants;
import af.asr.cassandra.util.CodecRegistry;
import af.asr.cassandra.util.ContactPointUtils;
import af.asr.cassandra.util.LocalDateTimeCodec;
import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.InvalidQueryException;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.PreDestroy;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.StampedLock;

@SuppressWarnings("WeakerAccess")
public class CassandraSessionProvider {

    private final Environment env;
    private final Logger logger;
    private final ConcurrentHashMap<String, Cluster> clusterCache;
    private final ConcurrentHashMap<String, Session> sessionCache;
    private final StampedLock mapperLock = new StampedLock();
    private String adminClusterName;
    private String adminContactPoints;
    private String adminKeyspace;
    private MappingManager adminSessionMappingManager;

    public CassandraSessionProvider(@Nonnull final Environment env, @Nonnull final Logger logger) {
        super();
        Assert.notNull(env, "An environment must be given.");
        Assert.notNull(logger, "A logger must be given.");
        this.env = env;
        this.logger = logger;
        this.clusterCache = new ConcurrentHashMap<>();
        this.sessionCache = new ConcurrentHashMap<>();
    }

    public void setAdminClusterName(@Nonnull final String adminClusterName) {
        Assert.notNull(adminClusterName, "A cluster name must be given.");
        Assert.hasText(adminClusterName, "A cluster name must be given.");
        this.adminClusterName = adminClusterName;
    }

    public void setAdminContactPoints(@Nonnull final String adminContactPoints) {
        Assert.notNull(adminContactPoints, "At least one contact point must be given.");
        Assert.hasText(adminContactPoints, "At least one contact point must be given.");
        this.adminContactPoints = adminContactPoints;
    }

    public void setAdminKeyspace(@Nonnull final String adminKeyspace) {
        Assert.notNull(adminKeyspace, "An keyspace must be given.");
        Assert.hasText(adminKeyspace, "An keyspace must be given.");
        this.adminKeyspace = adminKeyspace;
    }

    @Nonnull
    public Session getAdminSession() {
        if (this.adminClusterName == null
                || this.adminContactPoints == null
                || this.adminKeyspace == null) {
            throw new IllegalStateException("Cluster name, contact points, and keyspace must be set to retrieve an admin session.");
        }

        try {
            return this.getSession(this.adminClusterName, this.adminContactPoints, this.adminKeyspace);
        } catch (final KeyspaceDoesntExistYet ignored) {
            final Cluster cluster = this.clusterCache.get(adminClusterName);
            try (final Session session = cluster.newSession()) {
                session.execute("CREATE KEYSPACE " + this.adminKeyspace + " WITH REPLICATION = " +
                        ReplicationStrategyResolver.replicationStrategy(
                                env.getProperty(CassandraConnectorConstants.DEFAULT_REPLICATION_TYPE,
                                        CassandraConnectorConstants.DEFAULT_REPLICATION_TYPE_DEFAULT),
                                env.getProperty(CassandraConnectorConstants.DEFAULT_REPLICATION_REPLICAS,
                                        CassandraConnectorConstants.DEFAULT_REPLICATION_REPLICAS_DEFAULT)));

                return this.getSession(this.adminClusterName, this.adminContactPoints, this.adminKeyspace);
            }
        }
    }

    @Nonnull
    public Session getTenantSession() {
        return TenantContextHolder.identifier()
                .map(this::getTenantSession)
                .orElseThrow(() ->
                        new IllegalArgumentException("Could not find tenant identifier, make sure you set an identifier using TenantContextHolder."));
    }

    @Nonnull
    public Session getTenantSession(@Nonnull final String identifier) {
        Assert.notNull(identifier, "A tenant identifier must be given.");
        Assert.hasText(identifier, "A tenant identifier must be given.");

        final Mapper<Tenant> tenantInfoMapper = this.getAdminSessionMappingManager().mapper(Tenant.class);
        tenantInfoMapper.setDefaultDeleteOptions(OptionProvider.deleteConsistencyLevel(this.env));
        tenantInfoMapper.setDefaultGetOptions(OptionProvider.readConsistencyLevel(this.env));
        tenantInfoMapper.setDefaultSaveOptions(OptionProvider.writeConsistencyLevel(this.env));
        final Tenant tenantInfo = tenantInfoMapper.get(identifier);
        if (tenantInfo == null) throw ServiceException.notFound("Tenant [" + identifier + "] unknown.");
        return this.getSession(tenantInfo.getClusterName(), tenantInfo.getContactPoints(), tenantInfo.getKeyspace());
    }

    @Nonnull
    public Session getSession(@Nonnull final String clusterName,
                              @Nonnull final String contactPoints,
                              @Nonnull final String keyspace) {
        Assert.notNull(clusterName, "A cluster name must be given.");
        Assert.hasText(clusterName, "A cluster name must be given.");
        Assert.notNull(contactPoints, "At least one contact point must be given.");
        Assert.hasText(contactPoints, "At least one contact point must be given.");
        Assert.notNull(keyspace, "A keyspace must be given.");
        Assert.hasText(keyspace, "A keyspace must be given.");

        this.sessionCache.computeIfAbsent(keyspace, (sessionKey) -> {
            this.logger.info("Create new session for keyspace [" + keyspace + "].");

            final Cluster cluster = this.clusterCache.computeIfAbsent(clusterName,
                    (clusterKey) -> getCluster(clusterKey, contactPoints));
            try {
                CodecRegistry.apply(cluster);
                return cluster.connect(keyspace);
            } catch (final InvalidQueryException ex) {
                throw new KeyspaceDoesntExistYet("Could not connect keyspace!", ex);
            }
        });

        return this.sessionCache.get(keyspace);
    }

    private Cluster getCluster(@Nonnull final String clusterName, @Nonnull final String contactPoints) {
        CodecRegistry.register(new LocalDateTimeCodec());

        final Cluster.Builder clusterBuilder = Cluster.builder().withClusterName(clusterName);

        if (this.env.containsProperty(CassandraConnectorConstants.CLUSTER_USER_PROP)) {
            final String user = this.env.getProperty(CassandraConnectorConstants.CLUSTER_USER_PROP);
            final String pwd = this.env.getProperty(CassandraConnectorConstants.CLUSTER_PASSWORD_PROP);

            final AuthProvider authProvider = new PlainTextAuthProvider(user, pwd);
            clusterBuilder.withAuthProvider(authProvider);
        }

        ContactPointUtils.process(clusterBuilder, contactPoints);
        return clusterBuilder.build();
    }

    @Nonnull
    public MappingManager getAdminSessionMappingManager() {
        if (this.adminSessionMappingManager == null) {
            final long lockStamp = this.mapperLock.writeLock();
            try {
                if (this.adminSessionMappingManager == null) {
                    this.adminSessionMappingManager = new MappingManager(this.getAdminSession());
                }
            } finally {
                this.mapperLock.unlockWrite(lockStamp);
            }
        }

        return this.adminSessionMappingManager;
    }

    public void touchAdminSession() {
        this.getAdminSession();
    }

    @PreDestroy
    private void cleanUp() {
        this.logger.info("Clean up cluster connections.");

        this.sessionCache.values().forEach(Session::close);
        this.sessionCache.clear();

        this.clusterCache.values().forEach(Cluster::close);
        this.clusterCache.clear();
    }
}