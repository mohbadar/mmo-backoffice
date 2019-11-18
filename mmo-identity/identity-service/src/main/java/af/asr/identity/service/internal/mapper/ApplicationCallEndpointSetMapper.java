package af.asr.identity.service.internal.mapper;


import af.asr.identity.api.v1.domain.CallEndpointSet;
import af.asr.identity.service.internal.repository.ApplicationCallEndpointSetEntity;
import java.util.Collections;


public interface ApplicationCallEndpointSetMapper {
    static ApplicationCallEndpointSetEntity mapToEntity(
            final String applicationIdentifier,
            final CallEndpointSet callEndpointSet)
    {
        final ApplicationCallEndpointSetEntity ret = new ApplicationCallEndpointSetEntity();
        ret.setApplicationIdentifier(applicationIdentifier);
        ret.setCallEndpointSetIdentifier(callEndpointSet.getIdentifier());
        ret.setCallEndpointGroupIdentifiers(callEndpointSet.getPermittableEndpointGroupIdentifiers());
        return ret;
    }

    static CallEndpointSet map(final ApplicationCallEndpointSetEntity entity) {
        final CallEndpointSet ret = new CallEndpointSet();
        ret.setIdentifier(entity.getCallEndpointSetIdentifier());
        if (entity.getCallEndpointGroupIdentifiers() == null)
            ret.setPermittableEndpointGroupIdentifiers(Collections.emptyList());
        else
            ret.setPermittableEndpointGroupIdentifiers(entity.getCallEndpointGroupIdentifiers());
        return ret;
    }
}