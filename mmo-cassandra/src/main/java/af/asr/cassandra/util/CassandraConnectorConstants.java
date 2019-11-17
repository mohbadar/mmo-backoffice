package af.asr.cassandra.util;

public interface CassandraConnectorConstants {

    String LOGGER_NAME = "cassandra-logger";

    String CLUSTER_NAME_PROP = "cassandra.clusterName";
    String CLUSTER_NAME_PROP_DEFAULT = "staging_cluster";

    String CLUSTER_USER_PROP = "cassandra.cluster.user";
    String CLUSTER_PASSWORD_PROP = "cassandra.cluster.pwd";

    String CONTACT_POINTS_PROP = "cassandra.contactPoints";
    String CONTACT_POINTS_PROP_DEFAULT = "127.0.0.1:9042,127.0.0.2:9042,127.0.0.3:9042";

    String KEYSPACE_PROP = "cassandra.keyspace";
    String KEYSPACE_PROP_DEFAULT = "seshat";

    String CONSISTENCY_LEVEL_READ_PROP = "cassandra.cl.read";
    String CONSISTENCY_LEVEL_WRITE_PROP = "cassandra.cl.write";
    String CONSISTENCY_LEVEL_DELETE_PROP = "cassandra.cl.delete";
    String CONSISTENCY_LEVEL_PROP_DEFAULT = "LOCAL_QUORUM";

    String DEFAULT_REPLICATION_TYPE = "cassandra.default.replication.type";
    String DEFAULT_REPLICATION_REPLICAS = "cassandra.default.replication.replicas";

    String DEFAULT_REPLICATION_TYPE_DEFAULT = "Simple";
    String DEFAULT_REPLICATION_REPLICAS_DEFAULT = "1";
}