package af.asr.permittedfeignclient.service.service;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

class TokenCacheKey {
    private final String user;
    private final String tenant;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<String> endpointSet;

    TokenCacheKey(final String user, final String tenant, final @Nullable String endpointSet) {
        this.user = user;
        this.tenant = tenant;
        this.endpointSet = Optional.ofNullable(endpointSet);
    }

    String getUser() {
        return user;
    }

    String getTenant() {
        return tenant;
    }

    Optional<String> getEndpointSet() {
        return endpointSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenCacheKey that = (TokenCacheKey) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(tenant, that.tenant) &&
                Objects.equals(endpointSet, that.endpointSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, tenant, endpointSet);
    }

    @Override
    public String toString() {
        return "TokenCacheKey{" +
                "user='" + user + '\'' +
                ", tenant='" + tenant + '\'' +
                ", endpointSet='" + endpointSet + '\'' +
                '}';
    }
}