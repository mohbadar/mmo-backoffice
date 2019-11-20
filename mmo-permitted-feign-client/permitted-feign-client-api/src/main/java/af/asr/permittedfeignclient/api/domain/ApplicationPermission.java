package af.asr.permittedfeignclient.api.domain;


import af.asr.identity.api.v1.domain.Permission;
import af.asr.validation.constaints.ValidIdentifier;

import java.util.Objects;
import javax.annotation.Nullable;
import javax.validation.Valid;


@SuppressWarnings("unused")
public class ApplicationPermission {
    @ValidIdentifier
    private String endpointSetIdentifier;
    @Valid
    private Permission permission;

    public ApplicationPermission() {
    }

    public ApplicationPermission(@Nullable String endpointSetIdentifier, Permission permission) {
        this.endpointSetIdentifier = endpointSetIdentifier;
        this.permission = permission;
    }

    public String getEndpointSetIdentifier() {
        return endpointSetIdentifier;
    }

    public void setEndpointSetIdentifier(String endpointSetIdentifier) {
        this.endpointSetIdentifier = endpointSetIdentifier;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationPermission that = (ApplicationPermission) o;
        return Objects.equals(endpointSetIdentifier, that.endpointSetIdentifier) &&
                Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endpointSetIdentifier, permission);
    }

    @Override
    public String toString() {
        return "ApplicationPermission{" +
                "endpointSetIdentifier='" + endpointSetIdentifier + '\'' +
                ", permission=" + permission +
                '}';
    }
}