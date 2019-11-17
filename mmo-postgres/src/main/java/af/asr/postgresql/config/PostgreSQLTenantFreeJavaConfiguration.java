package af.asr.postgresql.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@SuppressWarnings("WeakerAccess")
@Configuration
@ConditionalOnProperty(prefix = "postgresql", name = "enabled", matchIfMissing = true)
public class PostgreSQLTenantFreeJavaConfiguration {
    @Bean
    public DataSource dataSource(final MetaDataSourceWrapper metaDataSource) {
        return metaDataSource.getMetaDataSource();
    }
}
