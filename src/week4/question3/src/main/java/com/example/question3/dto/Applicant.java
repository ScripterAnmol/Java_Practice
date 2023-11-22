package com.example.question3.dto;

public class Applicant{
    private int id;
    private String name;
    private String address;

    public Applicant() {
        this.id=0;
        this.name="";
        this.address="";
    }

    public Applicant(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
