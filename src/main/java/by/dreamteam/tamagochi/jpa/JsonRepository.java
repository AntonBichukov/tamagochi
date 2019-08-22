package by.dreamteam.tamagochi.jpa;

import by.dreamteam.tamagochi.user.BotUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class JsonRepository {
    private final ObjectMapper objectMapper;
    private final File file;

    public void save(Map<Integer, BotUser> botUserMap) {
        try {
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            file.createNewFile();
            objectMapper.writeValue(file, botUserMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, BotUser> load() {
        try {
            return objectMapper.readValue(file, Map.class);
        } catch (IOException e) {
            return null;
        }
    }

}
