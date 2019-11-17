package af.asr.vault.service.provider;

public class InvalidKeyTimestampException extends Exception {
    public InvalidKeyTimestampException(final String version) {
        super("Invalid key version: " +  version);
    }
}