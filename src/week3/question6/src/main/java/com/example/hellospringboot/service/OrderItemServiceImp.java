package com.example.hellospringboot.service;

import com.example.hellospringboot.dao.OrderItemRepository;
import com.example.hellospringboot.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//public class OrderItemServiceImp{}

@Service
public class OrderItemServiceImp implements OrderItemService{
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImp(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItem> getAllOrderItems(){
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> getOrderItemById(Long orderItemId){
        return orderItemRepository.findById(orderItemId);
    }

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

}
