package af.gov.anar.lib.cors.service;

import af.gov.anar.lib.cors.model.CorsEntity;
import af.gov.anar.lib.cors.repository.CorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CorsServiceImpl implements CorsService {

    @Autowired
    private CorsRepository corsRepository;


    @Override
    public List<String> getAllowedOrigins() {
        List<String> allowedOrigins = new ArrayList<>();
        for (CorsEntity corsEntity: corsRepository.findAll() ) {
            allowedOrigins.add(corsEntity.getAllowedOrigin());
        }
        return allowedOrigins;
    }

    @Override
    public List<String> getAllowedMethods() {
        List<String> allowedMethods = new ArrayList<>();
        for (CorsEntity corsEntity: corsRepository.findAll() ) {
            allowedMethods.add(corsEntity.getAllowedMethod());
        }
        return allowedMethods;
    }

    @Override
    public List<String> getAllowedHeaders() {
        List<String> allowedHeaders = new ArrayList<>();
        for (CorsEntity corsEntity: corsRepository.findAll() ) {
            allowedHeaders.add(corsEntity.getAllowedHeader());
        }
        return Arrays.asList("*");
    }

    @Override
    public List<CorsEntity> findAll() {
        return corsRepository.findAll();
    }

    @Override
    public CorsEntity save(CorsEntity corsEntity) {
        return corsRepository.save(corsEntity);
    }

    @Override
    public void delete(long id) {
        corsRepository.deleteById(id);
    }
}
