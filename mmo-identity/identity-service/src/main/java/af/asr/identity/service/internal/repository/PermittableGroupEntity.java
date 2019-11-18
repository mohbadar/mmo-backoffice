package af.asr.identity.service.internal.repository;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Frozen;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.List;
import java.util.Objects;


@SuppressWarnings({"unused", "WeakerAccess"})
@Table(name = PermittableGroups.TABLE_NAME)
public class PermittableGroupEntity {
    @PartitionKey
    @Column(name = PermittableGroups.IDENTIFIER_COLUMN)
    private String identifier;

    @Frozen
    @Column(name = PermittableGroups.PERMITTABLES_COLUMN)
    private List<PermittableType> permittables;

    public PermittableGroupEntity() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<PermittableType> getPermittables() {
        return permittables;
    }

    public void setPermittables(List<PermittableType> permittables) {
        this.permittables = permittables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermittableGroupEntity that = (PermittableGroupEntity) o;
        return Objects.equals(identifier, that.identifier) &&
                Objects.equals(permittables, that.permittables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, permittables);
    }

    @Override
    public String toString() {
        return "PermittableGroupEntity{" +
                "identifier='" + identifier + '\'' +
                ", permittables=" + permittables +
                '}';
    }
}