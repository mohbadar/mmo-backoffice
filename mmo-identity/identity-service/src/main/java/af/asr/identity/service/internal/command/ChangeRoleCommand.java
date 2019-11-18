package af.asr.identity.service.internal.command;


import af.asr.identity.api.v1.domain.Role;

@SuppressWarnings("unused")
public class ChangeRoleCommand {
    private String identifier;
    private Role instance;

    public ChangeRoleCommand()
    {
    }

    public ChangeRoleCommand(final String identifier, final Role instance) {
        this.identifier = identifier;
        this.instance = instance;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Role getInstance() {
        return instance;
    }

    public void setInstance(Role instance) {
        this.instance = instance;
    }

    @Override
    public String toString() {
        return "ChangeRoleCommand{" +
                "identifier='" + identifier + '\'' +
                '}';
    }
}