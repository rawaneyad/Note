package com.example.note_project;

public class Notebooks {
    private String id ;
    private String name ;
    private String img;
//int img;
    public Notebooks(String id, String name , /*int img*/String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public Notebooks() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
/*
    public int getImg() {
        return img;
    }

    public void setImgNotebook(int img) {
        this.img = img;
    }*/
}
