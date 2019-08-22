package by.dreamteam.tamagochi.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;
import java.util.List;

import static by.dreamteam.tamagochi.keyboard.BotKeyboard.Buttons.CLEAR;
import static by.dreamteam.tamagochi.keyboard.BotKeyboard.Buttons.FEED;
import static by.dreamteam.tamagochi.keyboard.BotKeyboard.Buttons.START;
import static by.dreamteam.tamagochi.keyboard.BotKeyboard.Buttons.UPDATE;

@Component
public class BotKeyboard {

    public ReplyKeyboardMarkup defaultKeyboardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        KeyboardButton startButton = START.getButton();
        KeyboardButton updateButton = UPDATE.getButton();
        KeyboardButton feedButton = FEED.getButton();
        KeyboardButton clearButton = CLEAR.getButton();

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(startButton);
        firstRow.add(feedButton);

        KeyboardRow secondRow = new KeyboardRow();
        firstRow.add(updateButton);
        firstRow.add(clearButton);

        List<KeyboardRow> keyboardRows = Arrays.asList(firstRow, secondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return replyKeyboardMarkup;
    }

    private static KeyboardButton createButton(String command) {
        KeyboardButton button = new KeyboardButton()
                .setText(command);
        return button;
    }

    public enum Buttons {
        START(createButton("/start")),
        UPDATE(createButton("/update")),
        FEED(createButton("/feed")),
        CLEAR(createButton("/clear"));


        private KeyboardButton button;

        Buttons(KeyboardButton button) {
            this.button = button;
        }

        public KeyboardButton getButton() {
            return button;
        }
    }
}
