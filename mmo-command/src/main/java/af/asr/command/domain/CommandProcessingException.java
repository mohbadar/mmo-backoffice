package af.asr.command.domain;

public class CommandProcessingException extends Exception {

    public CommandProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}