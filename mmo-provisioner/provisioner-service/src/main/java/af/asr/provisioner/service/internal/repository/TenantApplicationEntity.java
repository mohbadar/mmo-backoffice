package af.asr.provisioner.service.internal.repository;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.Set;

@Table(name = TenantApplicationEntity.TABLE_NAME)
public class TenantApplicationEntity {

    static final String TABLE_NAME = "tenant_applications";
    static final String TENANT_IDENTIFIER_COLUMN = "tenant_identifier";
    static final String ASSIGNED_APPLICATIONS_COLUMN = "assigned_applications";

    @PartitionKey
    @Column(name = TENANT_IDENTIFIER_COLUMN)
    private String tenantIdentifier;
    @Column(name = ASSIGNED_APPLICATIONS_COLUMN)
    private Set<String> applications;

    public TenantApplicationEntity() {
        super();
    }

    public String getTenantIdentifier() {
        return tenantIdentifier;
    }

    public void setTenantIdentifier(String tenantIdentifier) {
        this.tenantIdentifier = tenantIdentifier;
    }

    public Set<String> getApplications() {
        return applications;
    }

    public void setApplications(Set<String> applications) {
        this.applications = applications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TenantApplicationEntity that = (TenantApplicationEntity) o;

        return tenantIdentifier.equals(that.tenantIdentifier);

    }

    @Override
    public int hashCode() {
        return tenantIdentifier.hashCode();
    }
}