package af.asr.identity.api.v1.domain;

import java.util.Objects;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import af.asr.vault.api.domain.AllowedOperation;
import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Permission {
    @NotBlank
    private String permittableEndpointGroupIdentifier;

    @NotNull
    @Valid
    private Set<AllowedOperation> allowedOperations;

    public Permission() {
    }

    public Permission(String permittableEndpointGroupIdentifier, Set<AllowedOperation> allowedOperations) {
        this.permittableEndpointGroupIdentifier = permittableEndpointGroupIdentifier;
        this.allowedOperations = allowedOperations;
    }

    public String getPermittableEndpointGroupIdentifier() {
        return permittableEndpointGroupIdentifier;
    }

    public void setPermittableEndpointGroupIdentifier(String permittableEndpointGroupIdentifier) {
        this.permittableEndpointGroupIdentifier = permittableEndpointGroupIdentifier;
    }

    public Set<AllowedOperation> getAllowedOperations() {
        return allowedOperations;
    }

    public void setAllowedOperations(Set<AllowedOperation> allowedOperations) {
        this.allowedOperations = allowedOperations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(permittableEndpointGroupIdentifier, that.permittableEndpointGroupIdentifier) &&
                Objects.equals(allowedOperations, that.allowedOperations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permittableEndpointGroupIdentifier, allowedOperations);
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permittableEndpointGroupIdentifier='" + permittableEndpointGroupIdentifier + '\'' +
                ", allowedOperations=" + allowedOperations +
                '}';
    }
}