package af.asr.postgresql.domain;


import lombok.Data;
import lombok.ToString;

@SuppressWarnings("WeakerAccess")
@Data
@ToString
public final class Tenant {

    private final String identifier;
    private String driverClass;
    private String databaseName;
    private String host;
    private String port;
    private String user;
    private String password;

    public Tenant(final String identifier) {
        super();
        this.identifier = identifier;
    }
}