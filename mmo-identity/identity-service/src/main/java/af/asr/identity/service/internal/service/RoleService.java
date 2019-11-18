package af.asr.identity.service.internal.service;


import af.asr.identity.api.v1.domain.Role;
import af.asr.identity.service.internal.repository.RoleEntity;
import af.asr.identity.service.internal.repository.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RoleService {

    private final Roles repository;

    @Autowired
    public RoleService(final Roles repository) {
        this.repository = repository;
    }

    public List<Role> findAll() {
        return repository.getAll().stream()
                .map(this::mapEntity)
                .sorted((x, y) -> (x.getIdentifier().compareTo(y.getIdentifier())))
                .collect(Collectors.toList());
    }

    private Role mapEntity(final RoleEntity roleEntity) {
        final Role ret = new Role();
        ret.setIdentifier(roleEntity.getIdentifier());
        ret.setPermissions(RoleMapper.mapPermissions(roleEntity.getPermissions()));
        return ret;
    }

    public Optional<Role> findByIdentifier(final String identifier)
    {
        final Optional<RoleEntity> ret = repository.get(identifier);

        return ret.map(this::mapEntity);
    }
}