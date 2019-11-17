package af.asr.data.jpa.core;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.changelog.ChangeSet;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Optional;

@Component
public class MigrationHelper {

    private MigrationHelper() {
        super();
    }

    public static String execute(final DataSource dataSource, final String changeLogFile) throws Exception {
        final JdbcConnection jdbcConnection = new JdbcConnection(dataSource.getConnection());
        final Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcConnection);
        final Liquibase liquibase =
                new Liquibase(changeLogFile, new ClassLoaderResourceAccessor(), database);
        liquibase.update(new Contexts(), new LabelExpression());

        final Optional<ChangeSet> optionalMaxChangeSet =
                liquibase.getDatabaseChangeLog().getChangeSets()
                        .stream()
                        .max(VersionComparator::compare);

        return optionalMaxChangeSet.orElseThrow(() -> new IllegalStateException("No valid version found!")).getId();
    }
}