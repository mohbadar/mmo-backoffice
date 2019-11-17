package af.asr.data.jpa.local;

import af.asr.data.jpa.core.DatabaseConnectorConstants;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.util.HashMap;

@Profile("local")
@Configuration
@EnableConfigurationProperties({
        LocalDatabaseProperties.class
})
@EnableJpaRepositories(
        basePackages = {
                "org.apache.fineract.cn.core.data.jpa.local.repository"
        }
)
public class LocalDatabaseConfiguration {

    public LocalDatabaseConfiguration() {
        super();
    }

    @Bean
    public DataSource dataSource(@Qualifier(DatabaseConnectorConstants.LOGGER_NAME) final Logger logger,
                                 final LocalDatabaseProperties localDatabaseProperties) {
        final LocalRoutingDataSource localRoutingDataSource =
                new LocalRoutingDataSource(logger, localDatabaseProperties);
        localRoutingDataSource.setTargetDataSources(new HashMap<>());
        localRoutingDataSource.setLenientFallback(false);
        return localRoutingDataSource;
    }
}