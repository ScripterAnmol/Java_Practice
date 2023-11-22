package com.example.question3.dto;

public class Classification {
    private String name;
    private int classId;

    private String description;

    public Classification() {
        this.name="";
        this.classId=0;
        this.description="";
    }

    public Classification(String name, int classId, String description) {
        this.name = name;
        this.classId = classId;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
