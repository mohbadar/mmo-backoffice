package af.asr.listening;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class EventExpectation {
    private final EventKey key;
    private boolean eventFound = false;
    private boolean eventWithdrawn = false;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition found = lock.newCondition();

    EventExpectation(final EventKey key) {
        this.key = key;
    }

    EventKey getKey() {
        return key;
    }

    void setEventFound() {
        lock.lock();
        try {
            this.eventFound = true;
            found.signal();
        }
        finally {
            lock.unlock();
        }
    }

    void setEventWithdrawn() {
        lock.lock();
        try {
            this.eventWithdrawn = true;
            found.signal();
        }
        finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("WeakerAccess")
    public boolean waitForOccurrence(long timeout, TimeUnit timeUnit) throws InterruptedException {

        lock.lock();
        try {
            if (eventFound)
                return true;

            if (eventWithdrawn)
                return false;

            found.await(timeout, timeUnit);

            return (eventFound);
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return key.toString();
    }
}