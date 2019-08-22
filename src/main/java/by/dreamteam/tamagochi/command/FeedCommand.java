package by.dreamteam.tamagochi.command;

import by.dreamteam.tamagochi.utils.ResourceUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class FeedCommand extends DefaultCommand {

    public FeedCommand() {
        super("/feed", "");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        try {
            userService.increaseExperience(user.getId(), 1);
            absSender.execute(new SendMessage(chat.getId(),
                    String.format("Yaaamyyy, thank you, %s!!", user.getFirstName())));
            SendSticker sticker = new SendSticker();
            sticker.setChatId(chat.getId());
            sticker.setSticker(ResourceUtils.getFile("static/sticker.png"));
            absSender.execute(sticker);
        } catch (TelegramApiException e) {
            System.out.println("ERROR in " + this.getClass().getCanonicalName());
        }
    }
}
