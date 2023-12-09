import java.io.IOException;

public interface ShoppingManager {
    void addProduct(Product product, int choice);
    void removeProduct(Product product);
    void emptyCart();
    void printProducts();
    void fileWriter() throws IOException;
    void fileReader();
}
