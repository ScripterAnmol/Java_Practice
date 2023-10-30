package com.example.HelloSpringBoot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController{
    @GetMapping("/getGreetings")
    public String getGreetings(){
        return "Hi, I'm sending some greetings to make you day happy";
    }
}
