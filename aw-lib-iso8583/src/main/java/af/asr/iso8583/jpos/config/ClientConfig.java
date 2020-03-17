package af.asr.iso8583.jpos.config;

import af.asr.iso8583.jpos.contract.Client;
import af.asr.iso8583.jpos.service.ClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
    @Bean(name="client")
    public Client connect() {
        return new ClientImpl();
    }
}