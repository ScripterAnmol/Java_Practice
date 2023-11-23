package com.example.question3.pages;

import com.example.question3.dto.Decision;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

public class SearchResults{
    protected WebClient client;
    protected HtmlPage page;

    public SearchResults(){
        this.page=null;
    }
    public SearchResults(WebClient client,HtmlPage page){
        this.client = client;
        this.page = page;
    }

    public HtmlPage getFirstCase(){
        try{
//            sorting chronologically
            HtmlAnchor caseNumberChronology = page.getFirstByXPath("//a[contains(text(),'Case Number')]");
            caseNumberChronology.click();
            Thread.sleep(30000);

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
}
