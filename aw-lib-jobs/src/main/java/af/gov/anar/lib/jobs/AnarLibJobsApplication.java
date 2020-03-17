package af.gov.anar.lib.jobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class AnarLibJobsApplication {

	@Scheduled(fixedRateString = "${scheduler.fixedrate.ms}")
	public static void main(String[] args) {
		SpringApplication.run(AnarLibJobsApplication.class, args);
	}

}
