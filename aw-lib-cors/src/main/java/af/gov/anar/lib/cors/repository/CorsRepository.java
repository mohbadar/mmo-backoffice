package af.gov.anar.lib.cors.repository;

import af.gov.anar.lib.cors.model.CorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsRepository extends JpaRepository<CorsEntity, Long> {
}
