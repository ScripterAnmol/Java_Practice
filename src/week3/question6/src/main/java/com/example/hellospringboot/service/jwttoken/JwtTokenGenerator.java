package com.example.hellospringboot.service.jwttoken;

import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;

import java.util.Map;

public interface JwtTokenGenerator{
    String generateJwtToken(String username,String role);

    String generateJwtToken(Long id,String role);

    Claims decodeJWT(String jwt);

    Long extractIdFromHeader(HttpHeaders headers);

    String extractRoleFromHeader(HttpHeaders headers);
}
