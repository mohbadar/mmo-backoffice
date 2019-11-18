package af.asr.identity.service.internal.service;


import af.asr.identity.api.v1.domain.Permission;
import af.asr.identity.service.internal.repository.AllowedOperationType;
import af.asr.identity.service.internal.repository.PermissionType;
import af.asr.vault.api.domain.AllowedOperation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class RoleMapper {
    @SuppressWarnings("WeakerAccess")
    static Set<AllowedOperation> mapAllowedOperations(
            final Set<AllowedOperationType> allowedOperations) {
        return allowedOperations.stream()
                .map(RoleMapper::mapAllowedOperation)
                .collect(Collectors.toSet());
    }

    public static AllowedOperation mapAllowedOperation(final AllowedOperationType allowedOperationType) {
        return AllowedOperation.valueOf(allowedOperationType.toString());
    }

    static List<Permission> mapPermissions(List<PermissionType> permissions) {
        return permissions.stream()
                .map(i -> new Permission(
                        i.getPermittableGroupIdentifier(),
                        RoleMapper.mapAllowedOperations(i.getAllowedOperations()))).collect(Collectors.toList());
    }
}