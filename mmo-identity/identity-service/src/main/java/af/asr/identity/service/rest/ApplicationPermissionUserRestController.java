package af.asr.identity.service.rest;


import javax.annotation.Nonnull;

import af.asr.ServiceException;
import af.asr.command.gateway.CommandGateway;
import af.asr.identity.api.v1.PermittableGroupIds;
import af.asr.identity.service.internal.command.SetApplicationPermissionUserEnabledCommand;
import af.asr.identity.service.internal.service.ApplicationService;
import af.asr.identity.service.internal.service.UserService;
import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.annotation.Permittable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@SuppressWarnings("unused")
@RestController
@RequestMapping("/applications/{applicationidentifier}/permissions/{permissionidentifier}/users/{useridentifier}")
public class ApplicationPermissionUserRestController {
    private final ApplicationService service;
    private final UserService userService;
    private final CommandGateway commandGateway;

    @Autowired
    public ApplicationPermissionUserRestController(
            final ApplicationService service,
            final UserService userService,
            final CommandGateway commandGateway) {
        this.service = service;
        this.userService = userService;
        this.commandGateway = commandGateway;
    }

    @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
    @RequestMapping(value = "/enabled", method = RequestMethod.PUT,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<Void>
    setApplicationPermissionEnabledForUser(@PathVariable("applicationidentifier") String applicationIdentifier,
                                           @PathVariable("permissionidentifier") String permittableEndpointGroupIdentifier,
                                           @PathVariable("useridentifier") String userIdentifier,
                                           @RequestBody Boolean enabled)
    {
        ApplicationRestController.checkApplicationPermissionIdentifier(service, applicationIdentifier, permittableEndpointGroupIdentifier);
        checkUserIdentifier(userIdentifier);
        commandGateway.process(new SetApplicationPermissionUserEnabledCommand(applicationIdentifier, permittableEndpointGroupIdentifier, userIdentifier, enabled));
        return ResponseEntity.accepted().build();
    }

    @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.SELF_MANAGEMENT)
    @RequestMapping(value = "/enabled", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<Boolean>
    getApplicationPermissionEnabledForUser(@PathVariable("applicationidentifier") String applicationIdentifier,
                                           @PathVariable("permissionidentifier") String permittableEndpointGroupIdentifier,
                                           @PathVariable("useridentifier") String userIdentifier) {
        ApplicationRestController.checkApplicationPermissionIdentifier(service, applicationIdentifier, permittableEndpointGroupIdentifier);
        checkUserIdentifier(userIdentifier);
        return ResponseEntity.ok(service.applicationPermissionEnabledForUser(applicationIdentifier, permittableEndpointGroupIdentifier, userIdentifier));
    }

    private void checkUserIdentifier(final @Nonnull String identifier) {
        userService.findByIdentifier(identifier).orElseThrow(
                () -> ServiceException.notFound("User ''" + identifier + "'' doesn''t exist."));
    }
}