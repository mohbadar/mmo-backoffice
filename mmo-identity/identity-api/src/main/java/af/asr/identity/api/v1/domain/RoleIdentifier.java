package af.asr.identity.api.v1.domain;


import af.asr.identity.api.v1.validation.NotRootRole;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Objects;


@SuppressWarnings({"unused", "WeakerAccess"})
public class RoleIdentifier {
    @NotBlank
    @NotRootRole
    private String identifier;

    public RoleIdentifier() {
    }

    public RoleIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RoleIdentifier))
            return false;
        RoleIdentifier that = (RoleIdentifier) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override public String toString() {
        return "RoleIdentifier{" +
                "identifier='" + identifier + '\'' +
                '}';
    }
}