package af.asr.identity.service.internal.command;

import af.asr.vault.api.domain.Signature;

@SuppressWarnings("unused")
public class SetApplicationSignatureCommand {
    private String applicationIdentifier;
    private String keyTimestamp;
    private Signature signature;

    public SetApplicationSignatureCommand() {
    }

    public SetApplicationSignatureCommand(String applicationIdentifier, String keyTimestamp, Signature signature) {
        this.applicationIdentifier = applicationIdentifier;
        this.keyTimestamp = keyTimestamp;
        this.signature = signature;
    }

    public String getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }

    public String getKeyTimestamp() {
        return keyTimestamp;
    }

    public void setKeyTimestamp(String keyTimestamp) {
        this.keyTimestamp = keyTimestamp;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "SetApplicationSignatureCommand{" +
                "applicationIdentifier='" + applicationIdentifier + '\'' +
                ", keyTimestamp='" + keyTimestamp + '\'' +
                '}';
    }
}