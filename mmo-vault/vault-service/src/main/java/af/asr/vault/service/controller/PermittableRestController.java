package af.asr.vault.service.controller;


import af.asr.vault.api.domain.PermittableEndpoint;
import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.annotation.Permittable;
import af.asr.vault.service.service.PermittableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/permittables")
public class PermittableRestController {

    private final PermittableService service;

    @Autowired
    public PermittableRestController(final PermittableService service) {
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
    ResponseEntity<List<PermittableEndpoint>> fetchPermittableEndpoints() {
        final Set<PermittableEndpoint> permittableEndpoints = service.getPermittableEndpoints(
                Collections.singletonList(AcceptedTokenType.TENANT));

        return ResponseEntity.ok(new ArrayList<>(permittableEndpoints));
    }
}