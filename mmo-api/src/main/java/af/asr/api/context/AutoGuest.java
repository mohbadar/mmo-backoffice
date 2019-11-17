package af.asr.api.context;


@SuppressWarnings("unused")
public class AutoGuest extends AutoUserContext {
    public AutoGuest() {
        super("guest", "N/A");
    }
}