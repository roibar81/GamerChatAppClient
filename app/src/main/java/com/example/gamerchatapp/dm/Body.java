package com.example.gamerchatapp.dm;

import java.util.ArrayList;

public class Body {

    private ArrayList<User> userList;
    private ArrayList<Game> gameList;
    private String pattern;

    public Body() {
        this.userList = new ArrayList<>();
        this.gameList = new ArrayList<>();
    }

    public Body(String pattern) {
        this.pattern = pattern;
        this.userList = new ArrayList<>();
        this.gameList = new ArrayList<>();
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    @Override
    public String toString() {
        return "Body{" +
                "userList=" + userList +
                ", gameList=" + gameList +
                ", pattern='" + pattern + '\'' +
                '}';
    }
}
