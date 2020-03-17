package af.gov.anar.lib.cors.init;

import af.gov.anar.lib.cors.model.CorsEntity;
import af.gov.anar.lib.cors.service.CorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CorsInitializer {

    @Value("${anar.cors.allowed-origin}: *")
    private String allowedOrigin;

    @Value("${anar.cors.allowed-method}: *")
    private String allowedMethod;


    @Value("${anar.cors.allowed-header}: *")
    private String allowedHeader;

    @Autowired
    private CorsService corsService;


    @PostConstruct
    public void init()
    {
        if(corsService.findAll().size() < 1)
        {
            CorsEntity corsEntity = CorsEntity.builder()
                    .allowedHeader(allowedOrigin)
                    .allowedMethod(allowedMethod)
                    .allowedHeader(allowedHeader)
                    .build();
            corsService.save(corsEntity);
        }

    }


}
