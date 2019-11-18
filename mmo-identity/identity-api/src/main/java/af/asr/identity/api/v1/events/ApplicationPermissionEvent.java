package af.asr.identity.api.v1.events;

import java.util.Objects;

@SuppressWarnings({"unused", "WeakerAccess"})
public class ApplicationPermissionEvent {
    private String applicationIdentifier;
    private String permittableGroupIdentifier;

    public ApplicationPermissionEvent() {
    }

    public ApplicationPermissionEvent(String applicationIdentifier, String permittableGroupIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
        this.permittableGroupIdentifier = permittableGroupIdentifier;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationPermissionEvent that = (ApplicationPermissionEvent) o;
        return Objects.equals(applicationIdentifier, that.applicationIdentifier) &&
                Objects.equals(permittableGroupIdentifier, that.permittableGroupIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationIdentifier, permittableGroupIdentifier);
    }

    @Override
    public String toString() {
        return "ApplicationPermissionEvent{" +
                "applicationIdentifier='" + applicationIdentifier + '\'' +
                ", permittableGroupIdentifier='" + permittableGroupIdentifier + '\'' +
                '}';
    }
}