package af.asr.identity.service.internal.repository;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.List;


@Table(name = ApplicationCallEndpointSets.TABLE_NAME)
public class ApplicationCallEndpointSetEntity {
    @PartitionKey
    @Column(name = ApplicationCallEndpointSets.APPLICATION_IDENTIFIER_COLUMN)
    private String applicationIdentifier;

    @ClusteringColumn
    @Column(name = ApplicationCallEndpointSets.CALLENDPOINTSET_IDENTIFIER_COLUMN)
    private String callEndpointSetIdentifier;

    @Column(name = ApplicationCallEndpointSets.CALLENDPOINT_GROUP_IDENTIFIERS_COLUMN)
    private List<String> callEndpointGroupIdentifiers;

    public ApplicationCallEndpointSetEntity() {
    }

    public String getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
    }

    public String getCallEndpointSetIdentifier() {
        return callEndpointSetIdentifier;
    }

    public void setCallEndpointSetIdentifier(String callEndpointSetIdentifier) {
        this.callEndpointSetIdentifier = callEndpointSetIdentifier;
    }

    public List<String> getCallEndpointGroupIdentifiers() {
        return callEndpointGroupIdentifiers;
    }

    public void setCallEndpointGroupIdentifiers(List<String> callEndpointGroupIdentifiers) {
        this.callEndpointGroupIdentifiers = callEndpointGroupIdentifiers;
    }
}