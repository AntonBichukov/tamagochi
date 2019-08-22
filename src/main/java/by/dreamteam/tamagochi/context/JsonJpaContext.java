package by.dreamteam.tamagochi.context;

import by.dreamteam.tamagochi.context.configuration.BotConfuguration;
import by.dreamteam.tamagochi.jpa.JsonRepository;
import by.dreamteam.tamagochi.utils.ResourceUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class JsonJpaContext {

    @Bean
    public JsonRepository jsonRepository(BotConfuguration botConfuguration) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = ResourceUtils.getFile(botConfuguration.getDataFile());
        return new JsonRepository(objectMapper, file);
    }
}
