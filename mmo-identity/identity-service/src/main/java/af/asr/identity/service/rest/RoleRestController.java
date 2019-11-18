package af.asr.identity.service.rest;

import java.util.List;
import javax.validation.Valid;

import af.asr.ServiceException;
import af.asr.command.gateway.CommandGateway;
import af.asr.identity.api.v1.PermittableGroupIds;
import af.asr.identity.api.v1.domain.Role;
import af.asr.identity.api.v1.validation.CheckRoleChangeable;
import af.asr.identity.service.internal.command.ChangeRoleCommand;
import af.asr.identity.service.internal.command.CreateRoleCommand;
import af.asr.identity.service.internal.command.DeleteRoleCommand;
import af.asr.identity.service.internal.service.RoleService;
import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.annotation.Permittable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/roles")
public class RoleRestController
{
    private final RoleService service;
    private final CommandGateway commandGateway;

    @Autowired public RoleRestController(
            final CommandGateway commandGateway,
            final RoleService service)
    {
        this.commandGateway = commandGateway;
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.ROLE_MANAGEMENT)
    public @ResponseBody ResponseEntity<Void> create(@RequestBody @Valid final Role instance)
    {
        if (instance == null)
            throw ServiceException.badRequest("Instance may not be null.");

        if (service.findByIdentifier(instance.getIdentifier()).isPresent())
            throw ServiceException.conflict("Instance already exists with identifier:" + instance.getIdentifier());

        final CreateRoleCommand createCommand = new CreateRoleCommand(instance);
        this.commandGateway.process(createCommand);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.ROLE_MANAGEMENT)
    public @ResponseBody List<Role> findAll() {
        return service.findAll();
    }

    @RequestMapping(value= PathConstants.IDENTIFIER_RESOURCE_STRING, method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.ROLE_MANAGEMENT)
    public @ResponseBody ResponseEntity<Role> get(@PathVariable(PathConstants.IDENTIFIER_PATH_VARIABLE) final String identifier)
    {
        return new ResponseEntity<>(checkIdentifier(identifier), HttpStatus.OK);
    }

    @RequestMapping(value= PathConstants.IDENTIFIER_RESOURCE_STRING, method = RequestMethod.DELETE,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.ROLE_MANAGEMENT)
    public @ResponseBody ResponseEntity<Void> delete(@PathVariable(PathConstants.IDENTIFIER_PATH_VARIABLE) final String identifier)
    {
        if (!CheckRoleChangeable.isChangeableRoleIdentifier(identifier))
            throw ServiceException.badRequest("Role with identifier: " + identifier + " cannot be deleted.");

        checkIdentifier(identifier);

        final DeleteRoleCommand deleteCommand = new DeleteRoleCommand(identifier);
        this.commandGateway.process(deleteCommand);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value= PathConstants.IDENTIFIER_RESOURCE_STRING,method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.ROLE_MANAGEMENT)
    public @ResponseBody ResponseEntity<Void> change(
            @PathVariable(PathConstants.IDENTIFIER_PATH_VARIABLE) final String identifier, @RequestBody @Valid final Role instance)
    {
        if (!CheckRoleChangeable.isChangeableRoleIdentifier(identifier))
            throw ServiceException.badRequest("Role with identifier: " + identifier + " cannot be changed.");

        checkIdentifier(identifier);

        if (!identifier.equals(instance.getIdentifier()))
            throw ServiceException.badRequest("Instance identifiers may not be changed.");

        final ChangeRoleCommand changeCommand = new ChangeRoleCommand(identifier, instance);
        this.commandGateway.process(changeCommand);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    private Role checkIdentifier(final String identifier) {
        if (identifier == null)
            throw ServiceException.badRequest("identifier may not be null.");

        return service.findByIdentifier(identifier)
                .orElseThrow(() -> ServiceException.notFound("Instance with identifier " + identifier + " doesn't exist."));
    }
}