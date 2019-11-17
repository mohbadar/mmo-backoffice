package af.asr.postgresql.domain;

import af.asr.ApplicationName;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class FlywayFactoryBean {

    private final ApplicationName applicationName;

    public FlywayFactoryBean(final ApplicationName applicationName) {
        super();
        this.applicationName = applicationName;
    }

    public Flyway create(final DataSource dataSource) {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setLocations("db/migrations/postgresql");
        flyway.setTable(this.applicationName.getServiceName() + "_schema_version");
        flyway.setBaselineOnMigrate(true);
        flyway.setBaselineVersionAsString("0");
        return flyway;
    }
}