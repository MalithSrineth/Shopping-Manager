public interface ShoppingManager {
    void addProduct(Product product, int choice);
    void removeProduct(Product product);
    void emptyCart();
    void printProducts();
    void fileWriter();
    void fileReader();
}
