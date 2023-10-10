package week2.helperFunction;

public class Product {
    public int productId;
    public String productName = "";
    public double productValue = 0;

    public Product(int productId,String productName,double productValue) {
        this.productId = productId;
        this.productName = productName;
        this.productValue = productValue;
    }
}