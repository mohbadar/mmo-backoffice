package af.asr.identity.service.internal.command;

@SuppressWarnings("unused")
public class SetApplicationPermissionUserEnabledCommand {
    private String applicationIdentifier;
    private String permittableGroupIdentifier;
    private String userIdentifier;
    private boolean enabled;

    public SetApplicationPermissionUserEnabledCommand() {
    }

    public SetApplicationPermissionUserEnabledCommand(String applicationIdentifier, String permittableGroupIdentifier, String userIdentifier, boolean enabled) {
        this.applicationIdentifier = applicationIdentifier;
        this.permittableGroupIdentifier = permittableGroupIdentifier;
        this.userIdentifier = userIdentifier;
        this.enabled = enabled;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "SetApplicationPermissionUserEnabledCommand{" +
                "applicationIdentifier='" + applicationIdentifier + '\'' +
                ", permittableGroupIdentifier='" + permittableGroupIdentifier + '\'' +
                ", userIdentifier='" + userIdentifier + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
