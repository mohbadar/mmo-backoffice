package af.gov.anar.lib.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class AnarKafkaProducer<T> {

    @Autowired
    private KafkaTemplate<String, T> template;

    public ListenableFuture<SendResult<String, T>> produce(String topic, T obj)
    {
        return template.send(topic, obj);
    }
}
