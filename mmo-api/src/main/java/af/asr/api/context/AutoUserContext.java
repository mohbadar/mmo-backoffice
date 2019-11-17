package af.asr.api.context;

import af.asr.api.util.UserContext;
import af.asr.api.util.UserContextHolder;

import java.util.Optional;


@SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "WeakerAccess"})
public class AutoUserContext implements AutoCloseable {
    private final Optional<UserContext> previousUserContext;

    public AutoUserContext(final String userName, final String accessToken) {
        previousUserContext = UserContextHolder.getUserContext();

        UserContextHolder.setAccessToken(userName, accessToken);
    }

    @Override public void close() {
        UserContextHolder.clear();
        previousUserContext.ifPresent(UserContextHolder::setUserContext);
    }
}