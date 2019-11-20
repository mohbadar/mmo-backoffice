package af.asr.provisioner.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.Valid;

@ConfigurationProperties(prefix = "provisioner")
public class ProvisionerProperties {
    @Valid
    private DataStoreOption dataStoreOption = DataStoreOption.ALL;

    public DataStoreOption getDataStoreOption() {
        return dataStoreOption;
    }

    public void setDataStoreOption(DataStoreOption dataStoreOption) {
        this.dataStoreOption = dataStoreOption;
    }
}