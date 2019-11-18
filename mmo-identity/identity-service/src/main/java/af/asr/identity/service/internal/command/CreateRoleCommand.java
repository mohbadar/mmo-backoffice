package af.asr.identity.service.internal.command;

import af.asr.identity.api.v1.domain.Role;

@SuppressWarnings("unused")
public class CreateRoleCommand {
    private Role instance;

    public CreateRoleCommand() {
    }

    public CreateRoleCommand(final Role instance) {
        this.instance = instance;
    }

    public Role getInstance() {
        return instance;
    }

    public void setInstance(Role instance) {
        this.instance = instance;
    }

    @Override
    public String toString() {
        return "CreateRoleCommand{" +
                "instance=" + instance.getIdentifier() +
                '}';
    }
}