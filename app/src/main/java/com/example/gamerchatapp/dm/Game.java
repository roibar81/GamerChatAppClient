package com.example.gamerchatapp.dm;


public class Game {
    private String name;
    private String image;
    private String catagory;
    private int id;

    public Game(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Game(int id, String name, String image, String catagory) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.catagory = catagory;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    @Override
    public String toString() {
        return "Game [catagory=" + catagory + ", id=" + id + ", image=" + image + ", name=" + name + "]";
    }

}
