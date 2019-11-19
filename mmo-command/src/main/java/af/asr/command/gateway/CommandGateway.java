package af.asr.command.gateway;


import af.asr.command.domain.CommandCallback;
import af.asr.command.domain.CommandProcessingException;
import af.asr.command.internal.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandGateway {

    private final CommandBus commandBus;

    @Autowired
    public CommandGateway(final CommandBus commandBus) {
        super();
        this.commandBus = commandBus;
    }

    public <C> void process(final C command) {
        this.commandBus.dispatch(command);
    }

    public <C, T> CommandCallback<T> process(final C command, Class<T> clazz) throws CommandProcessingException {
        return new CommandCallback<>(this.commandBus.dispatch(command, clazz));
    }
}