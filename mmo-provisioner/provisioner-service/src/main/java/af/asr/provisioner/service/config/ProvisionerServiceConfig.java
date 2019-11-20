package af.asr.provisioner.service.config;

import af.asr.async.config.EnableAsync;
import af.asr.cassandra.config.EnableCassandra;
import af.asr.config.EnableApplicationName;
import af.asr.config.EnableServiceException;
import af.asr.crypto.config.EnableCrypto;
import af.asr.vault.service.config.EnableAnubis;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@ComponentScan({
        "af.asr.provisioner.*"
})
@EnableCrypto
@EnableAsync
@EnableAnubis(provideSignatureRestController = false)
@EnablePostgreSQL
@EnableCassandra
@EnableServiceException
@EnableApplicationName
@EnableConfigurationProperties({ProvisionerActiveMQProperties.class, ProvisionerProperties.class, SystemProperties.class})
public class ProvisionerServiceConfig extends WebMvcConfigurerAdapter {

    public ProvisionerServiceConfig() {
        super();
    }

    @Bean(name = ProvisionerConstants.LOGGER_NAME)
    public Logger logger() {
        return LoggerFactory.getLogger(ProvisionerConstants.LOGGER_NAME);
    }

    @Bean(name = "tokenProvider")
    public TokenProvider tokenProvider(final SystemProperties systemProperties,
                                       @SuppressWarnings("SpringJavaAutowiringInspection") final SystemAccessTokenSerializer tokenSerializer,
                                       @Qualifier(ProvisionerConstants.LOGGER_NAME) final Logger logger) {
        final String timestamp = systemProperties.getPublicKey().getTimestamp();
        logger.info("Provisioner key timestamp: " + timestamp);

        return new TokenProvider( timestamp,
                systemProperties.getPrivateKey().getModulus(),
                systemProperties.getPrivateKey().getExponent(), tokenSerializer);
    }

    @Bean
    public ApiFactory apiFactory(@Qualifier(ProvisionerConstants.LOGGER_NAME) final Logger logger) {
        return new ApiFactory(logger);
    }

    @Override
    public void configurePathMatch(final PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(Boolean.FALSE);
    }

    @Bean
    public PooledConnectionFactory jmsFactory(final ProvisionerActiveMQProperties activeMQProperties) {
        final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        final ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(activeMQProperties.getBrokerUrl());
        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);

        return pooledConnectionFactory;
    }


    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactory(final PooledConnectionFactory jmsFactory, final ProvisionerActiveMQProperties activeMQProperties) {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(jmsFactory);
        factory.setConcurrency(activeMQProperties.getConcurrency());
        return factory;
    }
}