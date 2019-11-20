package af.asr.permittedfeignclient.api.client;


import af.asr.permittedfeignclient.api.domain.ApplicationPermission;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@SuppressWarnings({"WeakerAccess", "unused"})
@FeignClient
public interface ApplicationPermissionRequirements {
    @RequestMapping(
            value = "/requiredpermissions",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.ALL_VALUE
    )
    List<ApplicationPermission> getRequiredPermissions();
}