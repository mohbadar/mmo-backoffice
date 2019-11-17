package af.asr.api.util;


import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.Optional;

@SuppressWarnings({"WeakerAccess", "unused"})
public class UserContextHolder {

    private static final InheritableThreadLocal<UserContext> THREAD_LOCAL = new InheritableThreadLocal<>();

    private UserContextHolder() {
    }

    @Nonnull
    public static String checkedGetAccessToken() {
        return Optional.ofNullable(UserContextHolder.THREAD_LOCAL.get())
                .map(UserContext::getAccessToken)
                .orElseThrow(IllegalStateException::new);
    }

    @Nonnull
    public static String checkedGetUser() {
        return Optional.ofNullable(UserContextHolder.THREAD_LOCAL.get())
                .map(UserContext::getUser)
                .map(UserContextHolder::cropIdentifier)
                .orElseThrow(IllegalStateException::new);
    }

    private static String cropIdentifier(final String identifier) {
        if (identifier.length() > 32)
            return identifier.substring(0, 32);
        else
            return identifier;
    }

    @Nonnull
    public static Optional<UserContext> getUserContext() {
        return Optional.ofNullable(UserContextHolder.THREAD_LOCAL.get());
    }

    public static void setAccessToken(@Nonnull final String user, @Nonnull final String accessToken) {
        Assert.notNull(user, "User may not be null.");
        Assert.notNull(accessToken, "Access token may not be null.");
        UserContextHolder.THREAD_LOCAL.set(new UserContext(user, accessToken));
    }

    public static void setUserContext(@Nonnull final UserContext userContext)
    {
        UserContextHolder.THREAD_LOCAL.set(userContext);
    }

    public static void clear() {
        UserContextHolder.THREAD_LOCAL.remove();
    }
}