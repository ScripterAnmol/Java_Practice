package week2.helperFunction;

public class Order {
    public OrderItem[] orderDetails = new OrderItem[0];
    public Customer customerDetails = new Customer();

    public Order(OrderItem[] orderItems, Customer customerDetails) {
        this.orderDetails= orderItems;
        this.customerDetails = customerDetails;
    }
}
