package af.asr.api.context;

import af.asr.api.util.ApiConstants;

@SuppressWarnings({"WeakerAccess", "unused"})
public class AutoSeshat extends AutoUserContext{
    public AutoSeshat(String token) {
        super(ApiConstants.SYSTEM_SU, token);
    }
}