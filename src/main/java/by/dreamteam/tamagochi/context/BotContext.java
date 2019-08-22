package by.dreamteam.tamagochi.context;

import by.dreamteam.tamagochi.bot.PoolingTamagochiCommandBot;
import by.dreamteam.tamagochi.command.annotation.BotScan;
import by.dreamteam.tamagochi.command.register.CommandRegistrationService;
import by.dreamteam.tamagochi.context.configuration.BotConfuguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@Configuration
public class BotContext {

    @Bean
    @ConfigurationProperties(prefix = "tamagochi.bot")
    public BotConfuguration confuguration() {
        return new BotConfuguration();
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() {
        return new TelegramBotsApi();
    }

    @Bean
    public PoolingTamagochiCommandBot poolingTamagochiBot(BotConfuguration confuguration) {
        PoolingTamagochiCommandBot bot =
                new PoolingTamagochiCommandBot(confuguration.getName(), confuguration.getToken());

        bot.register(new HelpCommand());

        return bot;
    }

    @Bean
    public CommandRegistrationService commandRegistrationService(PoolingTamagochiCommandBot poolingTamagochiBot) {
        CommandRegistrationService commandRegistrationService =
                new CommandRegistrationService(poolingTamagochiBot);
        return commandRegistrationService;
    }

    /*private List<BotCommand> getCommands(UserService userService) {
        Optional<Annotation> botScanOptional = Stream.of(BotContext.class.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType().equals(BotScan.class))
                .findFirst();
        BotScan botScan = (BotScan) botScanOptional.orElseThrow(ClassFormatException::new);

        List<List<String>> classes = Stream.of(ResourceUtils.getFile(botScan.packageName()
                .replace(".", "/")))
                .filter(File::isDirectory)
                .map(File::listFiles)
                .map(Stream::of)
                .map(stream -> stream
                        .map(file -> String.format("%s.%s", botScan.packageName(),
                                file.getName().replace(".class", "")))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>();
        classes.stream()
                .forEach(result::addAll);

        List<Class> clazzez = result.stream()
                .map(this::getCommandClass)
                .filter(Objects::nonNull)
                .filter(clazz -> clazz.isAnnotationPresent(PetCommand.class))
                .collect(Collectors.toList());


        return clazzez.stream()
                .collect(Collectors.toMap(clazz -> clazz, clazz -> (PetCommand) clazz.getAnnotation(PetCommand.class)))
                .entrySet()
                .stream()
                .map((entry) -> getCommand(entry.getKey().getConstructors()[0], entry.getValue().command(),
                        entry.getValue().description(), userService))
                .collect(Collectors.toList());
    }

    private BotCommand getCommand(Constructor constructor, Object... params) {
        try {
            return (BotCommand) constructor.newInstance(params[0], params[1], params[2]);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class getCommandClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
