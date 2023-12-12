import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }
    public void emptyCart() {
        products.clear();
    }
    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getProductPrice();
        }
        return total;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
