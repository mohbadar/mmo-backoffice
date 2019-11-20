package af.asr.provisioner.api.v1.domain;


import af.asr.validation.constaints.ValidIdentifier;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@SuppressWarnings({"unused", "WeakerAccess"})
public final class Tenant {
    @ValidIdentifier
    private String identifier;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private CassandraConnectionInfo cassandraConnectionInfo;
    @NotNull
    private DatabaseConnectionInfo databaseConnectionInfo;

    public Tenant() {
        super();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CassandraConnectionInfo getCassandraConnectionInfo() {
        return cassandraConnectionInfo;
    }

    public void setCassandraConnectionInfo(CassandraConnectionInfo cassandraConnectionInfo) {
        this.cassandraConnectionInfo = cassandraConnectionInfo;
    }

    public DatabaseConnectionInfo getDatabaseConnectionInfo() {
        return databaseConnectionInfo;
    }

    public void setDatabaseConnectionInfo(DatabaseConnectionInfo databaseConnectionInfo) {
        this.databaseConnectionInfo = databaseConnectionInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tenant tenant = (Tenant) o;
        return Objects.equals(identifier, tenant.identifier) &&
                Objects.equals(name, tenant.name) &&
                Objects.equals(description, tenant.description) &&
                Objects.equals(cassandraConnectionInfo, tenant.cassandraConnectionInfo) &&
                Objects.equals(databaseConnectionInfo, tenant.databaseConnectionInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, name, description, cassandraConnectionInfo, databaseConnectionInfo);
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cassandraConnectionInfo=" + cassandraConnectionInfo +
                ", databaseConnectionInfo=" + databaseConnectionInfo +
                '}';
    }

}