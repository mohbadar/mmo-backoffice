package af.asr.identity.service.internal.command.handler;


import af.asr.command.annotation.Aggregate;
import af.asr.command.annotation.CommandHandler;
import af.asr.command.annotation.CommandLogLevel;
import af.asr.command.annotation.EventEmitter;
import af.asr.identity.api.v1.events.ApplicationPermissionEvent;
import af.asr.identity.api.v1.events.ApplicationPermissionUserEvent;
import af.asr.identity.api.v1.events.ApplicationSignatureEvent;
import af.asr.identity.api.v1.events.EventConstants;
import af.asr.identity.service.internal.command.CreateApplicationPermissionCommand;
import af.asr.identity.service.internal.command.DeleteApplicationCommand;
import af.asr.identity.service.internal.command.DeleteApplicationPermissionCommand;
import af.asr.identity.service.internal.command.SetApplicationSignatureCommand;
import af.asr.identity.service.internal.mapper.PermissionMapper;
import af.asr.identity.service.internal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Aggregate
@Component
public class ApplicationCommandHandler {
    private final ApplicationSignatures applicationSignatures;
    private final ApplicationPermissions applicationPermissions;
    private final ApplicationPermissionUsers applicationPermissionUsers;
    private final ApplicationCallEndpointSets applicationCallEndpointSets;

    @Autowired
    public ApplicationCommandHandler(final ApplicationSignatures applicationSignatures,
                                     final ApplicationPermissions applicationPermissions,
                                     final ApplicationPermissionUsers applicationPermissionUsers,
                                     final ApplicationCallEndpointSets applicationCallEndpointSets) {
        this.applicationSignatures = applicationSignatures;
        this.applicationPermissions = applicationPermissions;
        this.applicationPermissionUsers = applicationPermissionUsers;
        this.applicationCallEndpointSets = applicationCallEndpointSets;
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_PUT_APPLICATION_SIGNATURE)
    public ApplicationSignatureEvent process(final SetApplicationSignatureCommand command) {
        final ApplicationSignatureEntity applicationSignatureEntity = new ApplicationSignatureEntity();
        applicationSignatureEntity.setApplicationIdentifier(command.getApplicationIdentifier());
        applicationSignatureEntity.setKeyTimestamp(command.getKeyTimestamp());
        applicationSignatureEntity.setPublicKeyMod(command.getSignature().getPublicKeyMod());
        applicationSignatureEntity.setPublicKeyExp(command.getSignature().getPublicKeyExp());
        applicationSignatures.add(applicationSignatureEntity);

        return new ApplicationSignatureEvent(command.getApplicationIdentifier(), command.getKeyTimestamp());
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_DELETE_APPLICATION)
    public String process(final DeleteApplicationCommand command) {
        applicationSignatures.delete(command.getApplicationIdentifier());
        return command.getApplicationIdentifier();
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_POST_APPLICATION_PERMISSION)
    public ApplicationPermissionEvent process(final CreateApplicationPermissionCommand command) {
        final ApplicationPermissionEntity applicationPermissionEntity = new ApplicationPermissionEntity(
                command.getApplicationIdentifer(), PermissionMapper.mapToPermissionType(command.getPermission()));

        applicationPermissions.add(applicationPermissionEntity);
        return new ApplicationPermissionEvent(command.getApplicationIdentifer(), command.getPermission().getPermittableEndpointGroupIdentifier());
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_DELETE_APPLICATION_PERMISSION)
    public ApplicationPermissionEvent process(final DeleteApplicationPermissionCommand command) {
        applicationPermissions.delete(command.getApplicationIdentifier(), command.getPermittableGroupIdentifier());
        return new ApplicationPermissionEvent(command.getApplicationIdentifier(), command.getPermittableGroupIdentifier());
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_PUT_APPLICATION_PERMISSION_USER_ENABLED)
    public ApplicationPermissionUserEvent process(final SetApplicationPermissionUserEnabledCommand command) {
        applicationPermissionUsers.setEnabled(command.getApplicationIdentifier(), command.getPermittableGroupIdentifier(), command.getUserIdentifier(), command.isEnabled());
        return new ApplicationPermissionUserEvent(command.getApplicationIdentifier(), command.getPermittableGroupIdentifier(), command.getUserIdentifier());
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_PUT_APPLICATION_CALLENDPOINTSET)
    public ApplicationCallEndpointSetEvent process(final ChangeApplicationCallEndpointSetCommand command) {
        applicationCallEndpointSets.get(command.getApplicationIdentifier(), command.getCallEndpointSetIdentifier())
                .orElseThrow(() -> ServiceException.notFound("No application call endpoint '"
                        + command.getApplicationIdentifier() + "." + command.getCallEndpointSetIdentifier() + "'."));

        final ApplicationCallEndpointSetEntity toSave  = ApplicationCallEndpointSetMapper.mapToEntity(
                command.getApplicationIdentifier(),
                command.getCallEndpointSet());
        applicationCallEndpointSets.change(toSave);
        return new ApplicationCallEndpointSetEvent(command.getApplicationIdentifier(), command.getCallEndpointSetIdentifier());
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_POST_APPLICATION_CALLENDPOINTSET)
    public ApplicationCallEndpointSetEvent process(final CreateApplicationCallEndpointSetCommand command) {
        if (!applicationSignatures.signaturesExistForApplication(command.getApplicationIdentifier()))
            throw ServiceException.notFound("No application '" + command.getApplicationIdentifier() + "'.");

        final ApplicationCallEndpointSetEntity toSave  = ApplicationCallEndpointSetMapper.mapToEntity(
                command.getApplicationIdentifier(),
                command.getCallEndpointSet());
        applicationCallEndpointSets.add(toSave);
        return new ApplicationCallEndpointSetEvent(command.getApplicationIdentifier(), command.getCallEndpointSet().getIdentifier());
    }

    @CommandHandler(logStart = CommandLogLevel.INFO, logFinish = CommandLogLevel.INFO)
    @EventEmitter(selectorName = EventConstants.OPERATION_HEADER, selectorValue = EventConstants.OPERATION_DELETE_APPLICATION_CALLENDPOINTSET)
    public ApplicationCallEndpointSetEvent process(final DeleteApplicationCallEndpointSetCommand command) {
        applicationCallEndpointSets.get(command.getApplicationIdentifier(), command.getCallEndpointSetIdentifier())
                .orElseThrow(() -> ServiceException.notFound("No application call endpoint '"
                        + command.getApplicationIdentifier() + "." + command.getCallEndpointSetIdentifier() + "'."));

        applicationCallEndpointSets.delete(command.getApplicationIdentifier(), command.getCallEndpointSetIdentifier());
        return new ApplicationCallEndpointSetEvent(command.getApplicationIdentifier(), command.getCallEndpointSetIdentifier());
    }
}