package af.asr.identity.service.rest;

import af.asr.ServiceException;
import af.asr.command.gateway.CommandGateway;
import af.asr.identity.api.v1.PermittableGroupIds;
import af.asr.identity.api.v1.domain.PermittableGroup;
import af.asr.identity.service.internal.command.CreatePermittableGroupCommand;
import af.asr.identity.service.internal.service.PermittableGroupService;
import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.annotation.Permittable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@SuppressWarnings("unused")
@RestController
@RequestMapping("/permittablegroups")
public class PermittableGroupRestController {
    private final PermittableGroupService service;
    private final CommandGateway commandGateway;

    public PermittableGroupRestController(final PermittableGroupService service,
                                          final CommandGateway commandGateway) {
        this.service = service;
        this.commandGateway = commandGateway;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.SYSTEM)
    public @ResponseBody
    ResponseEntity<Void> create(@RequestBody @Valid final PermittableGroup instance)
    {
        if (instance == null)
            throw ServiceException.badRequest("Instance may not be null.");

        if (service.findByIdentifier(instance.getIdentifier()).isPresent())
            throw ServiceException.conflict("Instance already exists with identifier:" + instance.getIdentifier());

        final CreatePermittableGroupCommand createCommand = new CreatePermittableGroupCommand(instance);
        this.commandGateway.process(createCommand);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.ROLE_MANAGEMENT)
    public @ResponseBody
    List<PermittableGroup> findAll() {
        return service.findAll();
    }

    @RequestMapping(value= PathConstants.IDENTIFIER_RESOURCE_STRING, method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(value = AcceptedTokenType.SYSTEM)
    @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.ROLE_MANAGEMENT)
    public @ResponseBody ResponseEntity<PermittableGroup> get(@PathVariable(PathConstants.IDENTIFIER_PATH_VARIABLE) final String identifier)
    {
        return new ResponseEntity<>(checkIdentifier(identifier), HttpStatus.OK);
    }

    private PermittableGroup checkIdentifier(final String identifier) {
        if (identifier == null)
            throw ServiceException.badRequest("identifier may not be null.");

        return service.findByIdentifier(identifier)
                .orElseThrow(() -> ServiceException.notFound("Instance with identifier " + identifier + " doesn't exist."));
    }
}