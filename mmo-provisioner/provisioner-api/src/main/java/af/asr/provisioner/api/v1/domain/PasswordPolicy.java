package af.asr.provisioner.api.v1.domain;


@SuppressWarnings("unused")
public class PasswordPolicy {

    private String newPassword;
    private Integer expiresInDays;

    public PasswordPolicy() {
        super();
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Integer getExpiresInDays() {
        return expiresInDays;
    }

    public void setExpiresInDays(Integer expiresInDays) {
        this.expiresInDays = expiresInDays;
    }
}