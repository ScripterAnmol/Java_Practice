package com.example.question3.dto;

public class Binder{
    private int id;
    private String domains;
    private String fa;
    private String fafd;
    private int applicationNo;


    public Binder() {
        this.id=0;
        this.domains="";
        this.fa="";
        this.fafd="";
        this.applicationNo=0;
    }

    public Binder(int id, String domains, String fa, String fafd, int applicationNo) {
        this.id = id;
        this.domains = domains;
        this.fa = fa;
        this.fafd = fafd;
        this.applicationNo = applicationNo;
    }

    public int getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(int applicationNo) {
        this.applicationNo = applicationNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public String getFafd() {
        return fafd;
    }

    public void setFafd(String fafd) {
        this.fafd = fafd;
    }
}
