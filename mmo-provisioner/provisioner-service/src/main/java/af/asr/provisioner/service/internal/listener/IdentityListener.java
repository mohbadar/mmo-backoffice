package af.asr.provisioner.service.internal.listener;

import static af.asr.identity.api.v1.events.EventConstants.OPERATION_POST_PERMITTABLE_GROUP;
import static af.asr.identity.api.v1.events.EventConstants.OPERATION_PUT_APPLICATION_SIGNATURE;

import af.asr.config.TenantHeaderFilter;
import af.asr.identity.api.v1.events.ApplicationSignatureEvent;
import af.asr.identity.api.v1.events.EventConstants;
import af.asr.listening.EventExpectation;
import af.asr.listening.EventKey;
import af.asr.listening.TenantedEventListener;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class IdentityListener {
    private final Gson gson;
    private final TenantedEventListener eventListener = new TenantedEventListener();

    @Autowired
    public IdentityListener(final Gson gson) {
        this.gson = gson;
    }

    @JmsListener(
            subscription = EventConstants.DESTINATION,
            destination = EventConstants.DESTINATION,
            selector = EventConstants.SELECTOR_POST_PERMITTABLE_GROUP
    )
    public void onCreatePermittableGroup(
            @Header(TenantHeaderFilter.TENANT_HEADER)final String tenantIdentifier,
            final String payload) throws Exception {
        eventListener.notify(new EventKey(tenantIdentifier, OPERATION_POST_PERMITTABLE_GROUP, payload));
    }

    @JmsListener(
            subscription = EventConstants.DESTINATION,
            destination = EventConstants.DESTINATION,
            selector = EventConstants.SELECTOR_PUT_APPLICATION_SIGNATURE
    )
    public void onSetApplicationSignature(
            @Header(TenantHeaderFilter.TENANT_HEADER)final String tenantIdentifier,
            final String payload) throws Exception {
        final ApplicationSignatureEvent event = gson.fromJson(payload, ApplicationSignatureEvent.class);
        eventListener.notify(new EventKey(tenantIdentifier, OPERATION_PUT_APPLICATION_SIGNATURE, event));
    }

    public EventExpectation expectPermittableGroupCreation(final String tenantIdentifier,
                                                           final String permittableGroupIdentifier) {
        return eventListener.expect(new EventKey(tenantIdentifier, OPERATION_POST_PERMITTABLE_GROUP, permittableGroupIdentifier));
    }

    public EventExpectation expectApplicationSignatureSet(final String tenantIdentifier,
                                                          final String applicationIdentifier,
                                                          final String keyTimestamp) {
        final ApplicationSignatureEvent expectedEvent = new ApplicationSignatureEvent(applicationIdentifier, keyTimestamp);
        return eventListener.expect(new EventKey(tenantIdentifier, OPERATION_PUT_APPLICATION_SIGNATURE, expectedEvent));
    }

    public void withdrawExpectation(final EventExpectation eventExpectation) {
        eventListener.withdrawExpectation(eventExpectation);
    }
}