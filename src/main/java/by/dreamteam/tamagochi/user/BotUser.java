package by.dreamteam.tamagochi.user;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder(toBuilder = true)
public class BotUser {
    private Integer id;
    private String stickerId;
    private Integer rank;
    private Integer experience;
    private Timestamp lastFeed;
    private Timestamp lastPlay;
}
