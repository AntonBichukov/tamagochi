package by.dreamteam.tamagochi;

import by.dreamteam.tamagochi.bot.PoolingTamagochiCommandBot;
import by.dreamteam.tamagochi.command.register.CommandRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@SpringBootApplication
@EnableConfigurationProperties
public class TamagochiBotApplication implements ApplicationRunner {

    @Autowired
    private TelegramBotsApi api;
    @Autowired
    private PoolingTamagochiCommandBot bot;

    public static void main(String[] args) {
        ApiContextInitializer.init();

        SpringApplication.run(TamagochiBotApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        api.registerBot(bot);
        System.out.println("App start");
    }
}
