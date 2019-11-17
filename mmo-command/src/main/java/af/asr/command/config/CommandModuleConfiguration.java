package af.asr.command.config;

import af.asr.ApplicationName;
import af.asr.command.util.CommandConstants;
import af.asr.config.EnableApplicationName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableApplicationName
@ComponentScan(basePackages = {
        "org.apache.fineract.cn.command.internal",
        "org.apache.fineract.cn.command.gateway"
})
public class CommandModuleConfiguration {

    private final Environment environment;

    @Autowired
    public CommandModuleConfiguration(Environment environment) {
        super();
        this.environment = environment;
    }

    @Bean(name = CommandConstants.SERIALIZER)
    public Gson gson() {
        return new GsonBuilder().create();
    }

    @Bean(name = CommandConstants.LOGGER_NAME)
    public Logger loggerBean() {
        return LoggerFactory.getLogger(CommandConstants.LOGGER_NAME);
    }

    @Bean
    public PooledConnectionFactory jmsFactory() {
        final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        final ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(
                this.environment.getProperty(
                        CommandConstants.ACTIVEMQ_BROKER_URL_PROP,
                        CommandConstants.ACTIVEMQ_BROKER_URL_DEFAULT));
        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
        return pooledConnectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactory(final PooledConnectionFactory jmsFactory) {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(jmsFactory);
        factory.setConcurrency(
                this.environment.getProperty(
                        CommandConstants.ACTIVEMQ_CONCURRENCY_PROP,
                        CommandConstants.ACTIVEMQ_CONCURRENCY_DEFAULT
                )
        );
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(final ApplicationName applicationName, final PooledConnectionFactory jmsFactory) {
        final ActiveMQTopic activeMQTopic = new ActiveMQTopic(applicationName.toString());
        final JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.setConnectionFactory(jmsFactory);
        jmsTemplate.setDefaultDestination(activeMQTopic);
        return jmsTemplate;
    }
}