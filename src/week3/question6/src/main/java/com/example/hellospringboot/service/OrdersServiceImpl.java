package com.example.hellospringboot.service;

import com.example.hellospringboot.dao.OrdersRepository;
import com.example.hellospringboot.entity.Orders;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//public class OrdersServiceImpl {}

@Service
public class OrdersServiceImpl implements OrdersService{
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<Orders> getAllOrders(){
        return ordersRepository.findAll();
    }

    @Override
    public Optional<Orders> getOrderById(Long orderId){
        return ordersRepository.findById(orderId);
    }

    @Override
    public Orders saveOrder(Orders order){
        return ordersRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        ordersRepository.deleteById(orderId);
    }
}