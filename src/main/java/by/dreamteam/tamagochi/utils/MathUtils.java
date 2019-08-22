package by.dreamteam.tamagochi.utils;

import java.util.stream.IntStream;

public class MathUtils {

    public static Integer getProgression(int endValue) {
        return IntStream.iterate(1, integer -> integer + 1)
                .limit(endValue)
                .sum();
    }
}
