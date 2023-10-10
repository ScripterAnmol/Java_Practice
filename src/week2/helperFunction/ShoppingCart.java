package week2.helperFunction;

public class ShoppingCart{
    public OrderItem[] orderItems = new OrderItem[0];
    public Customer customerDetails = new Customer();

    public void addItem(OrderItem newItem) {
        OrderItem[] newArray = new OrderItem[orderItems.length + 1];

        System.arraycopy(orderItems, 0, newArray, 0, orderItems.length);

        newArray[orderItems.length] = newItem;

        orderItems = newArray;
    }

    public void clearOrderItems() {
        orderItems = new OrderItem[0];
    }
    public void addCustomerDetails(Customer newCustomer) {
        customerDetails = newCustomer;
    }

    public void displayCustomerDetails() {
        System.out.println("Available Customer Details");
        System.out.println("Product Name: " + customerDetails.custName);
        System.out.println("Product Email: " + customerDetails.custEmail);
        System.out.println("--------------");
    }

    public void displayOrderItems(){
        System.out.println("--------------");
        System.out.println("Added Items to Cart");
        for (OrderItem item : orderItems){
            System.out.println("Product ID: " + item.productId+ " Product Quantity: " + item.quantity);
        }
    }
}
