package af.gov.anar.lib.audit.handler;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Interface with function to write AuditRequest
 */
@Component
public interface AuditHandler<T> {

	/**
	 * Function to write AuditRequest
	 * 
	 * @param auditRequest The AuditRequest
	 * @return true - if AuditRequest is successfully written
	 */
	boolean addAudit(T auditRequest);

}