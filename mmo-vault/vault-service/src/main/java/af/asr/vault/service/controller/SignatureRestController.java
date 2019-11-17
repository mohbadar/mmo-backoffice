package af.asr.vault.service.controller;


import af.asr.ServiceException;
import af.asr.vault.api.domain.ApplicationSignatureSet;
import af.asr.vault.api.domain.Signature;
import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.annotation.Permittable;
import af.asr.vault.service.config.TenantSignatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping()
public class SignatureRestController {
    final private TenantSignatureRepository tenantSignatureRepository;

    @Autowired
    public SignatureRestController(final TenantSignatureRepository tenantSignatureRepository) {
        this.tenantSignatureRepository = tenantSignatureRepository;
    }

    @Permittable(AcceptedTokenType.SYSTEM)
    @RequestMapping(
            value = "/signatures",
            method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody ResponseEntity<List<String>> getAllSignatureSets() {
        return ResponseEntity.ok(tenantSignatureRepository.getAllSignatureSetKeyTimestamps());
    }

    @Permittable(AcceptedTokenType.SYSTEM)
    @RequestMapping(value = "/signatures/{timestamp}", method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody ResponseEntity<ApplicationSignatureSet> getSignatureSet(@PathVariable("timestamp") final String timestamp)
    {
        return tenantSignatureRepository.getSignatureSet(timestamp)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ServiceException.notFound("Signature for timestamp '" + timestamp + "' not found."));
    }

    @Permittable(AcceptedTokenType.SYSTEM)
    @RequestMapping(value = "/signatures/_latest", method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody ResponseEntity<ApplicationSignatureSet> getLatestSignatureSet()
    {
        return tenantSignatureRepository.getLatestSignatureSet()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ServiceException.notFound("No valid signature found."));
    }

    @Permittable(AcceptedTokenType.SYSTEM)
    @RequestMapping(value = "/signatures/{timestamp}", method = RequestMethod.DELETE,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody ResponseEntity<Void> deleteSignatureSet(@PathVariable("timestamp") final String timestamp)
    {
        tenantSignatureRepository.deleteSignatureSet(timestamp);
        return ResponseEntity.accepted().build();
    }

    @Permittable(AcceptedTokenType.SYSTEM)
    @RequestMapping(value = "/signatures/{timestamp}/application", method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody ResponseEntity<Signature> getApplicationSignature(@PathVariable("timestamp") final String timestamp)
    {
        return tenantSignatureRepository.getApplicationSignature(timestamp)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ServiceException.notFound("Signature for timestamp '" + timestamp + "' not found."));
    }

    @Permittable(AcceptedTokenType.SYSTEM)
    @RequestMapping(value = "/signatures/_latest/application", method = RequestMethod.GET,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody ResponseEntity<Signature> getLatestApplicationSignature()
    {
        return tenantSignatureRepository.getLatestApplicationSignature()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ServiceException.notFound("No valid signature found."));
    }
}