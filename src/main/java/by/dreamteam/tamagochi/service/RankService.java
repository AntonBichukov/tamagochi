package by.dreamteam.tamagochi.service;

import by.dreamteam.tamagochi.user.BotUser;
import by.dreamteam.tamagochi.utils.MathUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RankService {

    private Map<Integer, Integer> rankExp;

    @PostConstruct
    private void init() {
        rankExp = Stream.iterate(1, i -> i + 1)
                .limit(99)
                .collect(Collectors.toMap(i -> i, MathUtils::getProgression));
    }

    public BotUser increaseExp(BotUser botUser, int delta) {
        int currentExp = botUser.getExperience();
        int newExp = currentExp + delta;
        int currentRunkExp = rankExp.get(botUser.getRank());
        if (newExp >= currentRunkExp) {
            increaseExp(increaseRank(botUser, 1), newExp - currentRunkExp);
        }else {
            botUser.setExperience(newExp);
        }
        return botUser;
    }

    public BotUser increaseRank(BotUser botUser, int delta) {
        botUser.setRank(botUser.getRank() + delta);
        botUser.setExperience(0);
        return botUser;
    }

    public Integer getCurrentRankExp(int rank) {
        return rankExp.get(rank);
    }
}
