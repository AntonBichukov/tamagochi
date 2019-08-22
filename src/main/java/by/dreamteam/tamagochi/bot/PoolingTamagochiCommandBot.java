package by.dreamteam.tamagochi.bot;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class PoolingTamagochiCommandBot extends TelegramLongPollingCommandBot {

    private String token;

    public PoolingTamagochiCommandBot(String botUsername, String token) {
        super(botUsername);
        this.token = token;
    }


    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        System.out.println("Not registered command.");
    }
}
