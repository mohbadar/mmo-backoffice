package af.asr.vault.service.controller;


import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.annotation.Permittable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/initialize")
public class EmptyInitializeResourcesRestController {
    @Permittable(AcceptedTokenType.SYSTEM)
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody ResponseEntity<Void> initializeTenant() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}