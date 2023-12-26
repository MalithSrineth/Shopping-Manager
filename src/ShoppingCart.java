import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCart implements Serializable{
    private Map<Product, Integer> products;
    private double total;
    private double discount;

    public ShoppingCart() {
        this.products = new LinkedHashMap<>();
        this.total = 0;
        this.discount = 0;
    }

    public ShoppingCart(ShoppingCart shoppingCart) {
        this.products = new LinkedHashMap<>(shoppingCart.products);
        this.total = shoppingCart.total;
        this.discount = shoppingCart.discount;
    }

    public void addProduct(Product product, LoggingSession loggingSession) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }

        setTotal();
        calculateDiscount(loggingSession);
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
        this.products.clear();
        setTotal();
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void calculateDiscount(LoggingSession loggingSession) {
        if (loggingSession.getShoppingCarts().size() == 1) {
            ShoppingCart shoppingCart = loggingSession.getShoppingCarts().get(0);
            double discount = shoppingCart.getTotal()*10/100; 
            shoppingCart.setDiscount(discount);
        }
    }

}
