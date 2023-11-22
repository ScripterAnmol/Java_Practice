package com.example.question3.dto;

public class Trademark {
    private String name;
    private String type;
    private String image;

    public Trademark() {
        this.name="";
        this.type="";
        this.image="";
    }

    public Trademark(String name, String type, String image) {
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
