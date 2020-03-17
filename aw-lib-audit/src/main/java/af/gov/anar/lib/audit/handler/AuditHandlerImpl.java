package af.gov.anar.lib.audit.handler;

import af.gov.anar.lib.audit.data.Audit;
import af.gov.anar.lib.audit.data.AuditRepository;
import af.gov.anar.lib.audit.data.AuditRequestDto;
import af.gov.anar.lib.audit.util.AuditUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link AuditHandler} with function to write
 */
@Service
public class AuditHandlerImpl implements AuditHandler<AuditRequestDto> {

	/**
	 * Field for {@link AuditRepository} having data access operations related to
	 * audit
	 */
	@Autowired
	private AuditRepository auditRepository;

	/**
	 * Field for {@link ModelMapper} for performing object mapping
	 */
	@Autowired
	private ModelMapper modelMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * AuditHandler#writeAudit(AuditRequest)
	 */
	@Override
	public boolean addAudit(AuditRequestDto auditRequest) {

		AuditUtils.validateAuditRequest(auditRequest);

		Audit event = modelMapper.map(auditRequest, Audit.class);
		auditRepository.save(event);
		return true;
	}

}
