package af.gov.anar.lib.kafka.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude = {KafkaAutoConfiguration.class})
@EnableKafka
@ComponentScan(basePackages = {"af.*"})
public class KafkaConfig {

    @Bean
    public KafkaProperties kafkaProperties()
    {
        return new KafkaProperties();
    }
}
