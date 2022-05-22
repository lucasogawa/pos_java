package com.ogawalucas.class04practice04activity.model;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    
    public static List<ScoreboardItem> scoreboardItems = new ArrayList<>();
    
    public static void add(String name) {
        scoreboardItems.stream()
                .filter(scoreboardItem -> scoreboardItem.getName().equals(name))
                .findFirst()
                .ifPresentOrElse(
                        scoreboardItem -> scoreboardItem.setPoints(scoreboardItem.getPoints() + 1),
                        () -> scoreboardItems.add(new ScoreboardItem(name, 1))
                );
    }
}
