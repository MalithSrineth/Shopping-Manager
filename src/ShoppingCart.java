import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> products;
    private double total;

    public ShoppingCart() {
        this.products = new LinkedHashMap<>();
    }

    public ShoppingCart(Map<Product, Integer> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }

        setTotal();
    }

    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            int quantity = products.get(product);
            if (quantity > 1) {
                products.put(product, quantity - 1);
            } else {
                products.remove(product);
            }
        }
    }

    public void emptyCart() {
        products.clear();
    }

    // public double calculateTotal() {
    //     double total = 0;
    //     for (Product product : products.keySet()) {
    //         total += product.getProductPrice() * products.get(product);
    //     }
    //     return total;
    // }

    public Map<Product, Integer> getProducts() {
        return products;
    }
    
    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal() {
        double total = 0;
        for (Product product : products.keySet()) {
            total += product.getProductPrice() * products.get(product);
        }
        this.total = total;
    }

}
