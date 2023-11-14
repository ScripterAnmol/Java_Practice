package com.example.question1;

import org.springframework.stereotype.Component;

import java.util.List;

public class MenuItem{
    private String title;
    private List<SubnavItem> subnavItemsList;

    public MenuItem(){

    }
    public MenuItem(String title, List<SubnavItem> subnavItemsList) {
        this.title = title;
        this.subnavItemsList = subnavItemsList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubnavItem> getSubnavItemsList() {
        return subnavItemsList;
    }

    public void setSubnavItemsList(List<SubnavItem> subnavItemsList) {
        this.subnavItemsList = subnavItemsList;
    }
}
