package af.asr.postgresql.config;

import com.jolbox.bonecp.BoneCPDataSource;

public class MetaDataSourceWrapper {
    private final BoneCPDataSource metaDataSource;

    public MetaDataSourceWrapper(final BoneCPDataSource metaDataSource) {
        this.metaDataSource = metaDataSource;
    }

    BoneCPDataSource getMetaDataSource() {
        return metaDataSource;
    }
}