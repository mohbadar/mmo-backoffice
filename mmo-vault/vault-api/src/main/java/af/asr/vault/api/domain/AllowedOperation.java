package af.asr.vault.api.domain;


import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"WeakerAccess", "unused"})
public enum AllowedOperation {
    @SerializedName("READ")
    READ {
        @Override public boolean containsHttpMethod(String httpMethod) {
            return httpMethod.equalsIgnoreCase("get") || httpMethod.equalsIgnoreCase("head");
        }
    }, //GET, HEAD
    @SerializedName("CHANGE")
    CHANGE {
        @Override public boolean containsHttpMethod(String httpMethod) {
            return httpMethod.equalsIgnoreCase("post") || httpMethod.equalsIgnoreCase("put");
        }
    }, //POST, PUT
    @SerializedName("DELETE")
    DELETE {
        @Override public boolean containsHttpMethod(String httpMethod) {
            return httpMethod.equalsIgnoreCase("delete");
        }
    }; //DELETE

    public static final Set<AllowedOperation> ALL = Collections.unmodifiableSet(
            new HashSet<AllowedOperation>() {{add(READ); add(CHANGE); add(DELETE);}});

    public abstract boolean containsHttpMethod(final String httpMethod);
}
