package com.example.question3.pages;

import com.example.question3.dto.Classification;
import com.example.question3.dto.Decision;
import com.example.question3.services.FileSavingService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationDetails{

//    @Autowired
    private WebClient client;
    private HtmlPage page;
    private FileSavingService fileSavingService;


    public ApplicationDetails(){
        this.client=null;
        this.page=null;
        this.fileSavingService=null;
    }

//    public ApplicationDetails(HtmlPage page, FileSavingService fileSavingService){
//        this.page = page;
//        this.fileSavingService = fileSavingService;
//    }

    public ApplicationDetails(WebClient client, HtmlPage page, FileSavingService fileSavingService){
        this.client = client;
        this.page = page;
        this.fileSavingService = fileSavingService;
    }

    public int getApplicationNumber(){
        //     int applicationNumber = Integer.parseInt(page.getElementById("MainContent_ctrlTM_txtAppNr").getTextContent());

//        HtmlTableCell applicationNumberCell = (HtmlTableCell) page.getFirstByXPath("//*[@id='MainContent_tabBiblio']/div[1]//tbody/tr/td[normalize-space()='Application Number']/following-sibling::td");
        HtmlSpan applicationNumberCell = page.getFirstByXPath("//span[contains(@id,'txtAppNr')]");
        //     Getting Application Number
        int applicationNumber = Integer.parseInt(applicationNumberCell.getTextContent().trim());
        return applicationNumber;
    }

    public String getRedPartyName(){
        try{
            String redParty="";
            boolean hasNext= false;
            int redPartyCurPage=1;

            do{
                String linkedCasesTbodyXpath = "//*[@id='MainContent_ctrlProcedureList_gvwIPCases']/tbody";
                HtmlTableBody linkedCasesTbody = page.getFirstByXPath(linkedCasesTbodyXpath);
                if(linkedCasesTbody != null){
                    List<HtmlTableRow> rows = linkedCasesTbody.getRows();

                    for (int i = 1; i < rows.size(); i++) {
                        HtmlTableRow row = rows.get(i);
                        if (row.getAttribute("class").contains("gridview_pager")) {
                            continue;
                        }

                        HtmlTableCell secondCell = row.getCell(1);
                        if (secondCell != null && secondCell.getTextContent().trim().startsWith("Proceedings")) {
                            HtmlTableCell lastCell = row.getCell(row.getCells().size() - 1);
                            redParty = lastCell.getTextContent().trim();
                            System.out.println(redParty);
                        }
                    }

//                    Code for finding the next sibling for page change
                    String pagerCellsXpath = "//*[@id='MainContent_ctrlProcedureList_gvwIPCases']/tbody/tr[contains(@class, 'gridview_pager')]/td[1]/table/tbody/tr/td";
                    List<HtmlTableCell> pagerCells = page.getByXPath(pagerCellsXpath);

                    if (pagerCells != null && !pagerCells.isEmpty()) {
                        for (HtmlTableCell cell : pagerCells) {
                            DomElement nextSibling = cell.getNextElementSibling();
                            if (cell != null && Integer.parseInt(cell.getTextContent().trim()) == redPartyCurPage && nextSibling != null) {
                                HtmlAnchor anchor = nextSibling.getFirstByXPath(".//a");
                                if (anchor != null) {
                                    anchor.click();
                                    Thread.sleep(30000);
                                    this.page = (HtmlPage) client.getCurrentWindow().getEnclosedPage();
                                    redPartyCurPage++;
                                    hasNext=true;
                                }else{
                                    hasNext=false;
                                }
                                break;
                            }else{
                                hasNext=false;
                            }
                        }
                    }else{
                        hasNext=false;
                    }
                }else{
                    System.err.println("Element not found");
                    hasNext=false;
                }

            }while(hasNext);

            return redParty;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "";
        }
    }

    public List<Classification> getClassifications(){
        List<Classification> classifications = new ArrayList<>();
// Getting Classification Info
//            HtmlTableBody classificationTbody = page.getFirstByXPath("//*[@id='MainContent_ctrlTM_tblClass']/table/tbody");
        try{
            boolean hasNext = false;
            int curClassificationPage = 1;
            do{
                HtmlTableBody classificationTbody = page.getFirstByXPath("//div[contains(@id,'tblClass')]//table/tbody");

                if (classificationTbody != null) {
                    for (HtmlTableRow row : classificationTbody.getRows()) {
                        HtmlTableCell firstCell = row.getCell(0);
                        if (firstCell != null && "Goods & Services Specification".equals(firstCell.getTextContent().trim())) {
                            HtmlTableCell secondCell = row.getCell(1);
                            if(secondCell != null){
                                HtmlTableBody innerTableBody = secondCell.getFirstByXPath(".//table/tbody");
                                if (innerTableBody != null){
                                    for (HtmlTableRow innerRow : innerTableBody.getRows()) {
                                        if (innerRow.getFirstByXPath(".//th") != null) {
                                            continue;
                                        }
                                        if (innerRow.getAttribute("class").contains("gridview_pager")) {
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

                                        classifications.add(newClassification);
                                    }

//                                  Code for finding the next sibling for page change
                                    String pagerCellsXpath = ".//table/tbody/tr[contains(@class, 'gridview_pager')]/td[1]/table/tbody/tr/td";
                                    List<HtmlTableCell> pagerCells = secondCell.getByXPath(pagerCellsXpath);

                                    if (pagerCells != null && !pagerCells.isEmpty()){
                                        for (HtmlTableCell cell : pagerCells) {
                                            DomElement nextSibling = cell.getNextElementSibling();
                                            if (cell != null && Integer.parseInt(cell.getTextContent().trim())==curClassificationPage && nextSibling != null) {
                                                HtmlAnchor anchor = nextSibling.getFirstByXPath(".//a");
                                                if (anchor != null) {
                                                    anchor.click();
                                                    Thread.sleep(30000);
                                                    this.page = (HtmlPage) client.getCurrentWindow().getEnclosedPage();
                                                    hasNext=true;
                                                    curClassificationPage++;
                                                }else{
                                                    hasNext=false;
                                                }
                                                break;
                                            }else{
                                                hasNext=false;
                                            }
                                        }
                                    }
                                }else{
                                    System.err.println("Element not found");
                                    hasNext=false;
                                }
                            }
                        }
                    }
                } else {
                    System.err.println("Element not found");
                }
            }while(hasNext);

            for(int historyBack=1;historyBack<curClassificationPage;historyBack++){
                client.getCurrentWindow().getHistory().back();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return classifications;
    }

    public Decision getDecision(){
        try{
            Decision res= new Decision();

            int applicationNumber = getApplicationNumber();
            String redParty = getRedPartyName();
            List<Classification> classifications = getClassifications();

            String fafd="";

            int applicantId = 0;
            String applicantName = "";
            String applicantAddress = "";

            String trademarkName = "";
            String trademarkType = "";
            String trademarkImage = "";

// Getting Applicant Details
//            String applicantTableBodyXPath = "//table[@id='MainContent_ctrlTM_ctrlApplicant_ctrlApplicant_gvCustomers']/tbody";

            String applicantTableBodyXPath = "//table[contains(@id, 'ctrlApplicant_ctrlApplicant_gvCustomers')]/tbody";
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

//          Getting Trademark Info
//          String tableXPath = "//*[@id='MainContent_ctrlTM_ctl30']/div/table/tbody";
            String tableXPath = "//div[contains(@id,'tblMarkInfo')]//table[@class='alternated']/tbody";
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

            res.setClassifications(classifications);
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
