package af.asr.api.util;

import javax.annotation.Nonnull;
import java.util.Objects;

public class UserContext {
    private final String user;
    private final String accessToken;

    UserContext(@Nonnull String user, @Nonnull String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }

    @SuppressWarnings("WeakerAccess")
    @Nonnull public String getUser() {
        return user;
    }

    @SuppressWarnings("WeakerAccess")
    @Nonnull public String getAccessToken() {
        return accessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserContext that = (UserContext) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(accessToken, that.accessToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, accessToken);
    }
}