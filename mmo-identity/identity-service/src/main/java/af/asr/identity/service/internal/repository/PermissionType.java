package af.asr.identity.service.internal.repository;


import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

import java.util.Objects;
import java.util.Set;

/**
 * @author Myrle Krantz
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@UDT(name = Permissions.TYPE_NAME)
public class PermissionType {

    @Field(name = Permissions.PERMITTABLE_GROUP_IDENTIFIER_FIELD)
    private String permittableGroupIdentifier;

    @Field(name = Permissions.ALLOWED_OPERATIONS_FIELD)
    private Set<AllowedOperationType> allowedOperations;

    public PermissionType() {
    }

    public PermissionType(String permittableGroupIdentifier, Set<AllowedOperationType> allowedOperations) {
        this.permittableGroupIdentifier = permittableGroupIdentifier;
        this.allowedOperations = allowedOperations;
    }

    public String getPermittableGroupIdentifier() {
        return permittableGroupIdentifier;
    }

    public void setPermittableGroupIdentifier(String permittableGroupIdentifier) {
        this.permittableGroupIdentifier = permittableGroupIdentifier;
    }

    public Set<AllowedOperationType> getAllowedOperations() {
        return allowedOperations;
    }

    public void setAllowedOperations(Set<AllowedOperationType> allowedOperations) {
        this.allowedOperations = allowedOperations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionType that = (PermissionType) o;
        return Objects.equals(permittableGroupIdentifier, that.permittableGroupIdentifier) &&
                Objects.equals(allowedOperations, that.allowedOperations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permittableGroupIdentifier, allowedOperations);
    }

    @Override
    public String toString() {
        return "PermissionType{" +
                "permittableGroupIdentifier='" + permittableGroupIdentifier + '\'' +
                ", allowedOperations=" + allowedOperations +
                '}';
    }
}