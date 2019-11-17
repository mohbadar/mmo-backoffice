package af.asr.cassandra.core;

@SuppressWarnings("WeakerAccess")
public class CassandraJourneyWaypoint {

    private final String statement;

    public CassandraJourneyWaypoint(final String statement) {
        super();
        this.statement = statement;
    }

    public String getStatement() {
        return this.statement;
    }

    public Integer getHashValue() {
        return this.statement.hashCode();
    }
}