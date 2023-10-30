package com.example.hellospringboot.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

//public class Orders {}

@Entity
public class Orders{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private String custName;

    private String custPhNo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItem;

    public Orders(){
    }

    public Orders(Long orderId, String custName, String custPhNo, List<OrderItem> orderItem) {
        this.orderId = orderId;
        this.custName = custName;
        this.custPhNo = custPhNo;
        this.orderItem = orderItem;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhNo() {
        return custPhNo;
    }

    public void setCustPhNo(String custPhNo) {
        this.custPhNo = custPhNo;
    }
}