package com.example.question3.services;

import com.example.question3.model.CaseStatus;
import com.example.question3.dto.Decision;
import com.example.question3.pages.Homepage;
import com.example.question3.pages.SearchResults;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DataCrawlerService {
    private final WebClient client;

    @Autowired
    public DataCrawlerService(WebClient client){
        this.client=client;

//        this.client.getOptions().setJavaScriptEnabled(false);
//        this.client.getOptions().setCssEnabled(false);
    }

    public Decision getFirstCase(){

        try {
            CaseStatus[] caseStatusArray = {
                    new CaseStatus("Under Opposition", "Trade Mark"),
                    new CaseStatus("Under Opposition", "International Registration"),
            };

            Homepage homepage =  new Homepage(client,client.getPage("https://app.iponz.govt.nz/app/Extra/IP/TM/Qbe.aspx?sid=638362108144236733&op=EXTRA_tm_qbe&fcoOp=EXTRA__Default&directAccess=true"));
            HtmlPage searchResultsPage = homepage.searchByClassificationOrStatus(caseStatusArray);

            System.out.println(searchResultsPage.getTitleText());
            // Process the search results page or perform additional actions
            SearchResults searchResults = new SearchResults(client,searchResultsPage);

            Decision res = searchResults.getFirstCase();

            return res;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
