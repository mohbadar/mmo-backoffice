package af.asr.command.util;

public interface CommandConstants {

    String LOGGER_NAME = "command-logger";
    String SERIALIZER = "command-serializer";
    String COMMAND_SOURCE_TABLE_NAME = "command_source";

    String APPLICATION_NAME_PROP = "spring.application.name";
    String APPLICATION_NAME_DEFAULT = "command-v1";

    String ACTIVEMQ_BROKER_URL_PROP = "activemq.brokerUrl";
    String ACTIVEMQ_BROKER_URL_DEFAULT = "vm://localhost?broker.persistent=false";
    String ACTIVEMQ_CONCURRENCY_PROP = "activemq.concurrency";
    String ACTIVEMQ_CONCURRENCY_DEFAULT = "3-10";

}