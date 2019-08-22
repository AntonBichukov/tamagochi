package by.dreamteam.tamagochi.command;

import by.dreamteam.tamagochi.user.BotUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class UpdateCommand extends DefaultCommand {

    public UpdateCommand() {
        super("/update", "");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        try {
            BotUser botUser = userService.getUser(user.getId());
            absSender.execute(new SendMessage(chat.getId(),
                    String.format("Now you pet have %d rand and experoence %d/%d",
                            botUser.getRank(),
                            botUser.getExperience(),
                            userService.getCurrentRankExp(botUser.getRank()))));
        } catch (TelegramApiException e) {
            System.out.println("ERROR in " + this.getClass().getCanonicalName());
        }
    }
}
