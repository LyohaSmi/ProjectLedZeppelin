package com.javarush.smirnov;

import java.util.List;

public class Scene {
    private int id;
    private String text;
    private List<Option> options;
    private boolean gameOver = false;

    private String endType = null; // "win", "lose", или null если не конец
    public Scene(int id, String text, String endType) {
        this.id = id;
        this.text = text;
        this.gameOver = true;
        this.endType = endType;
    }

    public Scene(int id, String text, List<Option> options){
        this.id = id;
        this.text = text;
        this.options = options;
    }


    public int getId() { return id; }
    public String getText() { return text; }
    public List<Option> getOptions() { return options; }
    public boolean isGameOver() { return gameOver; }
    public String getEndType() { return endType; }
}
