package af.asr.identity.service.internal.command;

import af.asr.identity.api.v1.domain.Permission;

@SuppressWarnings("unused")
public class CreateApplicationPermissionCommand {
    private String applicationIdentifer;
    private Permission permission;

    public CreateApplicationPermissionCommand() {
    }

    public CreateApplicationPermissionCommand(final String applicationIdentifier, final Permission permission) {
        this.applicationIdentifer = applicationIdentifier;
        this.permission = permission;
    }

    public String getApplicationIdentifer() {
        return applicationIdentifer;
    }

    public void setApplicationIdentifer(String applicationIdentifer) {
        this.applicationIdentifer = applicationIdentifer;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "CreateApplicationPermissionCommand{" +
                "applicationIdentifer='" + applicationIdentifer + '\'' +
                ", permission=" + permission.getPermittableEndpointGroupIdentifier() +
                '}';
    }
}