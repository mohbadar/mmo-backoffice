package af.asr.api.util;

@SuppressWarnings("WeakerAccess")
public class NotFoundException extends RuntimeException {

    public NotFoundException(final String reason) {
        super(reason);
    }
}