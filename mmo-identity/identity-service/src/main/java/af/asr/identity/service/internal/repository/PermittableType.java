package af.asr.identity.service.internal.repository;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import java.util.Objects;

@SuppressWarnings({"unused", "WeakerAccess"})
@UDT(name = PermittableGroups.TYPE_NAME)
public class PermittableType {

    @Field(name = PermittableGroups.PATH_FIELD)
    private String path;
    @Field(name = PermittableGroups.METHOD_FIELD)
    private String method;
    @Field(name = PermittableGroups.SOURCE_GROUP_ID_FIELD)
    private String sourceGroupId;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSourceGroupId() {
        return sourceGroupId;
    }

    public void setSourceGroupId(String sourceGroupId) {
        this.sourceGroupId = sourceGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermittableType that = (PermittableType) o;
        return Objects.equals(path, that.path) &&
                Objects.equals(method, that.method) &&
                Objects.equals(sourceGroupId, that.sourceGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, method, sourceGroupId);
    }

    @Override
    public String toString() {
        return "PermittableType{" +
                "path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", sourceGroupId='" + sourceGroupId + '\'' +
                '}';
    }
}