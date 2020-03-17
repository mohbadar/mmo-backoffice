package af.gov.anar.lib.cryptojce.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "af.gov.anar.lib.cryptojce.*" })
public class CryptoJceBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(CryptoJceBootApplication.class, args);

	}

}
