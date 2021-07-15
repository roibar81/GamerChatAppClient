package com.example.gamerchatapp.dm;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Body implements Parcelable {
    private User user;
    private Game game;
    private ArrayList<User> userList;
    private ArrayList<Game> gameList;
    private ArrayList<ChatRoom> chatList;
    private String pattern;

    public Body() {
        this.userList = new ArrayList<>();
        this.gameList = new ArrayList<>();
        this.chatList = new ArrayList<>();
        this.pattern = "";
    }

    public Body(ArrayList<User> userList, ArrayList<Game> gameList, ArrayList<ChatRoom> chatList) {
        this.userList = userList;
        this.gameList = gameList;
        this.chatList = chatList;
        this.pattern = "";
    }

    public Body(ArrayList<User> userList, ArrayList<Game> gameList, ArrayList<ChatRoom> chatList, String pattern) {
        this.userList = userList;
        this.gameList = gameList;
        this.chatList = chatList;
        this.pattern = pattern;
    }

    public Body(User user, ArrayList<User> userList, ArrayList<Game> gameList, ArrayList<ChatRoom> chatList,String pattern) {
        this.user = user;
        this.userList = userList;
        this.gameList = gameList;
        this.chatList = chatList;
        this.pattern = pattern;
    }

    protected Body(Parcel in) {
        this.user = in.readParcelable(User.class.getClassLoader());
        this.game = in.readParcelable(Game.class.getClassLoader());
        this.userList = in.readArrayList(ArrayList.class.getClassLoader());
        this.gameList = in.readArrayList(ArrayList.class.getClassLoader());
        this.chatList = in.readArrayList(ArrayList.class.getClassLoader());
        this.pattern = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.user, flags);
        dest.writeParcelable((Parcelable) this.game, flags);
        dest.writeList(this.userList);
        dest.writeList(this.gameList);
        dest.writeList(this.chatList);
        dest.writeString(this.pattern);
    }

    public static final Creator<Body> CREATOR = new Creator<Body>() {
        @Override
        public Body createFromParcel(Parcel in) {
            return new Body(in);
        }

        @Override
        public Body[] newArray(int size) {
            return new Body[size];
        }
    };

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

    public ArrayList<ChatRoom> getChatList() {
        return chatList;
    }

    public void setChatList(ArrayList<ChatRoom> chatList) {
        this.chatList = chatList;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "Body{" +
                "user=" + user +
                ", game=" + game +
                ", userList=" + userList +
                ", gameList=" + gameList +
                ", chatList=" + chatList +
                ", pattern='" + pattern + '\'' +
                '}';
    }
}
