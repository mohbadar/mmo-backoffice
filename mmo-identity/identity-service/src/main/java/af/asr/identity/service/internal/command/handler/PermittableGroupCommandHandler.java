package af.asr.identity.service.internal.command.handler;


import java.util.stream.Collectors;

import af.asr.command.annotation.Aggregate;
import af.asr.command.annotation.CommandHandler;
import af.asr.command.annotation.CommandLogLevel;
import af.asr.command.annotation.EventEmitter;
import af.asr.identity.api.v1.domain.PermittableGroup;
import af.asr.identity.api.v1.events.EventConstants;
import af.asr.identity.service.internal.command.CreatePermittableGroupCommand;
import af.asr.identity.service.internal.repository.PermittableGroupEntity;
import af.asr.identity.service.internal.repository.PermittableGroups;
import af.asr.identity.service.internal.repository.PermittableType;
import af.asr.vault.api.domain.PermittableEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@SuppressWarnings("unused")
@Aggregate
@Component
public class PermittableGroupCommandHandler {

    private final PermittableGroups repository;

    @Autowired
    public PermittableGroupCommandHandler(final PermittableGroups repository)
    {
        this.repository = repository;
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_POST_PERMITTABLE_GROUP)
    public String process(final CreatePermittableGroupCommand command) {
        Assert.isTrue(!repository.get(command.getInstance().getIdentifier()).isPresent());

        repository.add(map(command.getInstance()));

        return command.getInstance().getIdentifier();
    }

    private PermittableGroupEntity map(final PermittableGroup instance) {
        final PermittableGroupEntity ret = new PermittableGroupEntity();
        ret.setIdentifier(instance.getIdentifier());
        ret.setPermittables(instance.getPermittables().stream().map(this::map).collect(Collectors.toList()));
        return ret;
    }

    private PermittableType map(final PermittableEndpoint instance) {
        final PermittableType ret = new PermittableType();
        ret.setMethod(instance.getMethod());
        ret.setSourceGroupId(instance.getGroupId());
        ret.setPath(instance.getPath());
        return ret;
    }
}