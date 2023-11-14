package com.example.question1;

import org.springframework.stereotype.Component;

import java.util.List;

public class SubnavItem {
    private String productCategoryTitle;
    private String productCategorySubnavDesc;
    private List<String> subnavItemsTextList;

    // Constructors, getters, and setters...

    // Example constructor
    public SubnavItem(String productCategoryTitle, String productCategorySubnavDesc, List<String> subnavItemsTextList) {
        this.productCategoryTitle = productCategoryTitle;
        this.productCategorySubnavDesc = productCategorySubnavDesc;
        this.subnavItemsTextList = subnavItemsTextList;
    }
}
