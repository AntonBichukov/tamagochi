package by.dreamteam.tamagochi.command;

import by.dreamteam.tamagochi.command.register.CommandRegistrationService;
import by.dreamteam.tamagochi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;

import javax.annotation.PostConstruct;

public abstract class DefaultCommand extends BotCommand {

    @Autowired
    protected UserService userService;
    @Autowired
    protected CommandRegistrationService registrationService;


    public DefaultCommand(String command, String description) {
        super(command, description);
    }

    @PostConstruct
    private void selfRegistration() {
        registrationService.registerBotCommand(this);
    }

}
