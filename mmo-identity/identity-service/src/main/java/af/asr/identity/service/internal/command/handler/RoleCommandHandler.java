package af.asr.identity.service.internal.command.handler;

import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

import af.asr.command.annotation.Aggregate;
import af.asr.command.annotation.CommandHandler;
import af.asr.command.annotation.CommandLogLevel;
import af.asr.command.annotation.EventEmitter;
import af.asr.identity.api.v1.domain.Role;
import af.asr.identity.api.v1.events.EventConstants;
import af.asr.identity.service.internal.command.ChangeRoleCommand;
import af.asr.identity.service.internal.command.CreateRoleCommand;
import af.asr.identity.service.internal.command.DeleteRoleCommand;
import af.asr.identity.service.internal.mapper.PermissionMapper;
import af.asr.identity.service.internal.repository.RoleEntity;
import af.asr.identity.service.internal.repository.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@SuppressWarnings("unused")
@Aggregate
@Component
public class RoleCommandHandler {

    private final Roles roles;

    @Autowired
    public RoleCommandHandler(final Roles roles)
    {
        this.roles = roles;
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_PUT_ROLE)
    public String process(final ChangeRoleCommand command) {
        final Optional<RoleEntity> instance = roles.get(command.getIdentifier());
        Assert.isTrue(instance.isPresent());

        instance.ifPresent(x -> roles.change(mapRole(command.getInstance())));

        return command.getInstance().getIdentifier();
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_POST_ROLE)
    public String process(final CreateRoleCommand command) {
        Assert.isTrue(!roles.get(command.getInstance().getIdentifier()).isPresent());

        roles.add(mapRole(command.getInstance()));

        return command.getInstance().getIdentifier();
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_DELETE_ROLE)
    public String process(final DeleteRoleCommand command) {
        final Optional<RoleEntity> instance = roles.get(command.getIdentifier());
        Assert.isTrue(instance.isPresent());

        instance.ifPresent(roles::delete);

        return command.getIdentifier();
    }

    private @Nonnull RoleEntity mapRole(
            @Nonnull final Role role) {
        final RoleEntity ret = new RoleEntity();
        ret.setIdentifier(role.getIdentifier());
        ret.setPermissions(role.getPermissions().stream()
                .map(PermissionMapper::mapToPermissionType)
                .collect(Collectors.toList()));

        return ret;
    }
}