package af.asr.vault.service.controller;


import javax.validation.Valid;

import af.asr.vault.api.domain.ApplicationSignatureSet;
import af.asr.vault.api.domain.Signature;
import af.asr.vault.api.validation.ValidKeyTimestamp;
import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.annotation.Permittable;
import af.asr.vault.service.repository.TenantAuthorizationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping()
public class SignatureCreatorRestController {

    private final TenantAuthorizationDataRepository tenantAuthorizationDataRepository;

    @Autowired
    public SignatureCreatorRestController(final TenantAuthorizationDataRepository tenantAuthorizationDataRepository) {
        this.tenantAuthorizationDataRepository = tenantAuthorizationDataRepository;
    }

    @Permittable(AcceptedTokenType.SYSTEM)
    @RequestMapping(
            value = "/signatures/{timestamp}",
            method = RequestMethod.POST,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    ResponseEntity<ApplicationSignatureSet> createSignatureSet(
            @PathVariable("timestamp") @ValidKeyTimestamp final String timestamp,
            @RequestBody @Valid final Signature identityManagerSignature) {
        return ResponseEntity.ok(
                new ApplicationSignatureSet(
                        timestamp,
                        tenantAuthorizationDataRepository.createSignatureSet(timestamp, identityManagerSignature),
                        identityManagerSignature));
    }
}