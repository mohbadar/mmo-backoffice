package af.asr.identity.service.internal.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import af.asr.identity.api.v1.domain.PermittableGroup;
import af.asr.identity.service.internal.repository.PermittableGroupEntity;
import af.asr.identity.service.internal.repository.PermittableGroups;
import af.asr.identity.service.internal.repository.PermittableType;
import af.asr.vault.api.domain.PermittableEndpoint;
import org.springframework.stereotype.Service;

@Service
public class PermittableGroupService {
    private final PermittableGroups repository;

    public PermittableGroupService(final PermittableGroups repository) {
        this.repository = repository;
    }

    public Optional<PermittableGroup> findByIdentifier(final String identifier) {
        final Optional<PermittableGroupEntity> ret = repository.get(identifier);

        return ret.map(this::mapPermittableGroup);
    }

    public List<PermittableGroup> findAll() {
        return repository.getAll().stream()
                .map(this::mapPermittableGroup)
                .collect(Collectors.toList());
    }

    private PermittableGroup mapPermittableGroup(final PermittableGroupEntity permittableGroupEntity) {
        final PermittableGroup ret = new PermittableGroup();
        ret.setIdentifier(permittableGroupEntity.getIdentifier());
        ret.setPermittables(permittableGroupEntity.getPermittables().stream()
                .map(this::mapPermittable)
                .collect(Collectors.toList()));
        return ret;
    }

    private PermittableEndpoint mapPermittable(final PermittableType entity) {
        final PermittableEndpoint ret = new PermittableEndpoint();
        ret.setMethod(entity.getMethod());
        ret.setGroupId(entity.getSourceGroupId());
        ret.setPath(entity.getPath());
        return ret;
    }
}