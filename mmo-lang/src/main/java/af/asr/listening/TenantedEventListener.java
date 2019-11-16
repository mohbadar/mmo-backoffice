package af.asr.listening;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@SuppressWarnings("WeakerAccess")
public class TenantedEventListener {
    private final Map<EventKey, EventExpectation> eventExpectations = new ConcurrentHashMap<>();

    public EventExpectation expect(final EventKey eventKey) {
        final EventExpectation value = new EventExpectation(eventKey);
        eventExpectations.put(eventKey, value);
        return value;
    }

    public void notify(final EventKey eventKey) {
        final EventExpectation eventExpectation = eventExpectations.remove(eventKey);
        if (eventExpectation != null) {
            eventExpectation.setEventFound();
        }
    }

    public void withdrawExpectation(final EventExpectation eventExpectation) {
        final EventExpectation expectation = eventExpectations.remove(eventExpectation.getKey());
        if (expectation != null) {
            eventExpectation.setEventWithdrawn();
        }
    }
}