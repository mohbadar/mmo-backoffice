package af.asr.vault.service.security;


import static af.asr.vault.service.config.AnubisConstants.LOGGER_NAME;

import java.util.Set;

import af.asr.ApplicationName;
import af.asr.vault.api.RoleConstants;
import af.asr.vault.service.annotation.AcceptedTokenType;
import af.asr.vault.service.service.PermittableService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GuestAuthenticator {
    private Set<ApplicationPermission> permissions;
    private final Logger logger;
    private final ApplicationName applicationName;

    @Autowired
    public GuestAuthenticator(final PermittableService permittableService,
                              final @Qualifier(LOGGER_NAME) Logger logger,
                              final ApplicationName applicationName) {
        this.permissions = permittableService.getPermittableEndpointsAsPermissions(AcceptedTokenType.GUEST);
        this.logger = logger;
        this.applicationName = applicationName;
    }

    AnubisAuthentication authenticate(final String user) {
        if (!user.equals(RoleConstants.GUEST_USER_IDENTIFIER))
            throw AmitAuthenticationException.invalidHeader();

        logger.info("Guest access \"authenticated\" successfully.", user);

        return new AnubisAuthentication(
                null,
                RoleConstants.GUEST_USER_IDENTIFIER,
                applicationName.toString(),
                applicationName.toString(),
                permissions);
    }
}