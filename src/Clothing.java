public class Clothing extends Product{
    private String size;
    private String color;

    public Clothing(String productID, String productName, double productPrice, int productQuantity, String size, String color) {
        super(productID, productName, productPrice, productQuantity);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    
}
