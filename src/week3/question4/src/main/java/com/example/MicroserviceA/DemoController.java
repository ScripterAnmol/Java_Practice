package com.example.MicroserviceA;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    @Value("${spring.datasource.name}")
    private String name;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @GetMapping("/test")
    public String testFunction(){
        System.out.println(name);
        System.out.println(url);
        System.out.println(username);
        return "This is a testing Controller";
    }
}