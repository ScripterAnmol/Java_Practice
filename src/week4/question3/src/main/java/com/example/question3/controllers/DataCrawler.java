package com.example.question3.controllers;

import com.example.question3.dto.Decision;
import com.example.question3.dto.Decisions;
import com.example.question3.services.DataCrawlerService;
import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataCrawler {
    private final DataCrawlerService dataCrawlerService;

    @Autowired
    public DataCrawler(DataCrawlerService dataCrawlerService){
        this.dataCrawlerService=dataCrawlerService;
    }

    @PostMapping("/crawlOneData")
    public ResponseEntity<?> crawlOneData(){
        try {
            Decision res = dataCrawlerService.getFirstTrademarkCase();

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @PostMapping("/crawlData")
    public ResponseEntity<?> crawlData(){
        try {
            Decisions res = dataCrawlerService.getAllTrademarkCase();

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }
}
