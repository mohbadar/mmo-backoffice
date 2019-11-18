package af.asr.identity.service.internal.repository;


import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(name = ApplicationPermissions.TABLE_NAME)
public class ApplicationPermissionEntity {
    @PartitionKey
    @Column(name = ApplicationPermissions.APPLICATION_IDENTIFIER_COLUMN)
    private String applicationIdentifier;

    @ClusteringColumn
    @Column(name = ApplicationPermissions.PERMITTABLE_GROUP_IDENTIFIER_COLUMN)
    private String permittableGroupIdentifier;

    @Column(name = ApplicationPermissions.PERMISSION_COLUMN)
    private PermissionType permission;

    public ApplicationPermissionEntity() {
    }

    public ApplicationPermissionEntity(final String applicationIdentifier, final PermissionType permission) {
        this.applicationIdentifier = applicationIdentifier;
        this.permittableGroupIdentifier = permission.getPermittableGroupIdentifier();
        this.permission = permission;
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

    public PermissionType getPermission() {
        return permission;
    }

    public void setPermission(PermissionType permission) {
        this.permission = permission;
    }
}