package af.asr.data.jpa.local;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "database", ignoreUnknownFields = false)
public final class LocalDatabaseProperties {

    private Pool pool;
    private Management management;

    public LocalDatabaseProperties() {
        super();
    }

    public Pool getPool() {
        return this.pool;
    }

    public void setPool(final Pool pool) {
        this.pool = pool;
    }

    public Management getManagement() {
        return this.management;
    }

    public void setManagement(final Management management) {
        this.management = management;
    }

    public static class Pool {
        private int minSize = 5;
        private int maxSize = 20;
        private long waitTime = 60000L;

        public Pool() {
            super();
        }

        public int getMinSize() {
            return this.minSize;
        }

        public void setMinSize(final int minSize) {
            this.minSize = minSize;
        }

        public int getMaxSize() {
            return this.maxSize;
        }

        public void setMaxSize(final int maxSize) {
            this.maxSize = maxSize;
        }

        public long getWaitTime() {
            return this.waitTime;
        }

        public void setWaitTime(final long waitTime) {
            this.waitTime = waitTime;
        }
    }

    public static class Management {
        private String driverClass;
        private String url;
        private String username;
        private String password;

        public Management() {
            super();
        }

        public String getDriverClass() {
            return this.driverClass;
        }

        public void setDriverClass(final String driverClass) {
            this.driverClass = driverClass;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(final String url) {
            this.url = url;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(final String username) {
            this.username = username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(final String password) {
            this.password = password;
        }
    }
}