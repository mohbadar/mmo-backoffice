package af.asr.provisioner.service.internal.util;

final class JdbcUrlBuilder {

    enum DatabaseType {
        POSTGRESQL("jdbc:postgresql:");

        private final String prefix;

        DatabaseType(final String prefix) {
            this.prefix = prefix;
        }

        String prefix() {
            return this.prefix;
        }
    }

    private final DatabaseType type;
    private String host;
    private String port;
    private String instanceName;

    private JdbcUrlBuilder(final DatabaseType type) {
        super();
        this.type = type;
    }

    static JdbcUrlBuilder create(final DatabaseType type) {
        return new JdbcUrlBuilder(type);
    }

    JdbcUrlBuilder host(final String host) {
        this.host = host;
        return this;
    }

    JdbcUrlBuilder port(final String port) {
        this.port = port;
        return this;
    }

    JdbcUrlBuilder instanceName(final String instanceName) {
        this.instanceName = instanceName;
        return this;
    }

    String build() {
        switch (this.type) {
            case POSTGRESQL:
                return this.type.prefix()
                        + this.host + ":"
                        + this.port
                        + (this.instanceName != null ? "/" + this.instanceName : "");
            default:
                throw new IllegalArgumentException("Unknown database type '" + this.type.name() + "'");
        }
    }
}