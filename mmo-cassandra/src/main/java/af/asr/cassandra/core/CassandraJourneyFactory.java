package af.asr.cassandra.core;

import af.asr.ApplicationName;
import org.slf4j.Logger;

@SuppressWarnings("unused")
public class CassandraJourneyFactory {

    private final Logger logger;
    private final ApplicationName applicationName;

    public CassandraJourneyFactory(final Logger logger, final ApplicationName applicationName) {
        super();
        this.logger = logger;
        this.applicationName = applicationName;
    }

    public CassandraJourney create(final CassandraSessionProvider cassandraSessionProvider) {
        final CassandraJourney cassandraJourney = new CassandraJourney(
                this.logger, this.applicationName, cassandraSessionProvider.getTenantSession()
        );

        cassandraJourney.init();
        return cassandraJourney;
    }
}