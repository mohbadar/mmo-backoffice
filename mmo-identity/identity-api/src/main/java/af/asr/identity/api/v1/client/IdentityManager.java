package af.asr.identity.api.v1.client;


import java.util.List;
import java.util.Set;

import af.asr.api.annotation.ThrowsException;
import af.asr.api.util.CustomFeignClientsConfiguration;
import af.asr.identity.api.v1.domain.*;
import af.asr.vault.api.client.Anubis;
import af.asr.vault.api.domain.ApplicationSignatureSet;
import af.asr.vault.api.domain.Signature;
import af.asr.vault.api.validation.ValidKeyTimestamp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("unused")
@FeignClient(name="identity-v1", path="/identity/v1", configuration= CustomFeignClientsConfiguration.class)
public interface IdentityManager extends Anubis {
    String REFRESH_TOKEN = "Identity-RefreshToken";
    @RequestMapping(value = "/token?grant_type=password", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    Authentication login(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping(value = "/token?grant_type=refresh_token", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    Authentication refresh();

    @RequestMapping(value = "/token?grant_type=refresh_token", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})

        //Using a header different than the one used by anubis for authentication so that this function is called without
        //access token-based authentication checking.  The refresh token checking result is what's important here.
    Authentication refresh(@RequestHeader(REFRESH_TOKEN) String refreshToken);

    @RequestMapping(value = "/token/_current", method = RequestMethod.DELETE,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    void logout();

    @RequestMapping(value = "/permittablegroups", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ThrowsException(status = HttpStatus.CONFLICT, exception = PermittableGroupAlreadyExistsException.class)
    void createPermittableGroup(@RequestBody final PermittableGroup x);

    @RequestMapping(value = "/permittablegroups/{identifier}", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    PermittableGroup getPermittableGroup(@PathVariable("identifier") String identifier);

    @RequestMapping(value = "/permittablegroups", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    List<PermittableGroup> getPermittableGroups();

    @RequestMapping(value = "/roles", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    void createRole(@RequestBody final Role role);

    @RequestMapping(value = "/roles/{identifier}", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    void changeRole(@PathVariable("identifier") String identifier, @RequestBody final Role role);

    @RequestMapping(value = "/roles/{identifier}", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    Role getRole(@PathVariable("identifier") String identifier);

    @RequestMapping(value = "/roles", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    List<Role> getRoles();

    @RequestMapping(value = "/roles/{identifier}", method = RequestMethod.DELETE,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    void deleteRole(@PathVariable("identifier") String identifier);

    @RequestMapping(value = "/users", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ThrowsException(status = HttpStatus.CONFLICT, exception = UserAlreadyExistsException.class)
    void createUser(@RequestBody UserWithPassword user);

    @RequestMapping(value = "/users/{useridentifier}/roleIdentifier", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    void changeUserRole(@PathVariable("useridentifier") String userIdentifier, @RequestBody RoleIdentifier role);

    @RequestMapping(value = "/users/{useridentifier}/permissions", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    Set<Permission> getUserPermissions(@PathVariable("useridentifier") String userIdentifier);

    @RequestMapping(value = "/users/{useridentifier}/password", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    void changeUserPassword(@PathVariable("useridentifier") String userIdentifier, @RequestBody Password password);

    @RequestMapping(value = "/users/{useridentifier}", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    User getUser(@PathVariable("useridentifier") String userIdentifier);

    @RequestMapping(value = "/users", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    List<User> getUsers();

    @RequestMapping(value = "/applications", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    List<String> getApplications();

    @RequestMapping(value = "/applications/{applicationidentifier}/signatures/{timestamp}", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    void setApplicationSignature(@PathVariable("applicationidentifier") String applicationIdentifier,
                                 @PathVariable("timestamp") @ValidKeyTimestamp String timestamp,
                                 Signature signature);

    @RequestMapping(value = "/applications/{applicationidentifier}/signatures/{timestamp}", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    Signature getApplicationSignature(@PathVariable("applicationidentifier") String applicationIdentifier,
                                      @PathVariable("timestamp") @ValidKeyTimestamp String timestamp);

    @RequestMapping(value = "/applications/{applicationidentifier}", method = RequestMethod.DELETE,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    void deleteApplication(@PathVariable("applicationidentifier") String applicationIdentifier);

    @RequestMapping(value = "/applications/{applicationidentifier}/permissions", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    @ThrowsException(status = HttpStatus.CONFLICT, exception = ApplicationPermissionAlreadyExistsException.class)
    void createApplicationPermission(@PathVariable("applicationidentifier") String applicationIdentifier, Permission permission);

    @RequestMapping(value = "/applications/{applicationidentifier}/permissions", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    List<Permission> getApplicationPermissions(@PathVariable("applicationidentifier") String applicationIdentifier);

    @RequestMapping(value = "/applications/{applicationidentifier}/permissions/{permissionidentifier}", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    Permission getApplicationPermission(@PathVariable("applicationidentifier") String applicationIdentifier,
                                        @PathVariable("permissionidentifier") String permittableEndpointGroupIdentifier);

    @RequestMapping(value = "/applications/{applicationidentifier}/permissions/{permissionidentifier}", method = RequestMethod.DELETE,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    void deleteApplicationPermission(@PathVariable("applicationidentifier") String applicationIdentifier,
                                     @PathVariable("permissionidentifier") String permittableEndpointGroupIdentifier);

    @RequestMapping(value = "/applications/{applicationidentifier}/callendpointset", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    @ThrowsException(status = HttpStatus.CONFLICT, exception = CallEndpointSetAlreadyExistsException.class)
    void createApplicationCallEndpointSet(@PathVariable("applicationidentifier") String applicationIdentifier, CallEndpointSet callEndpointSet);

    @RequestMapping(value = "/applications/{applicationidentifier}/callendpointset/{callendpointsetidentifier}", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    void changeApplicationCallEndpointSet(@PathVariable("applicationidentifier") String applicationIdentifier,
                                          @PathVariable("callendpointsetidentifier") String callEndpointSetIdentifier,
                                          CallEndpointSet callEndpointSet);

    @RequestMapping(value = "/applications/{applicationidentifier}/callendpointset", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    List<CallEndpointSet> getApplicationCallEndpointSets(@PathVariable("applicationidentifier") String applicationIdentifier);

    @RequestMapping(value = "/applications/{applicationidentifier}/callendpointset/{callendpointsetidentifier}", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    CallEndpointSet getApplicationCallEndpointSet(@PathVariable("applicationidentifier") String applicationIdentifier,
                                                  @PathVariable("callendpointsetidentifier") String callEndpointSetIdentifier);

    @RequestMapping(value = "/applications/{applicationidentifier}/callendpointset/{callendpointsetidentifier}", method = RequestMethod.DELETE,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    void deleteApplicationCallEndpointSet(@PathVariable("applicationidentifier") String applicationIdentifier,
                                          @PathVariable("callendpointsetidentifier") String callEndpointSetIdentifier);

    @RequestMapping(value = "/applications/{applicationidentifier}/permissions/{permissionidentifier}/users/{useridentifier}/enabled", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    void setApplicationPermissionEnabledForUser(@PathVariable("applicationidentifier") String applicationIdentifier,
                                                @PathVariable("permissionidentifier") String permittableEndpointGroupIdentifier,
                                                @PathVariable("useridentifier") String userIdentifier,
                                                Boolean enabled);

    @RequestMapping(value = "/applications/{applicationidentifier}/permissions/{permissionidentifier}/users/{useridentifier}/enabled", method = RequestMethod.GET,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    boolean getApplicationPermissionEnabledForUser(@PathVariable("applicationidentifier") String applicationIdentifier,
                                                   @PathVariable("permissionidentifier") String permittableEndpointGroupIdentifier,
                                                   @PathVariable("useridentifier") String userIdentifier);

    @RequestMapping(value = "/initialize", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ApplicationSignatureSet initialize(@RequestParam("password") String password);

    @RequestMapping(value = "/signatures", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.ALL_VALUE})
    ApplicationSignatureSet createSignatureSet();
}