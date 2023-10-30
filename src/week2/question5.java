package week2;

import week2.helperFunction.*;

import java.util.Scanner;

public class question5{
    public static void main(String[] args){
        ProductCatelogue availableProducts = new ProductCatelogue();
        availableProducts.addProduct(new Product(1,"Product 1",699));
        availableProducts.addProduct(new Product(2,"Product 2",399));
        availableProducts.addProduct(new Product(3,"Product 3",899));
        availableProducts.addProduct(new Product(4,"Product 4",1199));
        availableProducts.addProduct(new Product(4,"Product 4",1199));
        availableProducts.addProduct(new Product(5,"Product 5",1499));

//        TODO: change array to List
        Order[] placedOrders = new Order[0];

        ShoppingCart cart = new ShoppingCart();

        int continueShopping = 1;

        Scanner input = new Scanner(System.in);

        while(continueShopping==1){
            availableProducts.displayCatalogue();

            System.out.println("Input Total Number of Selected Products: ");
            int numberOfProducts = input.nextInt();

            for(int j=0;j<numberOfProducts;j++){
                OrderItem temp = new OrderItem();
                System.out.print("Input Product ID of Selected Product: ");
                temp.productId = input.nextInt();
                System.out.print("Input Purchase Quantity of Selected Product: ");
                temp.quantity = input.nextInt();

                cart.addItem(temp);
            }

            cart.displayOrderItems();
//      Todo: Split it in functions
            if(cart.customerDetails.custName.isEmpty() || cart.customerDetails.custEmail.isEmpty()){
                Customer cust = new Customer();
                input.nextLine();
                System.out.print("Enter Customer Name: ");
                cust.custName = input.nextLine();
                System.out.print("Enter Customer Email: ");
                cust.custEmail = input.nextLine();

                cart.addCustomerDetails(cust);
            }else{
                cart.displayCustomerDetails();

                System.out.print("Would You Like to Continue with same Customer Details? If no press 0 else please 1: ");
                if(input.nextInt()==0){
                    Customer cust = new Customer();
                    System.out.print("Enter Customer Name: ");
                    cust.custName = input.nextLine();
                    System.out.print("Enter Customer Email: ");
                    cust.custEmail = input.nextLine();

                    cart.addCustomerDetails(cust);
                }
            }

            System.out.println("Available Product Details");
            double cartValue = 0;
            for (OrderItem item : cart.orderItems) {
                double perUnitCost = availableProducts.getPerUnitCost(item.productId);
                double itemValue = 0;
                itemValue = item.quantity*perUnitCost;
                cartValue += itemValue;

                System.out.println("Product ID: " + item.productId + ", "+item.quantity+"units * "+ perUnitCost + " = " + itemValue);
                System.out.println("--------------");
            }

//            Bill Details
            System.out.println("Total Bill Amount: "+cartValue);
            System.out.println("--------------------------------------------------------");

            Order newOrder = new Order(cart.orderItems, cart.customerDetails);
            Order[] newArray = new Order[placedOrders.length + 1];
            System.arraycopy(placedOrders, 0, newArray, 0, placedOrders.length);
            newArray[placedOrders.length] = newOrder;
            placedOrders = newArray;

            cart.clearOrderItems();

            System.out.print("Would You Like to Continue Shopping? If no press 0 else please 1: ");
            continueShopping = input.nextInt();
            System.out.println("You entered: " + continueShopping);
        }

        System.out.println("--------------");
        System.out.println("All Placed Orders:");
        for (Order order : placedOrders) {
            System.out.println("Order details:");
            for (OrderItem item : order.orderDetails) {
                System.out.println("Product ID: " + item.productId);
                System.out.println("Quantity: " + item.quantity);
                // Add more details if needed
                System.out.println("--------------");
            }
            // Display customer details
            System.out.println("Customer details:");
            System.out.println("Name: " + order.customerDetails.custName);
            System.out.println("Email: " + order.customerDetails.custEmail);
            System.out.println("--------------");
            // Add more details if needed
            System.out.println("--------------------------------------------------------");
        }
    }
}
