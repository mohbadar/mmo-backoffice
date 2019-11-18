package af.asr.identity.service.internal.repository;


import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Frozen;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.List;
import java.util.Objects;
@SuppressWarnings({"unused", "WeakerAccess"})
@Table(name = Roles.TABLE_NAME)
public class RoleEntity {
    @PartitionKey
    @Column(name = Roles.IDENTIFIER_COLUMN)
    private String identifier;

    @Frozen
    @Column(name = Roles.PERMISSIONS_COLUMN)
    private List<PermissionType> permissions;

    public RoleEntity() {
    }

    public RoleEntity(String identifier, List<PermissionType> permissions) {
        this.identifier = identifier;
        this.permissions = permissions;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<PermissionType> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionType> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(identifier, that.identifier) &&
                Objects.equals(permissions, that.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, permissions);
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "identifier='" + identifier + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}