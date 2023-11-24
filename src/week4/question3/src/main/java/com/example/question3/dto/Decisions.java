package com.example.question3.dto;

import java.util.ArrayList;
import java.util.List;

public class Decisions{

    private int totalDecisions;
    private List<Decision> decisions;

    public Decisions(){
        this.totalDecisions=0;
        this.decisions = new ArrayList<>();
    }
    public Decisions(int totalDecisions, List<Decision> decisions) {
        this.totalDecisions = totalDecisions;
        this.decisions = decisions;
    }

    public int getTotalDecisions() {
        return totalDecisions;
    }

    public void setTotalDecisions(int totalDecisions) {
        this.totalDecisions = totalDecisions;
    }

    public List<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<Decision> decisions) {
        this.decisions = decisions;
        this.totalDecisions = decisions.size();
    }

    public void addDecision(Decision newDecision) {
        decisions.add(newDecision);
        this.totalDecisions = decisions.size();
    }
}
