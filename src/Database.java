import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private List<DatabaseObserver> observers = new ArrayList<>();

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

    public void registerObserver(DatabaseObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void unregisterObserver(DatabaseObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (DatabaseObserver observer : observers) {
            observer.onProductsUpdated(new ArrayList<>(products));
            observer.onUsersUpdated(new ArrayList<>(users));
        }
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
        notifyObservers();
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

    public synchronized void addProduct(Product product) {
        products.add(product);
        notifyObservers();
    }

    public synchronized void removeProduct(Product product) {
        products.remove(product);
        notifyObservers();
    }


    public synchronized void setProducts(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public synchronized List<Product> getProducts() {
        return new ArrayList<>(products); // Return a copy for encapsulation
    }

    public synchronized Product getProduct(String productID) {
        for (Product product : products) {
            if (product.getProductID().equals(productID)) {
                return product;
            }
        }
        return null;
    }
    
    public synchronized void saveToFile(String type) {
        String filename;
        switch (type) {
            case "users":
                filename = "users.ser";
                break;
            case "products":
                filename = "products.ser";
                break;
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            switch (type) {
                case "users":
                    oos.writeObject(users);
                    break;
                case "products":
                    oos.writeObject(products);
                    break;
            }
            System.out.println(type + " saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving " + type + " to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public synchronized void loadFromFile(String type) throws IllegalArgumentException {
        File file;
        switch (type) {
            case "users":
                file = new File("users.ser");
                break;
            case "products":
                file = new File("products.ser");
                break;
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
        
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                switch (type) {
                    case "users":
                        users = (List<User>) ois.readObject();
                        break;
                    case "products":
                        products = (List<Product>) ois.readObject();
                        break;
                }
                System.out.println(type + " loaded from file successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading " + type + " from file: " + e.getMessage());
            }
        }
    }
}