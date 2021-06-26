package com.example.gamerchatapp.dm;

public class Header {

    private String action;

    public Header(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Header{" +
                "action='" + action + '\'' +
                '}';
    }

}
