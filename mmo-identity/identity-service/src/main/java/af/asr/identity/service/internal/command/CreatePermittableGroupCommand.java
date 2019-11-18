package af.asr.identity.service.internal.command;

import af.asr.identity.api.v1.domain.PermittableGroup;

public class CreatePermittableGroupCommand {
    private PermittableGroup instance;

    @SuppressWarnings("unused")
    public CreatePermittableGroupCommand() {
    }

    public CreatePermittableGroupCommand(final PermittableGroup instance) {
        this.instance = instance;
    }

    public PermittableGroup getInstance() {
        return instance;
    }

    public void setInstance(PermittableGroup instance) {
        this.instance = instance;
    }

    @Override
    public String toString() {
        return "CreatePermittableGroupCommand{" +
                "instance=" + instance.getIdentifier() +
                '}';
    }
}