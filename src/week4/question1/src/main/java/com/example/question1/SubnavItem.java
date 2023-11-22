package com.example.question1;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class SubnavItem {
    private String productCategoryTitle;
    private String productCategorySubnavDesc;
    private List<String> subnavItemsTextList;

    public SubnavItem(){
        this.productCategoryTitle="";
        this.productCategorySubnavDesc="";
        this.subnavItemsTextList= new ArrayList<>();
    }

    public SubnavItem(String productCategoryTitle, String productCategorySubnavDesc, List<String> subnavItemsTextList) {
        this.productCategoryTitle = productCategoryTitle;
        this.productCategorySubnavDesc = productCategorySubnavDesc;
        this.subnavItemsTextList = subnavItemsTextList;
    }

    public String getProductCategoryTitle() {
        return productCategoryTitle;
    }

    public void setProductCategoryTitle(String productCategoryTitle) {
        this.productCategoryTitle = productCategoryTitle;
    }

    public String getProductCategorySubnavDesc() {
        return productCategorySubnavDesc;
    }

    public void setProductCategorySubnavDesc(String productCategorySubnavDesc) {
        this.productCategorySubnavDesc = productCategorySubnavDesc;
    }

    public List<String> getSubnavItemsTextList() {
        return subnavItemsTextList;
    }

    public void setSubnavItemsTextList(List<String> subnavItemsTextList) {
        this.subnavItemsTextList = subnavItemsTextList;
    }
}
