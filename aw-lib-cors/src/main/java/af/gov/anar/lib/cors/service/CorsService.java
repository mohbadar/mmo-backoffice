package af.gov.anar.lib.cors.service;

import af.gov.anar.lib.cors.model.CorsEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Interface with function for read and write of CORS Config
 */
@Component
public interface CorsService {

    List<String> getAllowedOrigins();
    List<String> getAllowedMethods();
    List<String> getAllowedHeaders();
    List<CorsEntity> findAll();
    CorsEntity save (CorsEntity corsEntity);
    void delete(long id);
}
