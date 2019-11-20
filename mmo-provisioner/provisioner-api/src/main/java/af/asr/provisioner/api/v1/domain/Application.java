package af.asr.provisioner.api.v1.domain;

import af.asr.validation.constaints.ValidApplicationName;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Application {

    @ValidApplicationName
    private String name;
    private String description;
    private String vendor;
    private String homepage;

    public Application() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}