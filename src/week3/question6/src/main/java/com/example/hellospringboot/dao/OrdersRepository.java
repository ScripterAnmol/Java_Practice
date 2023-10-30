package com.example.hellospringboot.dao;

import com.example.hellospringboot.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface OrdersRepository {}

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}