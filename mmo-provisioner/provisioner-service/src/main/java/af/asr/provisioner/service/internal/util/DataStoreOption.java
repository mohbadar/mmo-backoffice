package af.asr.provisioner.service.internal.util;

public enum DataStoreOption {
    ALL,
    CASSANDRA,
    RDBMS;

    public boolean isEnabled(final DataStoreOption dataStoreOption) {
        return this == ALL || this == dataStoreOption;
    }
}