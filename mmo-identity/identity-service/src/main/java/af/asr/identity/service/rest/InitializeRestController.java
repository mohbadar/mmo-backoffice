package af.asr.identity.service.rest;

import af.asr.identity.service.internal.command.handler.Provisioner;
import af.asr.identity.service.internal.service.TenantService;
import af.asr.vault.api.domain.ApplicationSignatureSet;
import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.annotation.Permittable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping()
public class InitializeRestController {
    private final TenantService tenantService;
    private final Provisioner provisioner;

    @Autowired
    InitializeRestController(
            final TenantService tenantService,
            final Provisioner provisioner)
    {
        this.tenantService = tenantService;
        this.provisioner = provisioner;
    }

    @RequestMapping(value = "/initialize",
            method = RequestMethod.POST,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(AcceptedTokenType.SYSTEM)
    public @ResponseBody ResponseEntity<ApplicationSignatureSet> initializeTenant(
            @RequestParam("password") final String adminPassword)
    {
        final ApplicationSignatureSet newSignatureSet = provisioner.provisionTenant(adminPassword);
        return new ResponseEntity<>(newSignatureSet, HttpStatus.OK);
    }

    @RequestMapping(value = "/signatures",
            method = RequestMethod.POST,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permittable(AcceptedTokenType.SYSTEM)
    public @ResponseBody ResponseEntity<ApplicationSignatureSet> createSignatureSet() {
        return ResponseEntity.ok(tenantService.createSignatureSet());
    }
}