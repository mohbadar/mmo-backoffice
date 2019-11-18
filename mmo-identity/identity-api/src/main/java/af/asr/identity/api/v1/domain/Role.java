package af.asr.identity.api.v1.domain;


import af.asr.identity.api.v1.validation.ChangeableRole;
import af.asr.validation.constaints.ValidIdentifier;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class Role {
    @ValidIdentifier
    @ChangeableRole
    private String identifier;

    @NotNull
    @Valid
    private List<Permission> permissions;



    public Role() {}

    public Role(
            final @Nonnull String identifier,
            final @Nonnull List<Permission> permissions) {
        Assert.notNull(identifier);
        Assert.notNull(permissions);

        this.identifier = identifier;
        this.permissions = permissions;
    }

    public void setIdentifier(String identifier) { this.identifier = identifier;}

    public String getIdentifier() {
        return identifier;
    }

    public void setPermissions(List<Permission> permissions) {this.permissions = permissions;}

    public List<Permission> getPermissions() {
        return permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(identifier, role.identifier) &&
                Objects.equals(permissions, role.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, permissions);
    }

    @Override
    public String toString() {
        return "Role{" +
                "identifier='" + identifier + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}