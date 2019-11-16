package af.asr.listening;

import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public class EventKey {
    private String tenantIdentifier;
    private String eventName;
    private Object event;

    public EventKey(
            final String tenantIdentifier,
            final String eventName,
            final Object event) {
        this.tenantIdentifier = tenantIdentifier;
        this.eventName = eventName;
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventKey eventKey = (EventKey) o;
        return Objects.equals(tenantIdentifier, eventKey.tenantIdentifier) &&
                Objects.equals(eventName, eventKey.eventName) &&
                Objects.equals(event, eventKey.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenantIdentifier,  eventName, event);
    }

    @Override
    public String toString() {
        return "EventKey{" +
                "tenantIdentifier='" + tenantIdentifier + '\'' +
                ", eventName='" + eventName + '\'' +
                ", event=" + event +
                '}';
    }
}