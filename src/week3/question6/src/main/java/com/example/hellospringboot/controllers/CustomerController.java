package com.example.hellospringboot.controllers;

import com.example.hellospringboot.entity.Customer;
import com.example.hellospringboot.service.jwttoken.JwtTokenGenerator;
import com.example.hellospringboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final JwtTokenGenerator jwttokengenerator;

    @Autowired
    public CustomerController(CustomerService customerService,JwtTokenGenerator jwttokengenerator) {
        this.customerService = customerService;
        this.jwttokengenerator = jwttokengenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer){
        if (customerService.existsByUsername(customer.getUsername())){
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
        Customer registeredCustomer = customerService.registerCustomer(customer);

        String token = jwttokengenerator.generateJwtToken(registeredCustomer.getId(), registeredCustomer.getRole());

        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> requestBody) {
        try {
            String username = (String) requestBody.get("username");
            String password = (String) requestBody.get("password");

            Customer loggedInCustomer = customerService.login(username, password);

             String token = jwttokengenerator.generateJwtToken(loggedInCustomer.getId(), loggedInCustomer.getRole());

            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Incorrect Username or Password", HttpStatus.UNAUTHORIZED);
        }
    }


    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestBody Customer customer, @RequestHeader HttpHeaders headers) {
        Long userId = jwttokengenerator.extractIdFromHeader(headers);

        Optional<Customer> optionalExistingCustomer = customerService.findById(userId);

        if (optionalExistingCustomer.isPresent()) {
            Customer existingCustomer = optionalExistingCustomer.get();

            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPassword(customer.getPassword());

            Customer updatedCustomer = customerService.updateProfile(existingCustomer);

            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            // Handle the case where the customer with the given ID is not found
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

}
