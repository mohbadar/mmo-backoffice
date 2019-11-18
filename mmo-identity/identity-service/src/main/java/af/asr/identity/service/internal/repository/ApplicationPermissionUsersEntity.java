package af.asr.identity.service.internal.repository;


import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;


@SuppressWarnings({"unused", "WeakerAccess"})
@Table(name = ApplicationPermissionUsers.TABLE_NAME)
public class ApplicationPermissionUsersEntity {
    @PartitionKey
    @Column(name = ApplicationPermissionUsers.APPLICATION_IDENTIFIER_COLUMN)
    private String applicationIdentifier;

    @SuppressWarnings("DefaultAnnotationParam")
    @ClusteringColumn(0)
    @Column(name = ApplicationPermissionUsers.PERMITTABLE_GROUP_IDENTIFIER_COLUMN)
    private String permittableGroupIdentifier;

    @ClusteringColumn(1)
    @Column(name = ApplicationPermissionUsers.USER_IDENTIFIER_COLUMN)
    private String userIdentifier;

    @Column(name = ApplicationPermissionUsers.ENABLED_COLUMN)
    private Boolean enabled;

    public ApplicationPermissionUsersEntity() {
    }

    public ApplicationPermissionUsersEntity(String applicationIdentifier, String permittableGroupIdentifier, String userIdentifier, Boolean enabled) {
        this.applicationIdentifier = applicationIdentifier;
        this.permittableGroupIdentifier = permittableGroupIdentifier;
        this.userIdentifier = userIdentifier;
        this.enabled = enabled;
    }

    public String getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }

    public String getPermittableGroupIdentifier() {
        return permittableGroupIdentifier;
    }

    public void setPermittableGroupIdentifier(String permittableGroupIdentifier) {
        this.permittableGroupIdentifier = permittableGroupIdentifier;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}