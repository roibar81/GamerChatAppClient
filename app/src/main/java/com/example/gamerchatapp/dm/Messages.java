package com.example.gamerchatapp.dm;

public class Messages {

    private int message_id;
    private int chat_room_id;
    private String user_name;
    private String message;
    private String timeStamp;

    public Messages(int chat_room_id, String user_name, String message) {
        this.chat_room_id = chat_room_id;
        this.user_name = user_name;
        this.message = message;
    }

    public Messages(int message_id, int chat_room_id, String user_name, String message, String timeStamp) {
        this.message_id = message_id;
        this.chat_room_id = chat_room_id;
        this.user_name = user_name;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getChat_room_id() {
        return chat_room_id;
    }

    public void setChat_room_id(int chat_room_id) {
        this.chat_room_id = chat_room_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "message_id=" + message_id +
                ", chat_room_id=" + chat_room_id +
                ", user_name='" + user_name + '\'' +
                ", message='" + message + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
