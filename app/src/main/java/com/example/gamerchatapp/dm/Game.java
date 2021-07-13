package com.example.gamerchatapp.dm;

public class Game {
    private int id;
    private String name;
    private byte[] imageBlob;
    private String category;

    public Game() {

    }

    public Game(int id, String name, byte[] imageBlob, String category) {
        this.id = id;
        this.name = name;
        this.imageBlob = imageBlob;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageBlob=" + imageBlob +
                ", category='" + category + '\'' +
                '}';
    }

}

