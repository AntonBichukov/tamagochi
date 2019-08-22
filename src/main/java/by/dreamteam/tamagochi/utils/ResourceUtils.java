package by.dreamteam.tamagochi.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class ResourceUtils {

    public static File getFile(String path) {
        try {
            return new ClassPathResource(path).getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
