package com.playstation.model;

public class Game {
    private String title;

    private Integer score;

    public Game(String title, String score) {
        this.title = title;
        this.score = Integer.valueOf(score);
    }

    public Game(String title, Integer score) {
        this.title = title;
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
