package af.asr.identity.api.v1.domain;


import af.asr.validation.constaints.ValidIdentifier;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.ScriptAssert;

import java.util.Objects;

@SuppressWarnings({"unused", "WeakerAccess"})
@ScriptAssert(lang = "javascript", script = "_this.identifier !== \"guest\" && _this.identifier !== \"seshat\" && _this.identifier !== \"system\" && _this.identifier !== \"wepemnefret\"" )
public class UserWithPassword {
    @ValidIdentifier
    private String identifier;

    @ValidIdentifier
    private String role;

    @NotBlank
    @Length(min = 8)
    private String password;

    public UserWithPassword()
    {
        super();
    }

    public UserWithPassword(final String identifier, final String role, final String password) {
        this.identifier = identifier;
        this.role = role;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserWithPassword))
            return false;
        UserWithPassword that = (UserWithPassword) o;
        return Objects.equals(identifier, that.identifier) &&
                Objects.equals(role, that.role) &&
                Objects.equals(password, that.password);
    }

    @Override public int hashCode() {
        return Objects.hash(identifier, role, password);
    }

    @Override public String toString() {
        return "UserWithPassword{" +
                "identifier='" + identifier + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}