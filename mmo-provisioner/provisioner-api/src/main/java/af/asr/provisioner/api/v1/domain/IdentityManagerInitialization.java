package af.asr.provisioner.api.v1.domain;

@SuppressWarnings("unused")
public final class IdentityManagerInitialization {

    private String adminPassword;

    public IdentityManagerInitialization() { }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}