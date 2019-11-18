package af.asr.identity.service.internal.mapper;


import af.asr.identity.api.v1.domain.Permission;
import af.asr.identity.service.internal.repository.AllowedOperationType;
import af.asr.identity.service.internal.repository.PermissionType;
import af.asr.vault.api.domain.AllowedOperation;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

public interface PermissionMapper {

    static Set<AllowedOperationType> mapToAllowedOperationsTypeSet(
            @Nullable final Set<AllowedOperation> allowedOperations) {
        if (allowedOperations == null)
            return Collections.emptySet();

        return allowedOperations.stream().map(op -> {
            switch (op) {
                case READ:
                    return AllowedOperationType.READ;
                case CHANGE:
                    return AllowedOperationType.CHANGE;
                case DELETE:
                    return AllowedOperationType.DELETE;
                default:
                    return null;
            }
        }).filter(op -> (op != null)).collect(Collectors.toSet());
    }

    static Set<AllowedOperation> mapToAllowedOperations(final Set<AllowedOperationType> allowedOperations) {
        return allowedOperations.stream().map(op -> {
            switch (op) {
                case READ:
                    return AllowedOperation.READ;
                case CHANGE:
                    return AllowedOperation.CHANGE;
                case DELETE:
                    return AllowedOperation.DELETE;
                default:
                    return null;
            }
        }).filter(op -> (op != null)).collect(Collectors.toSet());
    }

    static PermissionType mapToPermissionType(final Permission instance) {
        return new PermissionType(instance.getPermittableEndpointGroupIdentifier(), mapToAllowedOperationsTypeSet(instance.getAllowedOperations()));
    }

    static Permission mapToPermission(final PermissionType instance) {
        return new Permission(instance.getPermittableGroupIdentifier(), mapToAllowedOperations(instance.getAllowedOperations()));
    }
}