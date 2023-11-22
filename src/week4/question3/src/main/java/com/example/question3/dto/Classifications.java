package com.example.question3.dto;

public class Classifications {
    private Classification[] classificationArr;

    public Classifications(){
        this.classificationArr = new Classification[0];
    }

    public Classifications(Classification[] classificationArr) {
        this.classificationArr = classificationArr;
    }

    public Classification[] getClassificationArr() {
        return classificationArr;
    }

    public void setClassificationArr(Classification[] classificationArr) {
        this.classificationArr = classificationArr;
    }
}