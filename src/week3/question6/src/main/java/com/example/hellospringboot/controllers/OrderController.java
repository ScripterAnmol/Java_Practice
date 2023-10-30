package com.example.hellospringboot.controllers;

import com.example.hellospringboot.entity.OrderItem;
import com.example.hellospringboot.entity.Orders;
import com.example.hellospringboot.service.OrderItemService;
import com.example.hellospringboot.service.OrdersService;
import com.example.hellospringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController{
    private final OrdersService ordersService;
    private final OrderItemService orderItemService;

    private final ProductService productService;

    @Autowired
    public OrderController(OrdersService ordersService, OrderItemService orderItemService,ProductService productService) {
        this.ordersService = ordersService;
        this.orderItemService = orderItemService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Map<String, Object> requestBody) {
        try {
            String custName = (String) requestBody.get("custName");
            String custPhNo = (String) requestBody.get("custPhNo");

            Orders order = new Orders();
            order.setCustName(custName);
            order.setCustPhNo(custPhNo);

            List<Map<String, Object>> orderItemsList = (List<Map<String, Object>>) requestBody.get("orderItems");

            if (orderItemsList != null) {
                List<OrderItem> orderItems = new ArrayList<>();

                for (Map<String, Object> item : orderItemsList) {
                    Long productId = Long.parseLong(item.get("productId").toString());
                    Long quantity = Long.parseLong(item.get("quantity").toString());

                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(productService.getProductById(productId));
                    orderItem.setQuantity(quantity);
                    orderItem.setOrder(order);

                    orderItems.add(orderItem);
                }

                order.setOrderItem(orderItems);
            }

            Orders placedOrder = ordersService.saveOrder(order);

            return new ResponseEntity<>("Order Successfully! Order Id: "+placedOrder.getOrderId(), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error placing order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping
    public ResponseEntity<?> getOrders() {
        try {
            List<Orders> allOrders = ordersService.getAllOrders();
            List<Map<String, Object>> responseList = new ArrayList<>();

            for (Orders order : allOrders) {
                Map<String, Object> orderDetails = new HashMap<>();
                orderDetails.put("orderId", order.getOrderId());
                orderDetails.put("custName", order.getCustName());
                orderDetails.put("custPhNo", order.getCustPhNo());

                List<Map<String, Object>> orderItemsList = new ArrayList<>();
                List<OrderItem> orderItems = order.getOrderItem();

                if (orderItems != null && !orderItems.isEmpty()) {
                    for (OrderItem orderItem : orderItems) {
                        Map<String, Object> orderItemDetails = new HashMap<>();
                        orderItemDetails.put("productId", orderItem.getProduct().getProductId());
                        orderItemDetails.put("productPrice", orderItem.getProduct().getPrice());
                        orderItemDetails.put("quantity", orderItem.getQuantity());
                        orderItemsList.add(orderItemDetails);
                    }
                }

                orderDetails.put("orderItems", orderItemsList);
                responseList.add(orderDetails);
            }

            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving orders: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        try {
            Optional<Orders> orderOptional = ordersService.getOrderById(orderId);

            if (orderOptional.isPresent()) {
                Orders order = orderOptional.get();
                Map<String, Object> orderDetails = new HashMap<>();
                orderDetails.put("orderId", order.getOrderId());
                orderDetails.put("custName", order.getCustName());
                orderDetails.put("custPhNo", order.getCustPhNo());

                List<Map<String, Object>> orderItemsList = new ArrayList<>();
                List<OrderItem> orderItems = order.getOrderItem();

                if (orderItems != null && !orderItems.isEmpty()) {
                    for (OrderItem orderItem : orderItems) {
                        Map<String, Object> orderItemDetails = new HashMap<>();
                        orderItemDetails.put("productId", orderItem.getProduct().getProductId());
                        orderItemDetails.put("productPrice", orderItem.getProduct().getPrice());
                        orderItemDetails.put("quantity", orderItem.getQuantity());
                        orderItemsList.add(orderItemDetails);
                    }
                }

                orderDetails.put("orderItems", orderItemsList);
                return new ResponseEntity<>(orderDetails, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Order not found with ID: " + orderId, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody Orders updatedOrder) {
        try {
            Optional<Orders> existingOrderOptional = ordersService.getOrderById(orderId);

            if (existingOrderOptional.isPresent()) {
                Orders existingOrder = existingOrderOptional.get();

                existingOrder.setCustName(updatedOrder.getCustName());
                existingOrder.setCustPhNo(updatedOrder.getCustPhNo());

                Orders updatedOrderResult = ordersService.saveOrder(existingOrder);
                return new ResponseEntity<>("Order updated successfully! Order Id: " + updatedOrderResult.getOrderId(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Order not found with ID: " + orderId, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        try {
            Optional<Orders> existingOrderOptional = ordersService.getOrderById(orderId);

            if (existingOrderOptional.isPresent()) {
                ordersService.deleteOrder(orderId);
                return new ResponseEntity<>("Order deleted successfully! Order Id: " + orderId, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Order not found with ID: " + orderId, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
