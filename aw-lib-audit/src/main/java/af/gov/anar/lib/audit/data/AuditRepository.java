
package af.gov.anar.lib.audit.data;

import af.gov.anar.lib.audit.data.Audit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Repository interface with data access and data modification functions on
 * {@link Audit}
 */
@Repository
public interface AuditRepository extends JpaRepository<Audit, String> {
}
