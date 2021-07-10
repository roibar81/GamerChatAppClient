package com.example.gamerchatapp.dm;

import java.util.ArrayList;

public class Body {
    private User user;
    private Game game;
    private ArrayList<User> userList;
    private ArrayList<Game> gameList;
    private String pattern;

    public Body() {
        this.userList = new ArrayList<>();
        this.gameList = new ArrayList<>();
        this.pattern = "";
    }

    public Body(String pattern) {
        this.pattern = pattern;
        this.userList = new ArrayList<>();
        this.gameList = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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
                "user=" + user +
                ", game=" + game +
                ", userList=" + userList +
                ", gameList=" + gameList +
                ", pattern='" + pattern + '\'' +
                '}';
    }
}
