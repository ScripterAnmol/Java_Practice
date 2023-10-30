package com.example.hellospringboot.entity;

import jakarta.persistence.*;

@Entity
public class OrderItem{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    public OrderItem(){}

    public OrderItem(Long orderItemId, Product product, Long quantity, Orders order) {
        this.orderItemId = orderItemId;
        this.product = product;
        this.quantity = quantity;
        this.order = order;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}