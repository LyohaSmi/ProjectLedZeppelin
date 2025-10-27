package com.javarush.smirnov;

public class Option {
    private String text;               // Текст варианта выбора
    private int nextSceneId;

    public Option(int nextSceneId, String text){
        this.text = text;
        this.nextSceneId = nextSceneId;
    }

    public String getText() {
        return text;
    }
    public int getNextSceneId() {
        return nextSceneId;
    }
}
