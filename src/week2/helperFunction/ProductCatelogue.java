package week2.helperFunction;

public class ProductCatelogue{
    public Product[] productDetails = new Product[0];
    public void addProduct(Product newProduct) {
        Product[] newArray = new Product[productDetails.length + 1];

        System.arraycopy(productDetails, 0, newArray, 0, productDetails.length);

        newArray[productDetails.length] = newProduct;

        productDetails = newArray;
    }

    public void displayCatalogue() {
        System.out.println("Available Product Details");
        for (Product product : productDetails) {
            System.out.println("Product ID: " + product.productId);
            System.out.println("Product Name: " + product.productName);
            System.out.println("Product Value: " + product.productValue);
            System.out.println("--------------");
        }
    }

    public double getPerUnitCost(int productId) {
        for (Product product : productDetails) {
            if(product.productId==productId){
                return product.productValue;
            }
        }

        return 0;
    }
}
