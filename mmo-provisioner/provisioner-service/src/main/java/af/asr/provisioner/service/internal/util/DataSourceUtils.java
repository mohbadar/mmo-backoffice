package af.asr.provisioner.service.internal.util;

import af.asr.provisioner.api.v1.domain.DatabaseConnectionInfo;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceUtils {

    private DataSourceUtils() {
        super();
    }

    public static Connection create(final DatabaseConnectionInfo databaseConnectionInfo) {
        try {
            Class.forName(databaseConnectionInfo.getDriverClass());
        } catch (ClassNotFoundException cnfex) {
            throw new IllegalArgumentException(cnfex.getMessage(), cnfex);
        }

        final String jdbcUrl = JdbcUrlBuilder
                .create(JdbcUrlBuilder.DatabaseType.POSTGRESQL)
                .host(databaseConnectionInfo.getHost())
                .port(databaseConnectionInfo.getPort())
                .instanceName(databaseConnectionInfo.getDatabaseName())
                .build();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            final Connection connection = DriverManager.getConnection(jdbcUrl, databaseConnectionInfo.getUser(), databaseConnectionInfo.getPassword());
            connection.setAutoCommit(true);
            return connection;
        } catch (SQLException sqlex) {
            throw new IllegalStateException(sqlex.getMessage(), sqlex);
        }
    }

    public static Connection createProvisionerConnection(final Environment environment, String databaseName) {
        final DatabaseConnectionInfo databaseConnectionInfo = new DatabaseConnectionInfo();
        databaseConnectionInfo.setDriverClass(environment.getProperty("postgresql.driverClass"));
        if (databaseName != null) {
            databaseConnectionInfo.setDatabaseName(databaseName);
        }
        databaseConnectionInfo.setHost(environment.getProperty("postgresql.host"));
        databaseConnectionInfo.setPort(environment.getProperty("postgresql.port"));
        databaseConnectionInfo.setUser(environment.getProperty("postgresql.user"));
        databaseConnectionInfo.setPassword(environment.getProperty("postgresql.password"));

        try {
            final Connection connection = DataSourceUtils.create(databaseConnectionInfo);
            connection.setAutoCommit(true);
            return connection;
        } catch (SQLException error) {
            throw new IllegalStateException(error.getMessage(), error);
        }
    }
}