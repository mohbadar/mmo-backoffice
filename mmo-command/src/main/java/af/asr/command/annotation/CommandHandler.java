package af.asr.command.annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface CommandHandler {
    /**
     *
     * @return the log level with which to log a command which will be sent to a command handler. By default this is
     * NONE. The level should be left at NONE for sensitive data which do not belong in the log file, such as
     * passwords, financial transactions, and customer names and addresses. The logfile contents will include what
     * is output by "toString" of the command, so any fine-tuning of the contents of the logfile can be performed there.
     * For commands which happen frequently this can produce a lot of output. Consider using a log level of DEBUG or
     * TRACE if you decide to log a command of that sort. No log level of ERROR, or WARNING is offered here, because
     * a command is not an error.
     */
    CommandLogLevel logStart() default CommandLogLevel.NONE;

    /**
     *
     * @return the log level with which to log the result of a command which emits an event. Leave this as it's default
     * NONE, if the command is not event-emitting.  The level should also be left at NONE for sensitive data which do
     * not belong in the log file, however you should consider not putting such data in the event queue in the first
     * place. The logfile contents will include the output of the events "toString". For commands which happen frequently,
     * this can produce a lot of output.  Consider not using INFO as a log level for a command of that sort. If a command
     * handler is exited via an exception, no event is logged, but the exception may be logged regardless of this setting.
     */
    CommandLogLevel logFinish() default CommandLogLevel.NONE;
}