package af.gov.anar.lib.activemq.consumer;

import org.springframework.stereotype.Service;

@Service
public interface AnarConsumer<T> {

    void consume(T obj);
}
