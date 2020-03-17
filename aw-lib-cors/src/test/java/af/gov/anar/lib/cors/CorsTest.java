package af.gov.anar.lib.cors;


import af.gov.anar.lib.cors.config.CorsConfig;
import af.gov.anar.lib.cors.model.CorsEntity;
import af.gov.anar.lib.cors.repository.CorsRepository;
import af.gov.anar.lib.cors.service.CorsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CorsConfig.class)
public class CorsTest {

    @Autowired
    private CorsService corsService;

    @MockBean
    private CorsRepository corsRepository;

    @Test
    public void corsStoreStoreTest() {

        Mockito.when(corsRepository.save(ArgumentMatchers.any(CorsEntity.class))).thenReturn(new CorsEntity());


        CorsEntity corsEntity = CorsEntity.builder()
                .allowedHeader("*")
                .allowedMethod("*")
                .allowedHeader("*")
                .build();

        CorsEntity result = corsService.save(corsEntity);

        assertTrue(result != null);
    }


}