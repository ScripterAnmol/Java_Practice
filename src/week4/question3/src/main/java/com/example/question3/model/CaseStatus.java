package com.example.question3.model;

public class CaseStatus{
    private String description;
    private String caseType;

    public CaseStatus() {
        this.description="";
        this.caseType="";
    }

    public CaseStatus(String description, String caseType) {
        this.description = description;
        this.caseType = caseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }
}
