package af.asr.identity.api.v1.domain;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

import af.asr.validation.constaints.ValidIdentifier;


@SuppressWarnings({"unused", "WeakerAccess"})
public class CallEndpointSet {
    @ValidIdentifier
    private String identifier;

    @NotNull
    private List<String> permittableEndpointGroupIdentifiers;

    public CallEndpointSet() {
    }

    public CallEndpointSet(String identifier, List<String> permittableEndpointGroupIdentifiers) {
        this.identifier = identifier;
        this.permittableEndpointGroupIdentifiers = permittableEndpointGroupIdentifiers;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<String> getPermittableEndpointGroupIdentifiers() {
        return permittableEndpointGroupIdentifiers;
    }

    public void setPermittableEndpointGroupIdentifiers(List<String> permittableEndpointGroupIdentifiers) {
        this.permittableEndpointGroupIdentifiers = permittableEndpointGroupIdentifiers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallEndpointSet that = (CallEndpointSet) o;
        return Objects.equals(identifier, that.identifier) &&
                Objects.equals(permittableEndpointGroupIdentifiers, that.permittableEndpointGroupIdentifiers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, permittableEndpointGroupIdentifiers);
    }

    @Override
    public String toString() {
        return "CallEndpointSet{" +
                "identifier='" + identifier + '\'' +
                ", permittableEndpointGroupIdentifiers=" + permittableEndpointGroupIdentifiers +
                '}';
    }
}