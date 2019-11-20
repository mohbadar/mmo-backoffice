package af.asr.provisioner.api.v1.domain;


import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

@SuppressWarnings({"unused", "WeakerAccess"})
public final class CassandraConnectionInfo {

    @NotNull
    private String clusterName;
    @NotNull
    private String contactPoints;
    @NotNull
    private String keyspace;
    @NotNull
    private String replicationType;
    @NotNull
    private String replicas;

    public CassandraConnectionInfo() {
        super();
    }

    @Nonnull
    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(@Nonnull final String clusterName) {
        this.clusterName = clusterName;
    }

    @Nonnull
    public String getContactPoints() {
        return contactPoints;
    }

    public void setContactPoints(@Nonnull final String contactPoints) {
        this.contactPoints = contactPoints;
    }

    @Nonnull
    public String getKeyspace() {
        return keyspace;
    }

    public void setKeyspace(@Nonnull final String keyspace) {
        this.keyspace = keyspace;
    }

    @Nonnull
    public String getReplicationType() {
        return replicationType;
    }

    public void setReplicationType(@Nonnull final String replicationType) {
        this.replicationType = replicationType;
    }

    @Nonnull
    public String getReplicas() {
        return replicas;
    }

    public void setReplicas(@Nonnull final String replicas) {
        this.replicas = replicas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CassandraConnectionInfo that = (CassandraConnectionInfo) o;

        return clusterName.equals(that.clusterName)
                && contactPoints.equals(that.contactPoints)
                && keyspace.equals(that.keyspace)
                && replicationType.equals(that.replicationType)
                && replicas.equals(that.replicas);

    }

    @Override
    public int hashCode() {
        int result = clusterName.hashCode();
        result = 31 * result + contactPoints.hashCode();
        result = 31 * result + keyspace.hashCode();
        result = 31 * result + replicationType.hashCode();
        result = 31 * result + replicas.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CassandraConnectionInfo{" +
                "clusterName='" + clusterName + '\'' +
                ", contactPoints='" + contactPoints + '\'' +
                ", keyspace='" + keyspace + '\'' +
                ", replicationType='" + replicationType + '\'' +
                ", replicas='" + replicas + '\'' +
                '}';
    }
}