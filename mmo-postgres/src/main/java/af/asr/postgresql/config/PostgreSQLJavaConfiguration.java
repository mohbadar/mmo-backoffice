package af.asr.postgresql.config;

import af.asr.ApplicationName;
import af.asr.config.EnableApplicationName;
import af.asr.postgresql.util.JdbcUrlBuilder;
import af.asr.postgresql.util.PostgreSQLConstants;
import com.jolbox.bonecp.BoneCPDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@SuppressWarnings("WeakerAccess")
@Configuration
@ConditionalOnProperty(prefix = "postgresql", name = "enabled", matchIfMissing = true)
@EnableApplicationName
@Import(EclipseLinkJpaConfiguration.class)
public class PostgreSQLJavaConfiguration {

    private final Environment env;

    @Autowired
    protected PostgreSQLJavaConfiguration(Environment env) {
        super();
        this.env = env;
    }

    @Bean(name = PostgreSQLConstants.LOGGER_NAME)
    public Logger logger() {
        return LoggerFactory.getLogger(PostgreSQLConstants.LOGGER_NAME);
    }

    @Bean
    public FlywayFactoryBean flywayFactoryBean(final ApplicationName applicationName) {
        return new FlywayFactoryBean(applicationName);
    }

    @Bean
    public MetaDataSourceWrapper metaDataSourceWrapper() {
        final BoneCPDataSource boneCPDataSource = new BoneCPDataSource();
        boneCPDataSource.setDriverClass(
                this.env.getProperty(PostgreSQLConstants.POSTGRESQL_DRIVER_CLASS_PROP, PostgreSQLConstants.POSTGRESQL_DRIVER_CLASS_DEFAULT));
        boneCPDataSource.setJdbcUrl(JdbcUrlBuilder
                .create(JdbcUrlBuilder.DatabaseType.POSTGRESQL)
                .host(this.env.getProperty(PostgreSQLConstants.POSTGRESQL_HOST_PROP, PostgreSQLConstants.POSTGRESQL_HOST_DEFAULT))
                .port(this.env.getProperty(PostgreSQLConstants.POSTGRESQL_PORT_PROP, PostgreSQLConstants.POSTGRESQL_PORT_DEFAULT))
                .instanceName(this.env.getProperty(PostgreSQLConstants.POSTGRESQL_DATABASE_NAME_PROP, PostgreSQLConstants.POSTGRESQL_DATABASE_NAME_DEFAULT))
                .build());
        boneCPDataSource.setUsername(
                this.env.getProperty(PostgreSQLConstants.POSTGRESQL_USER_PROP, PostgreSQLConstants.POSTGRESQL_USER_DEFAULT));
        boneCPDataSource.setPassword(
                this.env.getProperty(PostgreSQLConstants.POSTGRESQL_PASSWORD_PROP, PostgreSQLConstants.POSTGRESQL_PASSWORD_DEFAULT));
        boneCPDataSource.setIdleConnectionTestPeriodInMinutes(
                Long.valueOf(this.env.getProperty(PostgreSQLConstants.BONECP_IDLE_CONNECTION_TEST_PROP, PostgreSQLConstants.BONECP_IDLE_CONNECTION_TEST_DEFAULT)));
        boneCPDataSource.setIdleMaxAgeInMinutes(
                Long.valueOf(this.env.getProperty(PostgreSQLConstants.BONECP_IDLE_MAX_AGE_PROP, PostgreSQLConstants.BONECP_IDLE_MAX_AGE_DEFAULT)));
        boneCPDataSource.setMaxConnectionsPerPartition(
                Integer.valueOf(this.env.getProperty(PostgreSQLConstants.BONECP_MAX_CONNECTION_PARTITION_PROP, PostgreSQLConstants.BONECP_MAX_CONNECTION_PARTITION_DEFAULT)));
        boneCPDataSource.setMinConnectionsPerPartition(
                Integer.valueOf(this.env.getProperty(PostgreSQLConstants.BONECP_MIN_CONNECTION_PARTITION_PROP, PostgreSQLConstants.BONECP_MIN_CONNECTION_PARTITION_DEFAULT)));
        boneCPDataSource.setPartitionCount(
                Integer.valueOf(this.env.getProperty(PostgreSQLConstants.BONECP_PARTITION_COUNT_PROP, PostgreSQLConstants.BONECP_PARTITION_COUNT_DEFAULT)));
        boneCPDataSource.setAcquireIncrement(
                Integer.valueOf(this.env.getProperty(PostgreSQLConstants.BONECP_ACQUIRE_INCREMENT_PROP, PostgreSQLConstants.BONECP_ACQUIRE_INCREMENT_DEFAULT)));
        boneCPDataSource.setStatementsCacheSize(
                Integer.valueOf(this.env.getProperty(PostgreSQLConstants.BONECP_STATEMENT_CACHE_PROP, PostgreSQLConstants.BONECP_STATEMENT_CACHE_DEFAULT)));

        final Properties driverProperties = new Properties();
        driverProperties.setProperty("useServerPrepStmts", "false");
        boneCPDataSource.setDriverProperties(driverProperties);
        return new MetaDataSourceWrapper(boneCPDataSource);
    }
}