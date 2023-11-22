package com.example.question3.dto;

public class Decision {
    private Binder binder;
    private Applicant applicant;
    private Classifications classifications;
    private Trademark trademark;

    public Decision(){
        this.binder = new Binder();
        this.applicant = new Applicant();
        this.classifications = new Classifications();
        this.trademark = new Trademark();
    }

    public Decision(Binder binder, Applicant applicant, Classifications classifications, Trademark trademark) {
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

    public Classifications getClassifications() {
        return classifications;
    }

    public void setClassifications(Classifications classifications) {
        this.classifications = classifications;
    }

    public Trademark getTrademark() {
        return trademark;
    }

    public void setTrademark(Trademark trademark) {
        this.trademark = trademark;
    }
}
