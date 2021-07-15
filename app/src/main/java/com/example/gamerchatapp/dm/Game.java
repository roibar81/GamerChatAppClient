package com.example.gamerchatapp.dm;

import com.example.gamerchatapp.R;

public class Game {
    private String name;
    private byte[] imageBlob;
    private String category;
    private int image;

    public Game() {

    }

    public Game(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public Game(String name, byte[] imageBlob, String category) {
        this.name = name;
        this.imageBlob = imageBlob;
        this.category = category;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Game{" +
                ", name='" + name + '\'' +
                ", imageBlob=" + imageBlob +
                ", category='" + category + '\'' +
                '}';
    }

}

