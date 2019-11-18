package af.asr.identity.service.internal.service;


import af.asr.identity.api.v1.PermittableGroupIds;
import af.asr.identity.api.v1.domain.Permission;
import af.asr.identity.api.v1.domain.User;
import af.asr.identity.service.internal.repository.*;
import af.asr.vault.api.domain.AllowedOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final Users users;
    private final Roles roles;

    @Autowired
    UserService(final Users users, final Roles roles)
    {
        this.users = users;
        this.roles = roles;
    }

    public List<User> findAll() {
        return users.getAll().stream().map(UserService::mapUser).collect(Collectors.toList());
    }

    public Optional<User> findByIdentifier(final String identifier)
    {
        return users.get(identifier).map(UserService::mapUser);
    }

    static private User mapUser(final UserEntity u) {
        return new User(u.getIdentifier(), u.getRole());
    }

    public Set<Permission> getPermissions(final String userIdentifier) {
        final Optional<UserEntity> userEntity = users.get(userIdentifier);
        final Optional<RoleEntity> roleEntity = userEntity.map(UserEntity::getRole).map(roles::get).orElse(Optional.empty());
        final List<PermissionType> permissionEntities = roleEntity.map(RoleEntity::getPermissions).orElse(Collections.emptyList());
        final List<Permission> permissions = RoleMapper.mapPermissions(permissionEntities);
        permissions.add(new Permission(PermittableGroupIds.SELF_MANAGEMENT, AllowedOperation.ALL));

        return new HashSet<>(permissions);
    }
}
