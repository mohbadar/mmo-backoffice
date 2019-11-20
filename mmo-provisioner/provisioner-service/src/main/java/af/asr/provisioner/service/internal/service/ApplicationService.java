package af.asr.provisioner.service.internal.service;


import af.asr.ServiceException;
import af.asr.cassandra.core.CassandraSessionProvider;
import af.asr.provisioner.service.config.ProvisionerConstants;
import af.asr.provisioner.service.internal.repository.ApplicationEntity;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.Result;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ApplicationService {

    private final Logger logger;
    private final CassandraSessionProvider cassandraSessionProvider;
    private final TenantApplicationService tenantApplicationService;

    @Autowired
    public ApplicationService(@Qualifier(ProvisionerConstants.LOGGER_NAME) final Logger logger,
                              final CassandraSessionProvider cassandraSessionProvider,
                              final TenantApplicationService tenantApplicationService) {
        super();
        this.logger = logger;
        this.cassandraSessionProvider = cassandraSessionProvider;
        this.tenantApplicationService = tenantApplicationService;
    }

    public void create(final ApplicationEntity applicationEntity) {
        final Mapper<ApplicationEntity> applicationEntityMapper =
                this.cassandraSessionProvider.getAdminSessionMappingManager().mapper(ApplicationEntity.class);

        if (applicationEntityMapper.get(applicationEntity.getName()) != null) {
            this.logger.warn("Tried to create duplicate application {}!", applicationEntity.getName());
            throw ServiceException.conflict("Application {0} already exists!", applicationEntity.getName());
        }

        applicationEntityMapper.save(applicationEntity);
    }

    public ApplicationEntity find(final String name) {
        final Mapper<ApplicationEntity> applicationEntityMapper =
                this.cassandraSessionProvider.getAdminSessionMappingManager().mapper(ApplicationEntity.class);

        final ApplicationEntity applicationEntity = applicationEntityMapper.get(name);
        if (applicationEntity == null) {
            this.logger.warn("Tried to find unknown application {}!", name);
            throw ServiceException.notFound("Application {0} not found!", name);
        }

        return applicationEntity;
    }

    public List<ApplicationEntity> fetchAll() {
        final ArrayList<ApplicationEntity> applicationEntities = new ArrayList<>();

        final ResultSet resultSet =
                this.cassandraSessionProvider.getAdminSession().execute(" SELECT * FROM applications ");

        final Mapper<ApplicationEntity> applicationEntityMapper =
                this.cassandraSessionProvider.getAdminSessionMappingManager().mapper(ApplicationEntity.class);

        if (resultSet != null) {
            final Result<ApplicationEntity> mappedApplicationEntities = applicationEntityMapper.map(resultSet);
            applicationEntities.addAll(mappedApplicationEntities.all());
        }

        return applicationEntities;
    }

    public void delete(final String name) {
        final Mapper<ApplicationEntity> applicationEntityMapper =
                this.cassandraSessionProvider.getAdminSessionMappingManager().mapper(ApplicationEntity.class);

        applicationEntityMapper.delete(name);
        this.tenantApplicationService.removeApplication(name);
    }
}