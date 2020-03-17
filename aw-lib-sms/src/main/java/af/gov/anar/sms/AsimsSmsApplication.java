package af.gov.anar.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"af.gov.anar.sms.*"})
public class AsimsSmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsimsSmsApplication.class, args);
	}

}
