package com.example.question3.pages;

import com.example.question3.dto.Classification;
import com.example.question3.dto.Decision;
import com.example.question3.services.FileSavingService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ApplicationDetails{
    private WebClient client;
    private HtmlPage page;
    private FileSavingService fileSavingService;


    public ApplicationDetails(){
        this.page=null;
    }

    public ApplicationDetails(WebClient client, HtmlPage page, FileSavingService fileSavingService){
        this.client = client;
        this.page = page;
        this.fileSavingService = fileSavingService;
    }


    public Decision getDecision(){
        try{
            Decision res= new Decision();

//            Getting Application Number
            int applicationNumber = Integer.parseInt(page.getElementById("MainContent_ctrlTM_txtAppNr").getTextContent());
            String redParty="";
            String fafd="";

            int applicantId = 0;
            String applicantName = "";
            String applicantAddress = "";

            String trademarkName = "";
            String trademarkType = "";
            String trademarkImage = "";

// Getting Applicant Details
            String applicantTableBodyXPath = "//table[@id='MainContent_ctrlTM_ctrlApplicant_ctrlApplicant_gvCustomers']/tbody";
            HtmlTableBody applicantTableBody = page.getFirstByXPath(applicantTableBodyXPath);

            if (applicantTableBody != null) {
                List<HtmlTableRow> rows = applicantTableBody.getByXPath(".//tr");

                if (rows.size() > 1) {
                    HtmlTableRow secondRow = rows.get(1);

                    List<HtmlTableCell> cells = secondRow.getCells();

                    applicantId = Integer.parseInt(cells.get(0).getTextContent().trim());
                    applicantName = cells.get(1).getTextContent().trim();
                    applicantAddress = cells.get(2).getTextContent().trim();
                } else {
                    System.out.println("Table has less than 2 rows");
                }
            } else {
                System.out.println("Table with ID MainContent_ctrlTM_ctrlApplicant_ctrlApplicant_gvCustomers not found");
            }

// Getting Trademark Info
            String tableXPath = "//*[@id='MainContent_ctrlTM_ctl30']/div/table/tbody";
            HtmlTableBody tableBody = page.getFirstByXPath(tableXPath);

            if (tableBody != null) {
                for (HtmlTableRow row : tableBody.getRows()) {
                    HtmlTableCell firstCell = row.getCell(0);
                    if (firstCell != null && "Mark Name".equals(firstCell.getTextContent().trim())) {
                        HtmlTableCell secondCell = row.getCell(1);
                        if (secondCell != null) {
                            trademarkName = secondCell.getTextContent().trim();
                            System.out.println(trademarkName);
                        }
                    } else if (firstCell != null && "Mark Type".equals(firstCell.getTextContent().trim())) {
                        HtmlTableCell secondCell = row.getCell(1);
                        if (secondCell != null) {
                            trademarkType=secondCell.getTextContent().trim();
                            System.out.println(trademarkType);
                        }
                    }else if (firstCell != null && "Picture".equals(firstCell.getTextContent().trim())) {
                        HtmlTableCell imageCell = row.getCell(1);

                        HtmlAnchor thumbnailLink = imageCell.getFirstByXPath(".//a[@class='device devicePopup']");
                        if (thumbnailLink != null) {
                            String thumbnailUrl = thumbnailLink.getAttribute("thmb");
                            trademarkImage = fileSavingService.fetchAndConvertToBase64(thumbnailUrl);
                            System.out.println("Image Base64: " + trademarkImage);
                        }
                    }
                }
            } else {
                System.out.println("Table body not found");
            }

// Getting Classification Info
            HtmlTableBody classificationTbody = page.getFirstByXPath("//*[@id='MainContent_ctrlTM_tblClass']/table/tbody");
            if (classificationTbody != null) {
                for (HtmlTableRow row : classificationTbody.getRows()) {
                    HtmlTableCell firstCell = row.getCell(0);
                    if (firstCell != null && "Goods & Services Specification".equals(firstCell.getTextContent().trim())) {
                        HtmlTableCell secondCell = row.getCell(1);
                        if (secondCell != null) {
                            HtmlTable innerTable = secondCell.getFirstByXPath(".//table");
                            if (innerTable != null) {
                                for (HtmlTableRow innerRow : innerTable.getRows()) {
                                    if (innerRow.getFirstByXPath(".//th") != null) {
                                        continue;
                                    }
                                    Classification newClassification = new Classification();
                                    int i=0;
                                    for (HtmlTableCell innerCell : innerRow.getCells()){
                                        if(i==0){
                                            newClassification.setClassId(Integer.parseInt(innerCell.getTextContent().trim()));
                                        }else{
                                            newClassification.setDescription(innerCell.getTextContent().trim());
                                        }
                                        i++;
                                    }

                                    res.addClassification(newClassification);
                                }
                            }
                        }
                    }
                }
            } else {
                System.err.println("Element not found");
            }

//            Getting Red Party Name
            HtmlTableBody linkedCasesTbody = page.getFirstByXPath("//*[@id='MainContent_ctrlProcedureList_gvwIPCases']/tbody");

            if (linkedCasesTbody != null) {
                List<HtmlTableRow> rows = linkedCasesTbody.getRows();

                for (int i = 1; i < rows.size(); i++) {
                    HtmlTableRow row = rows.get(i);
                    HtmlTableCell secondCell = row.getCell(1);

                    if (secondCell != null && secondCell.getTextContent().trim().startsWith("Proceedings")) {
                        HtmlTableCell lastCell = row.getCell(row.getCells().size() - 1);
                        redParty=lastCell.getTextContent().trim();
                        System.out.println(redParty);
                    }
                }
            } else {
                System.err.println("Element not found");
            }

//            Get FAFD
            HtmlTableBody tbody = page.getFirstByXPath("//*[@id='MainContent_ctrlHistoryList_gvHistory']/tbody");

            if (tbody != null) {
                for (HtmlTableRow row : tbody.getRows()) {
                    HtmlTableCell firstCell = row.getCell(0);

                    if (firstCell != null && "Opposition proceeding commenced".equals(firstCell.getTextContent().trim())) {
                        HtmlTableCell thirdCell = row.getCell(2);
                        fafd=thirdCell.getTextContent().trim();
                        System.out.println(fafd);
                    }
                }
            } else {
                System.err.println("Element not found");
            }

            res.getBinder().setDomains("TM");
            res.getBinder().setFa("opposition");
            res.getBinder().setApplicationNo(applicationNumber);
            res.getBinder().setRedParty(redParty);
            res.getBinder().setFafd(fafd);


            res.getApplicant().setId(applicantId);
            res.getApplicant().setName(applicantName);
            res.getApplicant().setAddress(applicantAddress);

            res.getTrademark().setName(trademarkName);
            res.getTrademark().setType(trademarkType);
            res.getTrademark().setImage(trademarkImage);

            return res;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }}

    public String getReferenceDocument(){
        HtmlAnchor fileLink = page.getFirstByXPath("//*[@id='MainContent_ctrlDocumentList_gvDocuments_hnkView_0']");

        if (fileLink != null) {
            String fileUrl = fileLink.getHrefAttribute();
            return fileUrl;
        } else {
            System.err.println("Element not found");
            return null;
        }
    }
}
