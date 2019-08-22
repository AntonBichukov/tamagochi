package by.dreamteam.tamagochi.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


public class PoolingTamagochiBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
    }

    @Override
    public String getBotUsername() {
        return "testPetsBot";
    }

    @Override
    public String getBotToken() {
        return "725731946:AAHKXHS7gr-UrwHCcKDiH9Qt359Lyv7QZMM";
    }
}
