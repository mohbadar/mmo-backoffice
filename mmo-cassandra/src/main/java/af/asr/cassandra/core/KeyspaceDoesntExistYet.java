package af.asr.cassandra.core;

import com.datastax.driver.core.exceptions.InvalidQueryException;

class KeyspaceDoesntExistYet extends IllegalArgumentException {
    KeyspaceDoesntExistYet(String s, InvalidQueryException ex) {
        super(s, ex);
    }
}