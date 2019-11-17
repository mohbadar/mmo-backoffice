package af.asr.command.domain;


import af.asr.command.annotation.EventEmitter;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public final class CommandHandlerHolder {

    private final Object aggregate;
    private final Method method;
    private final EventEmitter eventEmitter;
    private final Class<?>[] exceptionTypes;
    private final Consumer<Object> logStart;
    private final Consumer<Object> logFinish;

    public CommandHandlerHolder(final Object aggregate, final Method method, final EventEmitter eventEmitter,
                                final Class<?>[] exceptionTypes,
                                final Consumer<Object> logStart,
                                final Consumer<Object> logFinish) {
        super();
        this.aggregate = aggregate;
        this.method = method;
        this.eventEmitter = eventEmitter;
        this.exceptionTypes = exceptionTypes;
        this.logStart = logStart;
        this.logFinish = logFinish;
    }

    public Object aggregate() {
        return aggregate;
    }

    public Method method() {
        return method;
    }

    public EventEmitter eventEmitter() {
        return eventEmitter;
    }

    public Class<?>[] exceptionTypes() {
        return exceptionTypes;
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public void logStart(final Object command) {
        logStart.accept(command);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public void logFinish(final Object command) {
        logFinish.accept(command);
    }
}