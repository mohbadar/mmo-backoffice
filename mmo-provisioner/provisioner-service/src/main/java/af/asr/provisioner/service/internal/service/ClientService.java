package af.asr.provisioner.service.internal.service;

import af.asr.ServiceException;
import af.asr.cassandra.core.CassandraSessionProvider;
import af.asr.provisioner.service.internal.repository.ClientEntity;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.Result;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final CassandraSessionProvider cassandraSessionProvider;

    @Autowired
    public ClientService(final CassandraSessionProvider cassandraSessionProvider) {
        super();
        this.cassandraSessionProvider = cassandraSessionProvider;
    }

    public List<ClientEntity> fetchAll() {
        final ArrayList<ClientEntity> result = new ArrayList<>();

        final ResultSet clientResult =
                this.cassandraSessionProvider.getAdminSession().execute("SELECT * FROM clients");

        final Mapper<ClientEntity> clientEntityMapper =
                this.cassandraSessionProvider.getAdminSessionMappingManager().mapper(ClientEntity.class);

        final Result<ClientEntity> mappedClientEntities = clientEntityMapper.map(clientResult);
        if (mappedClientEntities != null) {
            result.addAll(mappedClientEntities.all());
        }

        return result;
    }

    public void create(final ClientEntity clientEntity) {
        final Mapper<ClientEntity> clientEntityMapper =
                this.cassandraSessionProvider.getAdminSessionMappingManager().mapper(ClientEntity.class);
        if (clientEntityMapper.get(clientEntity.getName()) != null) {
            throw ServiceException.conflict("Client {0} already exists!", clientEntity.getName());
        }
        clientEntityMapper.save(clientEntity);
    }

    public void delete(final String name) {
        final Mapper<ClientEntity> clientEntityMapper =
                this.cassandraSessionProvider.getAdminSessionMappingManager().mapper(ClientEntity.class);
        clientEntityMapper.delete(name);
    }

    public ClientEntity findByName(final String name) {
        final Mapper<ClientEntity> clientEntityMapper =
                this.cassandraSessionProvider.getAdminSessionMappingManager().mapper(ClientEntity.class);
        final ClientEntity clientEntity = clientEntityMapper.get(name);
        if (clientEntity == null) {
            throw ServiceException.notFound("Client {0} not found!", name);
        }
        return clientEntity;
    }
}