package com.example.gamerchatapp.dm;

public class ChatRoom {
    private int chatRoom_id;
    private String name;

    public ChatRoom(String name) {
        this.name = name;
    }

    public ChatRoom(int chatRoom_id, String name) {
        this.chatRoom_id = chatRoom_id;
        this.name = name;
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

    @Override
    public String toString() {
        return "ChatRoom{" +
                "chatRoom_id=" + chatRoom_id +
                ", name='" + name + '\'' +
                '}';
    }
}
