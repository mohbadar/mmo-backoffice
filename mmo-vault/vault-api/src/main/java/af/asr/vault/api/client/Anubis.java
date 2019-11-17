package af.asr.vault.api.client;


import af.asr.vault.api.domain.ApplicationSignatureSet;
import af.asr.vault.api.domain.PermittableEndpoint;
import af.asr.vault.api.domain.Signature;
import af.asr.vault.api.validation.ValidKeyTimestamp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@SuppressWarnings("WeakerAccess")
@FeignClient
public interface Anubis {
    @RequestMapping(
            value = "/permittables",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.ALL_VALUE
    )
    List<PermittableEndpoint> getPermittableEndpoints();

    @RequestMapping(value = "/signatures", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    List<String> getAllSignatureSets();

    @RequestMapping(value = "/signatures/{timestamp}", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    ApplicationSignatureSet createSignatureSet(@PathVariable("timestamp") @ValidKeyTimestamp String timestamp,
                                               @RequestBody Signature identityManagerSignature);

    @RequestMapping(value = "/signatures/{timestamp}", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    ApplicationSignatureSet getSignatureSet(@PathVariable("timestamp") String timestamp);

    @RequestMapping(value = "/signatures/_latest", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    ApplicationSignatureSet getLatestSignatureSet();

    @RequestMapping(value = "/signatures/{timestamp}", method = RequestMethod.DELETE,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    void deleteSignatureSet(@PathVariable("timestamp") String timestamp);

    @RequestMapping(value = "/signatures/{timestamp}/application", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    Signature getApplicationSignature(@PathVariable("timestamp") String timestamp);

    @RequestMapping(value = "/signatures/_latest/application", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    Signature getLatestApplicationSignature();

    @RequestMapping(value = "/initialize", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    void initializeResources();
}