package af.gov.anar.sms.data.repository;

import af.gov.anar.sms.data.model.SmsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<SmsLog, Long> {
}
