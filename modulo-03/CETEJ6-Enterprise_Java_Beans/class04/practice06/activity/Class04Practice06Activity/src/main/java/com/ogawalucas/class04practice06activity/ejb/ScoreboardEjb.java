package com.ogawalucas.class04practice06activity.ejb;

import com.ogawalucas.class04practice06activity.model.Scoreboard;
import com.ogawalucas.class04practice06activity.model.ScoreboardItem;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class ScoreboardEjb {
 
    public ScoreboardEjb() {
        
    }
    
    public List<ScoreboardItem> getAll() {
        return Scoreboard.scoreboardItems;
    }
    
    public static void add(String name) {
        Scoreboard.scoreboardItems.stream()
                .filter(scoreboardItem -> scoreboardItem.getName().equals(name))
                .findFirst()
                .ifPresentOrElse(
                        scoreboardItem -> scoreboardItem.setPoints(scoreboardItem.getPoints() + 1),
                        () -> Scoreboard.scoreboardItems.add(new ScoreboardItem(name, 1))
                );
    }
}
