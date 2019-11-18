package af.asr.identity.service.internal.command.handler;

import af.asr.ServiceException;
import af.asr.command.annotation.Aggregate;
import af.asr.command.annotation.CommandHandler;
import af.asr.command.annotation.CommandLogLevel;
import af.asr.command.annotation.EventEmitter;
import af.asr.identity.api.v1.events.EventConstants;
import af.asr.identity.service.internal.command.ChangeUserPasswordCommand;
import af.asr.identity.service.internal.command.ChangeUserRoleCommand;
import af.asr.identity.service.internal.command.CreateUserCommand;
import af.asr.identity.service.internal.repository.UserEntity;
import af.asr.identity.service.internal.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@SuppressWarnings("unused")
@Aggregate
@Component
public class UserCommandHandler {

    private final Users usersRepository;
    private final UserEntityCreator userEntityCreator;

    @Autowired
    UserCommandHandler(
            final Users usersRepository,
            final UserEntityCreator userEntityCreator)
    {
        this.usersRepository = usersRepository;
        this.userEntityCreator = userEntityCreator;
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_PUT_USER_ROLEIDENTIFIER)
    public String process(final ChangeUserRoleCommand command) {
        final UserEntity user = usersRepository.get(command.getIdentifier())
                .orElseThrow(() -> ServiceException.notFound(
                        "User " + command.getIdentifier() + " doesn't exist."));

        user.setRole(command.getRole());
        usersRepository.add(user);

        return user.getIdentifier();
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_PUT_USER_PASSWORD)
    public String process(final ChangeUserPasswordCommand command) {
        final UserEntity user = usersRepository.get(command.getIdentifier())
                .orElseThrow(() -> ServiceException.notFound(
                        "User " + command.getIdentifier() + " doesn't exist."));

        final UserEntity userWithNewPassword = userEntityCreator.build(
                user.getIdentifier(), user.getRole(), command.getPassword(),
                !SecurityContextHolder.getContext().getAuthentication().getName().equals(command.getIdentifier()));
        usersRepository.add(userWithNewPassword);

        return user.getIdentifier();
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_POST_USER)
    public String process(final CreateUserCommand command) {
        Assert.hasText(command.getPassword());

        final UserEntity userEntity = userEntityCreator.build(
                command.getIdentifier(), command.getRole(), command.getPassword(), true);

        usersRepository.add(userEntity);

        return command.getIdentifier();
    }
}
