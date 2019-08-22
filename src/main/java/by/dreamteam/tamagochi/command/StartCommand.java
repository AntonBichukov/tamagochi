package by.dreamteam.tamagochi.command;

import by.dreamteam.tamagochi.keyboard.BotKeyboard;
import by.dreamteam.tamagochi.user.BotUser;
import by.dreamteam.tamagochi.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class StartCommand extends DefaultCommand {

    @Autowired
    private BotKeyboard keyboard;

    public StartCommand() {
        super("/start", "");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        try {
            BotUser botUser = userService.create(user.getId());
            absSender.execute(new SendMessage(chat.getId(), "Hello, " + user.getFirstName()));

            SendSticker sticker = new SendSticker();
            sticker.setChatId(chat.getId());
            sticker.setSticker(ResourceUtils.getFile("static/sticker.png"));
            absSender.execute(sticker);

            SendMessage message = new SendMessage();
            message.setChatId(chat.getId())
                    .setText(String.format("You rank is %d and you have %d experience.",
                            botUser.getRank(), botUser.getExperience()))
                    .setReplyMarkup(keyboard.defaultKeyboardMarkup());

            absSender.execute(message);
        } catch (TelegramApiException e) {
            System.out.println("ERROR in " + this.getClass().getCanonicalName());
        }
    }
}
