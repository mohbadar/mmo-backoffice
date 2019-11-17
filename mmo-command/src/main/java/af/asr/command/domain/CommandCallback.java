package af.asr.command.domain;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class CommandCallback<T> {

    private final Future<T> result;

    public CommandCallback(final Future<T> result) {
        super();
        this.result = result;
    }

    public T get() throws ExecutionException, InterruptedException {
        return this.result.get();
    }
}