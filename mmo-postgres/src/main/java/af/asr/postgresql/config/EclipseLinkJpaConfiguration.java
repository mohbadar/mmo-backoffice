package af.asr.postgresql.config;

import org.eclipse.persistence.config.BatchWriting;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.config.TargetDatabase;
import org.eclipse.persistence.logging.SessionLog;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EntityScan({
        "af.asr.**.repository",
        "af.asr.postgresql.util"
})
public class EclipseLinkJpaConfiguration extends JpaBaseConfiguration {

    @Value("#{new Boolean(${" + EclipseLinkConstants.ECLIPSE_LINK_SHOW_SQL + ":" + EclipseLinkConstants.ECLIPSE_LINK_SHOW_SQL_DEFAULT + "})}")
    private Boolean eclipseLinkShowSql;

    protected EclipseLinkJpaConfiguration(DataSource dataSource, JpaProperties properties, ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider) {
        super(dataSource, properties, jtaTransactionManagerProvider);
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.eclipse.persistence.platform.database.PostgreSQLPlatform");
        vendorAdapter.setShowSql(eclipseLinkShowSql);
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(false);
        return vendorAdapter;
    }

    @Bean
    protected Map<String, Object> getVendorProperties() {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put(PersistenceUnitProperties.WEAVING, "static");
        properties.put(PersistenceUnitProperties.WEAVING_EAGER, "true");
        properties.put(PersistenceUnitProperties.TARGET_DATABASE, TargetDatabase.PostgreSQL);
        properties.put(PersistenceUnitProperties.BATCH_WRITING, BatchWriting.JDBC);
        properties.put(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.ALL_LABEL);// Todo: Reduce log level after test
        properties.put(PersistenceUnitProperties.LOGGING_PARAMETERS, "true");
        return properties;
    }
}