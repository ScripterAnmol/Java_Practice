package com.example.question3.dto;

public class Classification {
    private int classId;
    private String description;

    public Classification() {
        this.classId=0;
        this.description="";
    }

    public Classification(String name, int classId, String description) {
        this.classId = classId;
        this.description = description;
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
