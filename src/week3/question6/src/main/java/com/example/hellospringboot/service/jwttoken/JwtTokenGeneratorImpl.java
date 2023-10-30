package com.example.hellospringboot.service.jwttoken;

import io.jsonwebtoken.*;

import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

@Service
public class JwtTokenGeneratorImpl implements JwtTokenGenerator{
    @Value("${jwt.secret}")
    private String secretKey;
    @Override
    public String generateJwtToken(String username,String role){
        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @Override
    public String generateJwtToken(Long id,String role){
        return Jwts.builder()
                .claim("id", id)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @Override
    public Claims decodeJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    @Override
    public Long extractIdFromHeader(HttpHeaders headers){
        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            Claims claims = decodeJWT(jwtToken);

            // Extract the id from the claims
            Number idNumber = (Number) claims.get("id");
            if (idNumber != null) {
                return idNumber.longValue();
            } else {
                throw new RuntimeException("ID not found in JWT claims");
            }
        }

        throw new RuntimeException("Invalid or missing Authorization header");
    }

    @Override
    public String extractRoleFromHeader(HttpHeaders headers){
        String authorizationHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            Claims claims = decodeJWT(jwtToken);

            // Extract the id from the claims
            String role = (String) claims.get("role");
            if (role != null) {
                return role;
            } else {
                throw new RuntimeException("Role not found in JWT claims");
            }
        }

        throw new RuntimeException("Invalid or missing Authorization header");
    }
}
