package af.asr.provisioner.service.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "activemq")
public class ProvisionerActiveMQProperties {
    @SuppressWarnings("unused")
    final public static String ACTIVEMQ_BROKER_URL_PROP = "activemq.brokerUrl";
    @SuppressWarnings("unused")
    final public static String ACTIVEMQ_CONCURRENCY_PROP = "activemq.concurrency";
    @SuppressWarnings("WeakerAccess")
    final public static String ACTIVEMQ_BROKER_URL_DEFAULT = "vm://localhost?broker.persistent=false";
    @SuppressWarnings("WeakerAccess")
    final public static String ACTIVEMQ_CONCURRENCY_DEFAULT = "3-10";

    private String brokerUrl = ACTIVEMQ_BROKER_URL_DEFAULT;
    private String concurrency = ACTIVEMQ_CONCURRENCY_DEFAULT;

    public ProvisionerActiveMQProperties() {
    }

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public String getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(String concurrency) {
        this.concurrency = concurrency;
    }
}