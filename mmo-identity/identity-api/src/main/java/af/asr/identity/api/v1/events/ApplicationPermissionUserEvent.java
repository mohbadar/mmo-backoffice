package af.asr.identity.api.v1.events;

import java.util.Objects;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ApplicationPermissionUserEvent {
    private String applicationIdentifier;
    private String permittableGroupIdentifier;
    private String userIdentifier;

    public ApplicationPermissionUserEvent() {
    }

    public ApplicationPermissionUserEvent(String applicationIdentifier, String permittableGroupIdentifier, String userIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
        this.permittableGroupIdentifier = permittableGroupIdentifier;
        this.userIdentifier = userIdentifier;
    }

    public String getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }

    public String getPermittableGroupIdentifier() {
        return permittableGroupIdentifier;
    }

    public void setPermittableGroupIdentifier(String permittableGroupIdentifier) {
        this.permittableGroupIdentifier = permittableGroupIdentifier;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationPermissionUserEvent that = (ApplicationPermissionUserEvent) o;
        return Objects.equals(applicationIdentifier, that.applicationIdentifier) &&
                Objects.equals(permittableGroupIdentifier, that.permittableGroupIdentifier) &&
                Objects.equals(userIdentifier, that.userIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationIdentifier, permittableGroupIdentifier, userIdentifier);
    }

    @Override
    public String toString() {
        return "ApplicationPermissionUserEvent{" +
                "applicationIdentifier='" + applicationIdentifier + '\'' +
                ", permittableGroupIdentifier='" + permittableGroupIdentifier + '\'' +
                ", userIdentifier='" + userIdentifier + '\'' +
                '}';
    }
}