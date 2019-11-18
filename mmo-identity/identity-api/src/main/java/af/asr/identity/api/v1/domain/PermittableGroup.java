package af.asr.identity.api.v1.domain;


import af.asr.validation.constaints.ValidIdentifier;
import af.asr.vault.api.domain.PermittableEndpoint;

import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@SuppressWarnings({"WeakerAccess", "unused"})
public class PermittableGroup {
    @ValidIdentifier
    private String identifier;

    @NotNull
    @Valid
    private List<PermittableEndpoint> permittables;


    public PermittableGroup() {
    }

    public PermittableGroup(String identifier, List<PermittableEndpoint> permittables) {
        this.identifier = identifier;
        this.permittables = permittables;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<PermittableEndpoint> getPermittables() {
        return permittables;
    }

    public void setPermittables(List<PermittableEndpoint> permittables) {
        this.permittables = permittables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermittableGroup that = (PermittableGroup) o;
        return Objects.equals(identifier, that.identifier) &&
                Objects.equals(permittables, that.permittables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, permittables);
    }

    @Override
    public String toString() {
        return "PermittableGroup{" +
                "identifier='" + identifier + '\'' +
                ", permittables=" + permittables +
                '}';
    }
}