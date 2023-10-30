package com.example.hellospringboot.service;

import com.example.hellospringboot.entity.Customer;

import java.util.Optional;

public interface CustomerService {
    Customer registerCustomer(Customer customer);
    Customer login(String username, String password);
    Customer updateProfile(Customer customer);

    boolean existsByUsername(String username);

    Optional<Customer> findById(Long Id);
}