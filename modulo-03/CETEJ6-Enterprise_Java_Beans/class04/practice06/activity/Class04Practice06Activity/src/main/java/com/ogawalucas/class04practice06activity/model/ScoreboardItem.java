package com.ogawalucas.class04practice06activity.model;

import java.io.Serializable;

public class ScoreboardItem implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String name;
    private int points;

    public ScoreboardItem() {
        
    }

    public ScoreboardItem(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    
}
