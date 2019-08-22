package by.dreamteam.tamagochi.service;

import by.dreamteam.tamagochi.jpa.JsonRepository;
import by.dreamteam.tamagochi.user.BotUser;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@Service
public class UserService {

    @Autowired
    private JsonRepository repository;
    @Autowired
    private RankService rankService;
    private Map<Integer, BotUser> userMap;

    @PostConstruct
    private void init() {
        userMap = ofNullable(repository.load()).orElseGet(Maps::newHashMap);
    }

    public void save() {
        repository.save(userMap);
    }

    public BotUser create(Integer id) {

        BotUser botUser = BotUser.builder()
                .id(id)
                .rank(1)
                .experience(0)
                .stickerId(UUID.randomUUID().toString())
                .lastFeed(new Timestamp(System.currentTimeMillis()))
                .lastPlay(new Timestamp(System.currentTimeMillis()))
                .build();
        userMap.put(id, botUser);
        save();
        return botUser;
    }

    public BotUser getUser(int id) {
        return userMap.get(id);
    }

    public BotUser increaseExperience(Integer botUserId, int delta) {
        BotUser botUser = rankService.increaseExp(userMap.get(botUserId), delta);
        save();
        return botUser;
    }

    public Integer getCurrentRankExp(int rank) {
        return rankService.getCurrentRankExp(rank);
    }

}
