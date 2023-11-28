package com.example.question3.pages;

import com.example.question3.dto.Decision;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SearchResults{

//    @Autowired
    private WebClient client;
    private HtmlPage page;
//    private int totalCase = 0;
//    private int casePerPage = 0;
//    private int totalPage = 0;

    private int curSearchResultPage = 1;

    public SearchResults(){
        this.client=null;
        this.page=null;
//        this.totalCase = 0;
//        this.casePerPage = 0;
//        this.totalPage = 0;
    }


//    public SearchResults(HtmlPage page){
//        this.page = page;
//        this.totalCase = getTotalCase();
//        this.casePerPage = getCasePerPage();
//        this.totalPage = getTotalPage(this.totalCase,this.casePerPage);
//    }

    public SearchResults(WebClient client,HtmlPage page){
        this.client = client;
        this.page = page;
//        this.totalCase = getTotalCase();
//        this.casePerPage = getCasePerPage();
//        this.totalPage = getTotalPage(this.totalCase,this.casePerPage);
    }

    public WebClient getClient() {
        return client;
    }

    public void setClient(WebClient client) {
        this.client = client;
    }

    public HtmlPage getPage() {
        return page;
    }

    public void setPage(HtmlPage page) {
        this.page = page;
    }

//    public void setTotalCase(int totalCase) {
//        this.totalCase = totalCase;
//    }
//
//    public void setCasePerPage(int casePerPage) {
//        this.casePerPage = casePerPage;
//    }
//
//    public int getTotalPage() {
//        return totalPage;
//    }
//
//    public void setTotalPage(int totalPage) {
//        this.totalPage = totalPage;
//    }

    public int getTotalCase(){
        int totalCase=0;

        DomElement element = page.getElementById("MainContent_ctrlTMSearch_ctrlProcList_hdrNbItems");

        if (element != null) {
            String content = element.getTextContent().trim();
            String numericPart = content.replaceAll("\\D+", "");

            if (!numericPart.isEmpty()) {
                totalCase = Integer.parseInt(numericPart);
            }else{
                System.err.println("No numeric part found in the content");
            }
        }else{
            System.err.println("Element not found");
        }

        return totalCase;
    }

    public int getCasePerPage(){
        int casePerPage=0;
        HtmlSelect totalCaseOnPageSelector = page.getFirstByXPath("//table[@id='MainContent_ctrlTMSearch_ctrlProcList_gvwIPCases']/tbody/tr[last()]/td/div[2]/select");

        if(totalCaseOnPageSelector != null){
            String selectedValue = totalCaseOnPageSelector.getSelectedOptions().get(0).getValueAttribute();
            casePerPage = Integer.parseInt(selectedValue);
        }else{
            System.err.println("Select element not found");
        }

        return casePerPage;
    }

    public int getTotalPage(int totalCase,int casePerPage){
        int totalPage =0;

        if(casePerPage==0){
            return 0;
        }else{
            totalPage = (int)Math.ceil((double)totalCase/casePerPage);
        }

        return totalPage;
    }

    public HtmlPage getFirstCase(){
        try{
//            sorting chronologically
//            HtmlAnchor caseNumberChronology = page.getFirstByXPath("//a[contains(text(),'Case Number')]");
//            caseNumberChronology.click();
//            Thread.sleep(30000);

//            clicking on first case
            HtmlAnchor firstCase = (HtmlAnchor) page.getFirstByXPath("//table[@id='MainContent_ctrlTMSearch_ctrlProcList_gvwIPCases']/tbody/tr[2]/td[2]/a");
            firstCase.click();
            Thread.sleep(30000);
            HtmlPage newPage  = (HtmlPage) client.getCurrentWindow().getEnclosedPage();

            System.out.println("done");
            return newPage;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void sortChronologically(){
        try{
            HtmlAnchor caseNumberChronology = page.getFirstByXPath("//a[contains(text(),'Case Number')]");
            caseNumberChronology.click();
            Thread.sleep(30000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int getTotalCaseOnCurrentPage(){
        try{
            int currentPageTotalCase=0;
            DomElement table = page.getElementById("MainContent_ctrlTMSearch_ctrlProcList_gvwIPCases");

            if (table != null) {
                Iterable<HtmlTableRow> trElements = table.getByXPath(".//tbody/tr[not(.//tr)]");

                for (HtmlTableRow tr : trElements) {
                    currentPageTotalCase++;
                }
            } else {
                System.err.println("Tbody element not found");
            }

            System.out.println("Number of direct child tr elements: " + currentPageTotalCase);

            return  currentPageTotalCase-2;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public HtmlPage getCurrentPageCase(int caseNumber){
        try{
            HtmlAnchor currentCase = (HtmlAnchor) page.getFirstByXPath("//table[@id='MainContent_ctrlTMSearch_ctrlProcList_gvwIPCases']/tbody/tr["+(caseNumber+1)+"]/td[2]/a");
            currentCase.click();
            Thread.sleep(30000);
            HtmlPage newPage  = (HtmlPage) client.getCurrentWindow().getEnclosedPage();

            System.out.println("done");
            return newPage;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

//    public void nextPage(int curPage) {
    public boolean nextPage(){
        boolean hasNext=false;
        try{
            String casesTableXpath = "//table[@id='MainContent_ctrlTMSearch_ctrlProcList_gvwIPCases']/tbody/tr[last()]/td/table/tbody";
            HtmlTableBody tableBody = page.getFirstByXPath(casesTableXpath);

            if (tableBody != null) {
                Iterable<HtmlTableRow> rowIterable = tableBody.getRows();
                for (HtmlTableRow row : rowIterable) {
                    Iterable<HtmlTableCell> cells = row.getCells();
                    for (HtmlTableCell cell : cells) {
                        if (cell.getTextContent().trim().equals(Integer.toString(curSearchResultPage))){
                            DomElement nextCell = cell.getNextElementSibling();
                            if (nextCell != null) {
                                HtmlAnchor anchor = nextCell.getFirstByXPath(".//a");
                                if (anchor != null) {
                                    anchor.click();
                                    Thread.sleep(30000);
                                    this.page = (HtmlPage) client.getCurrentWindow().getEnclosedPage();
                                    hasNext=true;
                                    System.out.println("Clicked on the anchor element in the next td " + curSearchResultPage);
                                } else {
                                    hasNext=false;
                                    System.err.println("Anchor element not found in the next td " + curSearchResultPage);
                                }
                            } else {
                                hasNext=false;
                                System.err.println("Next td not found");
                            }
                            break;
                        }else{
                            hasNext=false;
                        }
                    }
                }
            }

            curSearchResultPage++;

            return hasNext;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}
