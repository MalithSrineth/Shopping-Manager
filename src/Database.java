import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Database {
    // Making the arrays private to encapsulate the data
    private List<User> users;
    private List<Product> products;
    private List<ShoppingCart> shoppingCarts;
    private List<Purchase> purchases;
    private List<LoggingSession> loggingSessions;

    // Singleton instance
    private static Database instance;

    // Private constructor to prevent external instantiation
    private Database() {
        users = new ArrayList<>();
        products = new ArrayList<>();
        shoppingCarts = new ArrayList<>();
        purchases = new ArrayList<>();
        loggingSessions = new ArrayList<>();
    }

    // Public method to get the instance of the Database
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // Methods to access and modify the users list
    public synchronized void addUser(User user) {
        users.add(user);
    }

    public synchronized User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public synchronized void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())) {
                users.set(i, user);
                break;
            }
        }
    }

    public synchronized List<User> getUsers() {
        return new ArrayList<>(users); // Return a copy for encapsulation
    }

    public synchronized void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.ser"))) {
            oos.writeObject(users);
            System.out.println("Users saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving Users to file: " + e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    public synchronized void loadUsers() {
        // Load the users ArrayList from a file using serialization
        File file = new File("users.ser");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                users = (ArrayList<User>) ois.readObject();
                System.out.println("Users loaded from file successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading users from file: " + e.getMessage());
            }
        }
    }

    // Similarly, add methods for products, shoppingCarts, purchases, loggingSessions...
    // Example for products:
    public synchronized void addProduct(Product product) {
        products.add(product);
    }

    public synchronized List<Product> getProducts() {
        return new ArrayList<>(products); // Return a copy for encapsulation
    }

    // ... and so on for other lists

    // Consider adding methods to remove or update items as needed
}