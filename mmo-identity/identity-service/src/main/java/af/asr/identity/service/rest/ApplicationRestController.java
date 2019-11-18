package af.asr.identity.service.rest;

import af.asr.ServiceException;
import af.asr.command.gateway.CommandGateway;
import af.asr.identity.api.v1.PermittableGroupIds;
import af.asr.identity.api.v1.domain.Permission;
import af.asr.identity.service.internal.command.CreateApplicationPermissionCommand;
import af.asr.identity.service.internal.command.DeleteApplicationCommand;
import af.asr.identity.service.internal.command.DeleteApplicationPermissionCommand;
import af.asr.identity.service.internal.command.SetApplicationSignatureCommand;
import af.asr.identity.service.internal.service.ApplicationService;
import af.asr.identity.service.internal.service.PermittableGroupService;
import af.asr.vault.api.domain.Signature;
import af.asr.vault.api.validation.ValidKeyTimestamp;
import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.annotation.Permittable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("WeakerAccess")
@RestController
@RequestMapping("/applications")
public class ApplicationRestController {
    private final ApplicationService service;
    private final PermittableGroupService permittableGroupService;
    private final CommandGateway commandGateway;

    @Autowired
    public ApplicationRestController(
            final ApplicationService service,
            final PermittableGroupService permittableGroupService,
            final CommandGateway commandGateway) {
        this.service = service;
        this.permittableGroupService = permittableGroupService;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.SYSTEM)
    public @ResponseBody
    ResponseEntity<List<String>>
    getApplications() {
        return ResponseEntity.ok(service.getAllApplications());
    }

    @RequestMapping(value = "/{applicationidentifier}/signatures/{timestamp}", method = RequestMethod.PUT,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.SYSTEM)
    public @ResponseBody
    ResponseEntity<Void>
    setApplicationSignature(@PathVariable("applicationidentifier") @Nonnull String applicationIdentifier,
                            @PathVariable("timestamp") @ValidKeyTimestamp String timestamp,
                            @RequestBody @Valid Signature signature) {
        commandGateway.process(new SetApplicationSignatureCommand(applicationIdentifier, timestamp, signature));

        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value = "/{applicationidentifier}/signatures/{timestamp}", method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.SYSTEM)
    public @ResponseBody
    ResponseEntity<Signature>
    getApplicationSignature(@PathVariable("applicationidentifier") @Nonnull String applicationIdentifier,
                            @PathVariable("timestamp") @ValidKeyTimestamp String timestamp) {
        return service.getSignatureForApplication(applicationIdentifier, timestamp)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ServiceException
                        .notFound("Signature for application ''" + applicationIdentifier + "'' and key timestamp ''" + timestamp + "'' doesn''t exist."));
    }

    @RequestMapping(value = "/{applicationidentifier}", method = RequestMethod.DELETE,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.SYSTEM)
    public @ResponseBody
    ResponseEntity<Void>
    deleteApplication(@PathVariable("applicationidentifier") @Nonnull String applicationIdentifier) {
        checkApplicationIdentifier(applicationIdentifier);
        commandGateway.process(new DeleteApplicationCommand(applicationIdentifier));
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value = "/{applicationidentifier}/permissions", method = RequestMethod.POST,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.SYSTEM)
    @Permittable(value = AcceptedTokenType.TENANT, permittedEndpoint = "applications/{applicationidentifier}/permissions", groupId = PermittableGroupIds.APPLICATION_SELF_MANAGEMENT)
    public @ResponseBody
    ResponseEntity<Void>
    createApplicationPermission(@PathVariable("applicationidentifier") @Nonnull String applicationIdentifier,
                                @RequestBody @Valid Permission permission) {
        checkApplicationIdentifier(applicationIdentifier);
        checkPermittableGroupIdentifier(permission.getPermittableEndpointGroupIdentifier());
        commandGateway.process(new CreateApplicationPermissionCommand(applicationIdentifier, permission));
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value = "/{applicationidentifier}/permissions", method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.SYSTEM)
    @Permittable(value = AcceptedTokenType.TENANT, permittedEndpoint = "applications/{applicationidentifier}/permissions", groupId = PermittableGroupIds.APPLICATION_SELF_MANAGEMENT)
    public @ResponseBody
    ResponseEntity<List<Permission>>
    getApplicationPermissions(@PathVariable("applicationidentifier") @Nonnull String applicationIdentifier) {
        checkApplicationIdentifier(applicationIdentifier);
        return ResponseEntity.ok(service.getAllPermissionsForApplication(applicationIdentifier));
    }

    @RequestMapping(value = "/{applicationidentifier}/permissions/{permissionidentifier}", method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.SYSTEM)
    public @ResponseBody
    ResponseEntity<Permission> getApplicationPermission(@PathVariable("applicationidentifier") String applicationIdentifier,
                                                        @PathVariable("permissionidentifier") String permittableEndpointGroupIdentifier) {
        return service.getPermissionForApplication(applicationIdentifier, permittableEndpointGroupIdentifier)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ServiceException.notFound("Application permission ''{0}.{1}'' doesn''t exist.",
                        applicationIdentifier, permittableEndpointGroupIdentifier));
    }

    @RequestMapping(value = "/{applicationidentifier}/permissions/{permissionidentifier}", method = RequestMethod.DELETE,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.SYSTEM)
    public @ResponseBody
    ResponseEntity<Void>
    deleteApplicationPermission(@PathVariable("applicationidentifier") @Nonnull String applicationIdentifier,
                                @PathVariable("permissionidentifier") @Nonnull String permittableEndpointGroupIdentifier)
    {
        checkApplicationPermissionIdentifier(service, applicationIdentifier, permittableEndpointGroupIdentifier);
        commandGateway.process(new DeleteApplicationPermissionCommand(applicationIdentifier, permittableEndpointGroupIdentifier));
        return ResponseEntity.accepted().build();
    }

    private void checkApplicationIdentifier(final @Nonnull String identifier) {
        if (!service.applicationExists(identifier))
            throw ServiceException.notFound("Application with identifier ''" + identifier + "'' doesn''t exist.");
    }

    static void checkApplicationPermissionIdentifier(final @Nonnull ApplicationService service,
                                                     final @Nonnull String applicationIdentifier,
                                                     final @Nonnull String permittableEndpointGroupIdentifier) {
        if (!service.applicationPermissionExists(applicationIdentifier, permittableEndpointGroupIdentifier))
            throw ServiceException.notFound("Application permission ''{0}.{1}'' doesn''t exist.",
                    applicationIdentifier, permittableEndpointGroupIdentifier);
    }

    private void checkPermittableGroupIdentifier(final String permittableEndpointGroupIdentifier) {
        permittableGroupService.findByIdentifier(permittableEndpointGroupIdentifier)
                .orElseThrow(() -> ServiceException.notFound("Permittable group ''{0}'' doesn''t exist.", permittableEndpointGroupIdentifier));
    }
}