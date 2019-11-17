package af.asr.vault.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@SuppressWarnings("unused")
@ConfigurationProperties(prefix="anubis")
@Validated
public class AnubisProperties {
    private Boolean acceptGuestTokensForSystemEndpoints = false;

    public Boolean getAcceptGuestTokensForSystemEndpoints() {
        return acceptGuestTokensForSystemEndpoints;
    }

    public void setAcceptGuestTokensForSystemEndpoints(Boolean acceptGuestTokensForSystemEndpoints) {
        this.acceptGuestTokensForSystemEndpoints = acceptGuestTokensForSystemEndpoints;
    }
}