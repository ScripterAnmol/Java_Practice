package com.example.question3.dto;

import java.util.ArrayList;
import java.util.List;

public class Decision {
    private Binder binder;
    private Applicant applicant;
    private List<Classification> classifications;
    private Trademark trademark;

    public Decision(){
        this.binder = new Binder();
        this.applicant = new Applicant();
        this.trademark = new Trademark();
        this.classifications = new ArrayList<>();
    }

    public Decision(Binder binder, Applicant applicant, List<Classification> classifications, Trademark trademark) {
        this.binder = binder;
        this.applicant = applicant;
        this.classifications = classifications;
        this.trademark = trademark;
    }

    public Binder getBinder() {
        return binder;
    }

    public void setBinder(Binder binder) {
        this.binder = binder;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public List<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<Classification> classifications) {
        this.classifications = classifications;
    }

    public Trademark getTrademark() {
        return trademark;
    }

    public void setTrademark(Trademark trademark) {
        this.trademark = trademark;
    }

    public void addClassification(Classification newClassification) {
        classifications.add(newClassification);
    }
}
