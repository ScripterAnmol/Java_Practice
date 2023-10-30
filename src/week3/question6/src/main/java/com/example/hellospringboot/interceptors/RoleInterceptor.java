package com.example.hellospringboot.interceptors;

import com.example.hellospringboot.service.jwttoken.JwtTokenGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    private final JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    public RoleInterceptor(JwtTokenGenerator jwtTokenGenerator) {
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            HttpHeaders headers = convertHeaders(request);
            String role = jwtTokenGenerator.extractRoleFromHeader(headers);

            // Check if the role is admin
            if ("admin".equals(role)) {
                return true;
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied. User is not an admin.");
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());

            return false;
        }
    }

    private HttpHeaders convertHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> headerValues = request.getHeaders(headerName);
            List<String> values = Collections.list(headerValues);
            headers.put(headerName, values);
        }
        return headers;
    }
}