package af.asr.permittedfeignclient.service.controller;

import java.util.ArrayList;
import java.util.List;

import af.asr.permittedfeignclient.api.domain.ApplicationPermission;
import af.asr.permittedfeignclient.service.service.ApplicationPermissionRequirementsService;
import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.annotation.Permittable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requiredpermissions")
public class ApplicationPermissionRequirementsRestController {

    private final ApplicationPermissionRequirementsService service;

    @Autowired
    public ApplicationPermissionRequirementsRestController(final ApplicationPermissionRequirementsService service) {
        this.service = service;
    }

    @Permittable(AcceptedTokenType.GUEST)
    @RequestMapping(
            method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public
    @ResponseBody
    ResponseEntity<List<ApplicationPermission>> getRequiredPermissions() {
        final List<ApplicationPermission> requiredPermissions = service.getRequiredPermissions();

        return ResponseEntity.ok(new ArrayList<>(requiredPermissions));
    }
}