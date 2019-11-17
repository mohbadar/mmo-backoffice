package af.asr.data.jpa.local;

import af.asr.TenantContextHolder;
import af.asr.data.jpa.core.MigrationHelper;
import af.asr.data.jpa.local.repository.DataSourceInstance;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalRoutingDataSource extends AbstractRoutingDataSource {

    private final Logger logger;
    private final LocalDatabaseProperties localDatabaseProperties;
    private final Map<String, DataSource> cachedDataSources = new ConcurrentHashMap<>();
    private BasicDataSource managementDataSource;

    LocalRoutingDataSource(final Logger logger,
                           final LocalDatabaseProperties localDatabaseProperties) {
        super();
        this.logger = logger;
        this.localDatabaseProperties = localDatabaseProperties;
    }

    private DataSource getManagementDataSource() {
        if (this.managementDataSource == null) {
            this.logger.debug("Creating management data source.");
            this.managementDataSource = new BasicDataSource();
            this.managementDataSource.setDriverClassName(this.localDatabaseProperties.getManagement().getDriverClass());
            this.managementDataSource.setUrl(this.localDatabaseProperties.getManagement().getUrl());

            final String username = this.localDatabaseProperties.getManagement().getUsername();
            if (username != null) {
                this.managementDataSource.setUsername(username);
            }

            final String password = this.localDatabaseProperties.getManagement().getPassword();
            if (password != null) {
                this.managementDataSource.setPassword(password);
            }

            this.managementDataSource.setInitialSize(this.localDatabaseProperties.getPool().getMinSize());
            this.managementDataSource.setMaxTotal(this.localDatabaseProperties.getPool().getMaxSize());
            this.managementDataSource.setMaxWaitMillis(this.localDatabaseProperties.getPool().getWaitTime());
        }
        this.logger.debug("Providing management data source.");
        return this.managementDataSource;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContextHolder.identifier().orElse(null);
    }

    @Override
    protected DataSource determineTargetDataSource() {
        final Object dataSourceIdentifier = this.determineCurrentLookupKey();
        if (dataSourceIdentifier == null) {
            return this.getManagementDataSource();
        }

        final String dataSourceIdentifierAsString = dataSourceIdentifier.toString();

        this.cachedDataSources.computeIfAbsent(dataSourceIdentifierAsString, s -> {
            this.logger.debug("Creating data source for '{}'.", dataSourceIdentifierAsString);
            final DataSourceInstance dataSourceInstanceDetails = this.findDataSourceInstanceDetails(s);
            final BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setDriverClassName(dataSourceInstanceDetails.getDriverClass());
            basicDataSource.setUrl(dataSourceInstanceDetails.getJdbcUrl());
            basicDataSource.setUsername(dataSourceInstanceDetails.getUsername());
            basicDataSource.setPassword(dataSourceInstanceDetails.getPassword());

            basicDataSource.setInitialSize(this.localDatabaseProperties.getPool().getMinSize());
            basicDataSource.setMaxTotal(this.localDatabaseProperties.getPool().getMaxSize());
            basicDataSource.setMaxWaitMillis(this.localDatabaseProperties.getPool().getWaitTime());
            return basicDataSource;
        });
        this.logger.debug("Providing data source for '{}'.", dataSourceIdentifierAsString);
        return this.cachedDataSources.get(dataSourceIdentifierAsString);
    }

    private DataSourceInstance findDataSourceInstanceDetails(final String dataSourceIdentifier) {
        this.logger.debug("Fetching data source details for '{}'.", dataSourceIdentifier);
        final String query = "SELECT driver_class, jdbc_url, username, password FROM data_source_instances WHERE identifier = ? ";
        final DataSource managementDataSource = this.getManagementDataSource();
        try (
                final Connection conn = managementDataSource.getConnection();
                final PreparedStatement pstmt = conn.prepareCall(query)) {
            pstmt.setString(1, dataSourceIdentifier);
            try (final ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    final DataSourceInstance dataSourceInstance = new DataSourceInstance();
                    dataSourceInstance.setDriverClass(rset.getString(1));
                    dataSourceInstance.setJdbcUrl(rset.getString(2));
                    dataSourceInstance.setUsername(rset.getString(3));
                    dataSourceInstance.setPassword(rset.getString(4));
                    return dataSourceInstance;
                }
            } catch (final SQLException sqlex) {
                throw new IllegalStateException(sqlex.getMessage());
            }
        } catch (final SQLException sqlex) {
            throw new IllegalStateException(sqlex.getMessage());
        }
        throw new IllegalStateException("No data source instance found for identifier '" + dataSourceIdentifier + "'.");
    }

    @PostConstruct
    public void init() throws Exception {
        MigrationHelper.execute(this.getManagementDataSource(), "db/changelog/db.changelog-local.yml");
    }
}