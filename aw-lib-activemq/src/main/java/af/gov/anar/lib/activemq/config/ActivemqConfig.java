package af.gov.anar.lib.activemq.config;


import org.springframework.context.annotation.ComponentScan;


/**
 * @JmsListener:It marks a method to be the target of a JMS messagelistener on the specified destination. In our case the destination is inbound.queue.This class is responsible to listen messsage from the inbound queue and process the same.
 */
@ComponentScan(basePackages = {"af.*"})
public class ActivemqConfig {
}
