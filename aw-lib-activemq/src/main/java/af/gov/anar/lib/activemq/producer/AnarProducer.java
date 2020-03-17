package af.gov.anar.lib.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnarProducer<T> {

    @Autowired
    private JmsTemplate template;

    public void produce(String queue, T obj)
    {
        template.convertAndSend(queue, obj);
    }
}
