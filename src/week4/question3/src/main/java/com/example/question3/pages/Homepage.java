package com.example.question3.pages;

import com.example.question3.model.CaseStatus;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Homepage{
//    @Autowired
    private WebClient client;
    protected HtmlPage page;

    public Homepage(){
        this.page=null;
        this.client=null;
    }

    public Homepage(WebClient client,HtmlPage page){
        this.client = client;
        this.page = page;
    }

    public HtmlPage searchByClassificationOrStatus(CaseStatus[] status){
        try{
            // code to expand classification menu
            String classificationExpansionId = "MainContent_ctrlTMSearch_hdrClassifStatusCriteria_lblheader";
            HtmlSpan classificationExpansion = (HtmlSpan) page.getElementById(classificationExpansionId);
            if (classificationExpansion != null) {
                classificationExpansion.click();
            } else {
                System.err.println("Anchor with id MainContent_ctrlTMSearch_hdrClassifStatusCriteria_lblheader not found");
            }

            // code to open Modal for selecting Status
            String selectStatusAnchorId = "MainContent_ctrlTMSearch_ctrlCaseStatusSearchDialog_lnkBtnSearch";
            HtmlAnchor selectStatusAnchor = (HtmlAnchor) page.getElementById(selectStatusAnchorId);
            if (selectStatusAnchor != null) {
                selectStatusAnchor.click();
            } else {
                System.err.println("Anchor with id MainContent_ctrlTMSearch_ctrlCaseStatusSearchDialog_lnkBtnSearch not found");
            }

            // code for selecting Status
            String tbodyXPath = "//table[@id='MainContent_ctrlTMSearch_ctrlCaseStatusSearchDialog_ctrlCaseStatusSearch_ctrlCaseStatusList_gvCaseStatuss']/tbody";
            HtmlTableBody tbody = page.getFirstByXPath(tbodyXPath);

            if (tbody != null) {
                for (HtmlTableRow row : tbody.getRows()){
                    if (row.getCells().size() >= 3) {
                        HtmlTableCell secondTd = row.getCell(1);
                        HtmlTableCell thirdTd = row.getCell(2);

                        String secondTdValue = secondTd.getTextContent().trim();
                        String thirdTdValue = thirdTd.getTextContent().trim();

                        for (CaseStatus caseStatus : status) {
                            String variableValue1 = caseStatus.getDescription();
                            String variableValue2 = caseStatus.getCaseType();

                            if (secondTdValue.equals(variableValue1) && thirdTdValue.equals(variableValue2)) {
                                HtmlTableDataCell firstTd = (HtmlTableDataCell) row.getCell(0);
                                HtmlCheckBoxInput checkbox = firstTd.getFirstByXPath(".//input[@type='checkbox']");
                                if (checkbox != null) {
                                    checkbox.setChecked(true);
                                }
                            }
                        }
                    }
                }
            }

            HtmlAnchor closeSelectAnchor = (HtmlAnchor) page.getElementById("MainContent_ctrlTMSearch_ctrlCaseStatusSearchDialog_lnkBtnSelect");
            if (closeSelectAnchor != null){
                closeSelectAnchor.click();
            }else{
                System.err.println("Anchor with id MainContent_ctrlTMSearch_ctrlCaseStatusSearchDialog_lnkBtnSelect not found");
            }

            Thread.sleep(30000);

            HtmlAnchor searchButtonAnchor = (HtmlAnchor) page.getElementById("MainContent_ctrlTMSearch_lnkbtnSearch");
            if (searchButtonAnchor != null) {
                searchButtonAnchor.click();
                Thread.sleep(50000);
                return page;
            } else {
                System.err.println("Anchor with id MainContent_ctrlTMSearch_lnkbtnSearch not found");
                return null;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

}
