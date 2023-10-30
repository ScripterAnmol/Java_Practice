package com.example.hellospringboot.controllers;

import com.example.hellospringboot.service.jwttoken.JwtTokenGenerator;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/jwttoken")
public class JWTTokenController{

    private final JwtTokenGenerator jwttokengenerator;

    @Autowired
    public JWTTokenController(JwtTokenGenerator jwttokengenerator) {
        this.jwttokengenerator = jwttokengenerator;
    }

    @PostMapping
    @ResponseBody
    public String generateToken(@RequestBody Map<String, Object> requestBody) {
        String username = (String) requestBody.get("username");
        String role = (String) requestBody.get("role");

        String jwtToken = jwttokengenerator.generateJwtToken(username, role);

        System.out.println("Generated JWT Token: " + jwtToken);

        return jwtToken;
    }

    @PostMapping("/decode")
    @ResponseBody
    public Claims decodeJWT(@RequestBody Map<String, Object> requestBody) {
        String accesstoken = (String) requestBody.get("accesstoken");

        Claims jwtToken = jwttokengenerator.decodeJWT(accesstoken);

        System.out.println("Generated JWT Token: " + jwtToken);

        return jwtToken;
    }

}
