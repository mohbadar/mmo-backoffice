package af.asr.data.jpa.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataSourceInstanceRepository extends JpaRepository<DataSourceInstance, String> {
}