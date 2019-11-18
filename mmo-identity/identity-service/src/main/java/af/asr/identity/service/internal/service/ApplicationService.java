package af.asr.identity.service.internal.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

import af.asr.identity.api.v1.domain.CallEndpointSet;
import af.asr.identity.api.v1.domain.Permission;
import af.asr.identity.service.internal.mapper.ApplicationCallEndpointSetMapper;
import af.asr.identity.service.internal.mapper.PermissionMapper;
import af.asr.identity.service.internal.mapper.SignatureMapper;
import af.asr.identity.service.internal.repository.*;
import af.asr.vault.api.domain.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final ApplicationSignatures applicationSignaturesRepository;
    private final ApplicationPermissions applicationPermissionsRepository;
    private final ApplicationPermissionUsers applicationPermissionsUserRepository;
    private final ApplicationCallEndpointSets applicationCallEndpointSets;

    @Autowired
    public ApplicationService(final ApplicationSignatures applicationSignaturesRepository,
                              final ApplicationPermissions applicationPermissionsRepository,
                              final ApplicationPermissionUsers applicationPermissionsUserRepository,
                              final ApplicationCallEndpointSets applicationCallEndpointSets) {
        this.applicationSignaturesRepository = applicationSignaturesRepository;
        this.applicationPermissionsRepository = applicationPermissionsRepository;
        this.applicationPermissionsUserRepository = applicationPermissionsUserRepository;
        this.applicationCallEndpointSets = applicationCallEndpointSets;
    }

    public List<String> getAllApplications() {
        return applicationSignaturesRepository.getAll().stream()
                .map(ApplicationSignatureEntity::getApplicationIdentifier)
                .collect(Collectors.toList());
    }

    public List<Permission> getAllPermissionsForApplication(final String applicationIdentifier) {
        return applicationPermissionsRepository.getAllPermissionsForApplication(applicationIdentifier).stream()
                .map(PermissionMapper::mapToPermission)
                .collect(Collectors.toList());
    }

    public Optional<Permission> getPermissionForApplication(
            final String applicationIdentifier,
            final String permittableEndpointGroupIdentifier) {
        return applicationPermissionsRepository.getPermissionForApplication(applicationIdentifier, permittableEndpointGroupIdentifier)
                .map(PermissionMapper::mapToPermission);
    }

    public Optional<Signature> getSignatureForApplication(final String applicationIdentifier, final String timestamp) {
        return applicationSignaturesRepository.get(applicationIdentifier, timestamp)
                .map(SignatureMapper::mapToSignature);
    }

    public boolean applicationExists(final String applicationIdentifier) {
        return applicationSignaturesRepository.signaturesExistForApplication(applicationIdentifier);
    }

    public boolean applicationPermissionExists(final @Nonnull String applicationIdentifier,
                                               final @Nonnull String permittableGroupIdentifier) {
        return applicationPermissionsRepository.exists(applicationIdentifier, permittableGroupIdentifier);
    }

    public boolean applicationPermissionEnabledForUser(final String applicationIdentifier,
                                                       final String permittableEndpointGroupIdentifier,
                                                       final String userIdentifier) {
        return applicationPermissionsUserRepository.enabled(applicationIdentifier, permittableEndpointGroupIdentifier, userIdentifier);
    }

    public List<CallEndpointSet> getAllCallEndpointSetsForApplication(final String applicationIdentifier) {
        return applicationCallEndpointSets.getAllForApplication(applicationIdentifier).stream()
                .map(ApplicationCallEndpointSetMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<CallEndpointSet> getCallEndpointSetForApplication(
            final String applicationIdentifier,
            final String callEndpointSetIdentifier) {
        return applicationCallEndpointSets.get(applicationIdentifier, callEndpointSetIdentifier)
                .map(ApplicationCallEndpointSetMapper::map);
    }

    public boolean applicationCallEndpointSetExists(String applicationIdentifier, String callEndpointSetIdentifier) {
        return applicationCallEndpointSets.get(applicationIdentifier, callEndpointSetIdentifier).isPresent();
    }
}