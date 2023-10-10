package week2.helperFunction;

public class OrderItem{
    public int quantity=0;
    public int productId = -1;
    public OrderItem() {
    }
    public OrderItem(int productId,int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
