package af.asr.cassandra.core;


import org.springframework.util.Assert;

import java.util.ArrayList;

@SuppressWarnings("WeakerAccess")
public class CassandraJourneyRoute {

    private final String version;
    private final ArrayList<CassandraJourneyWaypoint> cassandraJourneyWaypoints;
    private CassandraJourneyRoute(final String version,
                                  final ArrayList<CassandraJourneyWaypoint> cassandraJourneyWaypoints) {
        super();
        this.version = version;
        this.cassandraJourneyWaypoints = cassandraJourneyWaypoints;
    }

    public static Builder plan(final String version) {
        return new Builder(version);
    }

    public ArrayList<CassandraJourneyWaypoint> getCassandraJourneyWaypoints() {
        return this.cassandraJourneyWaypoints;
    }

    public String getVersion() {
        return this.version;
    }

    public Integer getHashValue() {
        return 31 * this.cassandraJourneyWaypoints.stream().mapToInt(CassandraJourneyWaypoint::getHashValue).sum();
    }

    public static class Builder {

        private final String version;
        private ArrayList<CassandraJourneyWaypoint> cassandraJourneyWaypoints;

        private Builder(final String version) {
            super();
            this.version = version;
        }

        public Builder addWaypoint(final String statement) {
            if (this.cassandraJourneyWaypoints == null) {
                this.cassandraJourneyWaypoints = new ArrayList<>();
            }
            this.cassandraJourneyWaypoints.add(new CassandraJourneyWaypoint(statement));
            return this;
        }

        public CassandraJourneyRoute build() {
            Assert.notNull(this.cassandraJourneyWaypoints);
            return new CassandraJourneyRoute(this.version, this.cassandraJourneyWaypoints);
        }
    }
}