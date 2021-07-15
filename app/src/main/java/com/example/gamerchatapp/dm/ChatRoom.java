package com.example.gamerchatapp.dm;

public class ChatRoom {
    private int chatRoom_id;
    private String name;
    private int image;

    public ChatRoom(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public ChatRoom(int chatRoom_id, String name, int image) {
        this.chatRoom_id = chatRoom_id;
        this.name = name;
        this.image = image;
    }

    public int getChatRoom_id() {
        return chatRoom_id;
    }

    public void setChatRoom_id(int chatRoom_id) {
        this.chatRoom_id = chatRoom_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "chatRoom_id=" + chatRoom_id +
                ", name='" + name + '\'' +
                ", image=" + image +
                '}';
    }
}
