package af.asr.postgresql.util;

public interface PostgreSQLConstants {

    String LOGGER_NAME = "postgresql-logger";

    String POSTGRESQL_DRIVER_CLASS_PROP = "postgresql.driverClass";
    String POSTGRESQL_DRIVER_CLASS_DEFAULT = "org.postgresql.Driver";
    String POSTGRESQL_DATABASE_NAME_PROP = "postgresql.database";
    String POSTGRESQL_DATABASE_NAME_DEFAULT = "seshat";
    String POSTGRESQL_DATABASE_NAME = "postgres";
    String POSTGRESQL_HOST_PROP = "postgresql.host";
    String POSTGRESQL_HOST_DEFAULT = "localhost";
    String POSTGRESQL_PORT_PROP = "postgresql.port";
    String POSTGRESQL_PORT_DEFAULT = "5432";
    String POSTGRESQL_USER_PROP = "postgresql.user";
    String POSTGRESQL_USER_DEFAULT = "postgres";
    String POSTGRESQL_PASSWORD_PROP = "postgresql.password";
    String POSTGRESQL_PASSWORD_DEFAULT = "postgres";

    String BONECP_IDLE_MAX_AGE_PROP = "bonecp.idleMaxAgeInMinutes";
    String BONECP_IDLE_MAX_AGE_DEFAULT = "240";
    String BONECP_IDLE_CONNECTION_TEST_PROP = "bonecp.idleConnectionTestPeriodInMinutes";
    String BONECP_IDLE_CONNECTION_TEST_DEFAULT = "60";
    String BONECP_MAX_CONNECTION_PARTITION_PROP = "bonecp.maxConnectionsPerPartition";
    String BONECP_MAX_CONNECTION_PARTITION_DEFAULT = "16";
    String BONECP_MIN_CONNECTION_PARTITION_PROP = "bonecp.minConnectionsPerPartition";
    String BONECP_MIN_CONNECTION_PARTITION_DEFAULT = "4";
    String BONECP_PARTITION_COUNT_PROP = "bonecp.partitionCount";
    String BONECP_PARTITION_COUNT_DEFAULT = "2";
    String BONECP_ACQUIRE_INCREMENT_PROP = "bonecp.acquireIncrement";
    String BONECP_ACQUIRE_INCREMENT_DEFAULT = "4";
    String BONECP_STATEMENT_CACHE_PROP = "bonecp.statementsCacheSize";
    String BONECP_STATEMENT_CACHE_DEFAULT = "128";
}