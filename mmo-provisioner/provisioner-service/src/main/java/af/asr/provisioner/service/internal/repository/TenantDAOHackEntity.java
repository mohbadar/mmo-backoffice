package af.asr.provisioner.service.internal.repository;


import javax.persistence.*;

@SuppressWarnings("unused") //To remove exception that persistence unit is missing.
@Entity
@Table(name = "tenants")
public class TenantDAOHackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    @Column(name = "identifier")
    private String identifier;
    @Column(name = "driver_class")
    private String driverClass;
    @Column(name = "database_name")
    private String databaseName;
    @Column(name = "host")
    private String host;
    @Column(name = "port")
    private String port;
    @Column(name = "a_user")
    private String user;
    @Column(name = "pwd")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}