package com.example.question1;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@RestController
public class dataCollector {

    private final WebClient client;

    @Autowired
    public dataCollector(WebClient client){
        this.client=client;
    }
    @PostMapping("/scrapData")
    public ResponseEntity<?> scrapData(){
        try{
            List<MenuItem> menuObjects = new ArrayList<>();

            client.getOptions().setJavaScriptEnabled(false);
            HtmlPage homePage = client.getPage("https://clarivate.com");
            HtmlElement menuElement = homePage.getHtmlElementById("menu-main-menu");

            for(final DomElement childElements: menuElement.getChildElements()){
                MenuItem currentMenuItem = new MenuItem();

                /************************/
                HtmlElement navLinkElement = childElements.getElementsByTagName("a").get(0);
                String navLinkName = (navLinkElement != null) ? navLinkElement.getTextContent().trim() : "";

                currentMenuItem.setTitle(navLinkName);


                /************************/

                List<HtmlElement> nestedDivs = childElements.getElementsByTagName("div");

                if (!nestedDivs.isEmpty()) {
                    HtmlElement nestedDiv = nestedDivs.get(0);

                    if (nestedDiv != null) {
                        List<SubnavItem> subnavItemsList = new ArrayList<>();

                        // Access subnav-col elements
                        Iterable<HtmlElement> subnavCols = nestedDiv.getElementsByTagName("div");

                        for (HtmlElement subnavCol : subnavCols) {
                            // Check if the div has the class "subnav-col"
                            if (subnavCol.getAttribute("class").contains("subnav-col")) {
                                // Access other properties or perform actions with subnavCol
                                HtmlElement productCategoryTitleElement = subnavCol.getFirstByXPath(".//p[contains(@class, 'product-category-title')]");
                                String productCategoryTitle = (productCategoryTitleElement != null) ? productCategoryTitleElement.getTextContent().trim() : "";

                                HtmlElement productCategorySubnavDescElement = subnavCol.getFirstByXPath(".//p[contains(@class, 'subnav-desc')]");
                                String productCategorySubnavDesc = (productCategorySubnavDescElement != null) ? productCategorySubnavDescElement.getTextContent().trim() : "";
                                List<String> subnavItemsTextList = new ArrayList<>();

                                List<HtmlElement> subnavItemsDivs = subnavCol.getByXPath(".//div[contains(@class, 'subnav-items')]");

                                for (HtmlElement subnavItemsDiv : subnavItemsDivs) {
                                    // Access other properties or perform actions with subnavItemsDiv
                                    String subnavItemsText = subnavItemsDiv.getTextContent().trim();
                                    subnavItemsTextList.add(subnavItemsText);
                                }

                                SubnavItem subnavItem = new SubnavItem(productCategoryTitle, productCategorySubnavDesc, subnavItemsTextList);
                                subnavItemsList.add(subnavItem);
                            }
                        }

                        currentMenuItem.setSubnavItemsList(subnavItemsList);
                    }


                }

                menuObjects.add(currentMenuItem);
            }

            System.out.println("testing");
            return new ResponseEntity<>(menuObjects, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Oops!!! Some Error Occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
