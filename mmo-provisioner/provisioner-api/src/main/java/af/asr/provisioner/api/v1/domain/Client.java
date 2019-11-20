package af.asr.provisioner.api.v1.domain;

import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
public final class Client {

    @NotNull
    private String name;
    private String description;
    private String redirectUri;
    private String vendor;
    private String homepage;

    public Client() {
        super();
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(@Nonnull final String name) {
        Assert.notNull(name, "Client name must be given!");
        this.name = name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable final String description) {
        this.description = description;
    }

    @Nullable
    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(@Nullable final String redirectUri) {
        this.redirectUri = redirectUri;
    }

    @Nullable
    public String getVendor() {
        return vendor;
    }

    public void setVendor(@Nullable final String vendor) {
        this.vendor = vendor;
    }

    @Nullable
    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(@Nullable final String homepage) {
        this.homepage = homepage;
    }
}