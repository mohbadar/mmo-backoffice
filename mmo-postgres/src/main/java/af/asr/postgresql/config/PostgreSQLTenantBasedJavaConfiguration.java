package af.asr.postgresql.config;


import af.asr.postgresql.util.JdbcUrlBuilder;
import af.asr.postgresql.util.PostgreSQLConstants;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@SuppressWarnings("WeakerAccess")
@Configuration
@ConditionalOnProperty(prefix = "postgresql", name = "enabled", matchIfMissing = true)
public class PostgreSQLTenantBasedJavaConfiguration {
    @Bean
    public DataSource dataSource(@Qualifier(PostgreSQLConstants.LOGGER_NAME) final Logger logger,
                                 final MetaDataSourceWrapper metaDataSource) {

        final ContextAwareRoutingDataSource dataSource = new ContextAwareRoutingDataSource(logger, JdbcUrlBuilder.DatabaseType.POSTGRESQL);
        dataSource.setMetaDataSource(metaDataSource.getMetaDataSource());
        final HashMap<Object, Object> tenantDataSources = new HashMap<>();
        dataSource.setTargetDataSources(tenantDataSources);
        return dataSource;
    }
}