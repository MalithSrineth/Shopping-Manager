import java.util.List;

public interface DatabaseObserver {
    default void onProductsUpdated(List<Product> updatedProducts) {}
    default void onUsersUpdated(List<User> updatedUsers) {}

}