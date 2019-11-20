package af.asr.provisioner.api.v1.domain;

import af.asr.validation.constaints.ValidApplicationName;

@SuppressWarnings({"unused", "WeakerAccess"})
public class AssignedApplication {

    @ValidApplicationName
    private String name;

    public AssignedApplication() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}