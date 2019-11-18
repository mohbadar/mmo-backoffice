package af.asr.identity.api.v1.domain;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.ScriptAssert;

import java.util.Objects;


@SuppressWarnings({"WeakerAccess", "unused"})
@ScriptAssert(lang = "javascript", script = "_this.identifier !== \"guest\" && _this.identifier !== \"seshat\" && _this.identifier !== \"system\" && _this.identifier !== \"wepemnefret\"" )
public class User {
    @NotBlank
    @Length(min = 4, max = 32)
    private String identifier;

    @NotBlank
    private String role;

    public User() { }

    public User(final String identifier, final String role) {
        this.identifier = identifier;
        this.role = role;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(identifier, user.identifier) && Objects.equals(role, user.role);
    }

    @Override public int hashCode() {
        return Objects.hash(identifier, role);
    }

    @Override public String toString() {
        return "User{" +
                "identifier='" + identifier + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}