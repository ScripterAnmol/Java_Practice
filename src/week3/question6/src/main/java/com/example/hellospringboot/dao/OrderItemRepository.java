package com.example.hellospringboot.dao;

import com.example.hellospringboot.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface OrderItemRepository{}
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
