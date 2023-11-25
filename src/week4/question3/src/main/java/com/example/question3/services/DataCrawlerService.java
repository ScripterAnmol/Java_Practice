package com.example.question3.services;

import com.example.question3.dto.Decisions;
import com.example.question3.model.CaseStatus;
import com.example.question3.dto.Decision;
import com.example.question3.pages.ApplicationDetails;
import com.example.question3.pages.Homepage;
import com.example.question3.pages.SearchResults;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class DataCrawlerService {
    private final WebClient client;
    private final FileSavingService fileSavingService;
    @Autowired
    public DataCrawlerService(WebClient client, FileSavingService fileSavingService){
        this.client=client;
        this.fileSavingService = fileSavingService;

//        this.client.getOptions().setJavaScriptEnabled(false);
//        this.client.getOptions().setCssEnabled(false);
    }

    public Decision getFirstTrademarkCase(){
        try {
            CaseStatus[] caseStatusArray = {
                    new CaseStatus("Under Opposition", "Trade Mark"),
                    new CaseStatus("Under Opposition", "International Registration"),
            };

            Homepage homepage =  new Homepage(client,client.getPage("https://app.iponz.govt.nz/app/Extra/IP/TM/Qbe.aspx?sid=638362108144236733&op=EXTRA_tm_qbe&fcoOp=EXTRA__Default&directAccess=true"));
            HtmlPage searchResultsPage = homepage.searchByClassificationOrStatus(caseStatusArray);

            // Process the search results page or perform additional actions
            SearchResults searchResults = new SearchResults(client,searchResultsPage);
            searchResults.sortChronologically();

            ApplicationDetails applicationDetails = new ApplicationDetails(client,searchResults.getFirstCase(),fileSavingService);
            Decision res = applicationDetails.getDecision();
            String decisionReferenceDocument = applicationDetails.getReferenceDocument();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

            String jsFilename = "decision_reference_"+res.getBinder().getApplicationNo()+"_"+dateFormat.format(new Date())+ ".js";
            String fileLocation = "C:/Users/U6074524/IdeaProjects/anmolPractice/src/week4/question3/output";

            String referenceDocumentName = "decision_reference_document_"+res.getBinder().getApplicationNo()+"_"+dateFormat.format(new Date());
            fileSavingService.saveObjectToFile(res,jsFilename,fileLocation);
            fileSavingService.downloadFile(decisionReferenceDocument,referenceDocumentName,fileLocation);

            System.out.println("done");
            return res;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public Decisions getAllTrademarkCase(){
        try {
            CaseStatus[] caseStatusArray = {
                    new CaseStatus("Under Opposition", "Trade Mark"),
                    new CaseStatus("Under Opposition", "International Registration"),
            };
            Decisions res = new Decisions();

            Homepage homepage =  new Homepage(client,client.getPage("https://app.iponz.govt.nz/app/Extra/IP/TM/Qbe.aspx?sid=638362108144236733&op=EXTRA_tm_qbe&fcoOp=EXTRA__Default&directAccess=true"));
            HtmlPage searchResultsPage = homepage.searchByClassificationOrStatus(caseStatusArray);

            // Process the search results page or perform additional actions
            SearchResults searchResults = new SearchResults(client,searchResultsPage);

            searchResults.sortChronologically();

            System.out.println(searchResults.getCasePerPage());
            System.out.println(searchResults.getTotalCase());
            System.out.println(searchResults.getTotalPage());


            int totalResultPage = searchResults.getTotalPage();

            for(int i=1;i<=totalResultPage;i++){
                int currentPageTotalCase = searchResults.getTotalCaseOnCurrentPage();
                for(int j=1;j<=currentPageTotalCase;j++){

                    ApplicationDetails applicationDetails = new ApplicationDetails(client,searchResults.getCurrentPageCase(j),fileSavingService);
                    Decision decision = applicationDetails.getDecision();
                    String decisionReferenceDocument = applicationDetails.getReferenceDocument();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

                    String jsFilename = "decision_reference_"+decision.getBinder().getApplicationNo()+"_"+dateFormat.format(new Date())+ ".js";
                    String fileLocation = "C:/Users/U6074524/IdeaProjects/anmolPractice/src/week4/question3/output";

                    String referenceDocumentName = "decision_reference_document_"+decision.getBinder().getApplicationNo()+"_"+dateFormat.format(new Date());
                    fileSavingService.saveObjectToFile(decision,jsFilename,fileLocation);

                    if(decisionReferenceDocument!=null){
                        fileSavingService.downloadFile(decisionReferenceDocument,referenceDocumentName,fileLocation);
                    }

                    res.addDecision(decision);
                }

                if (i < totalResultPage) {
                    searchResults.nextPage(i);
                    Thread.sleep(15000);
                }
            }

            return res;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
