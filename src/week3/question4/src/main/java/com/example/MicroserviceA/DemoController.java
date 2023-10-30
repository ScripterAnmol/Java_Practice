package com.example.MicroserviceA;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController{

    @GetMapping("/test")
    public String testFunction(){
        return "This is a testing Controller";
    }
}
