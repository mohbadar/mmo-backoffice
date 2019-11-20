package af.asr.provisioner.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProvisionerApplication {

    public ProvisionerApplication() {
        super();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProvisionerServiceConfig.class, args);
    }
}