package com.example.hellospringboot.service;

import com.example.hellospringboot.entity.Orders;

import java.util.List;
import java.util.Optional;

public interface OrdersService {
    List<Orders> getAllOrders();
    Optional<Orders> getOrderById(Long orderId);
    Orders saveOrder(Orders order);
    void deleteOrder(Long orderId);
}
