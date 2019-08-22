package by.dreamteam.tamagochi.command.register;

import by.dreamteam.tamagochi.bot.PoolingTamagochiCommandBot;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;

import java.util.List;

@Value
@RequiredArgsConstructor
public class CommandRegistrationService {

    private final PoolingTamagochiCommandBot bot;

    private List<BotCommand> commands = Lists.newArrayList();


    public void registerBotCommand(BotCommand command) {
        if (!commands.contains(command)) {
            commands.add(command);
            bot.register(command);
        }
    }
}
