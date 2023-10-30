package com.example.hellospringboot.service;

import com.example.hellospringboot.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService{
    List<OrderItem> getAllOrderItems();

    Optional<OrderItem> getOrderItemById(Long orderItemId);

    OrderItem saveOrderItem(OrderItem orderItem);

    void deleteOrderItem(Long orderItemId);
}
