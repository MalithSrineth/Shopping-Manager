import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.io.Serializable;

public class LoggingSession implements Serializable{
    private static int lastSessionID = 0;
    private int sessionID;
    private User user;
    private ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();
    private LocalDate date;
    private LocalTime time;

    public LoggingSession(User user) {
        setSessionID();
        this.sessionID = getSessionID();
        this.user = user;
        this.shoppingCarts.add(new ShoppingCart(user.getShoppingCart()));
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public LoggingSession(LoggingSession other) {
        this.user = new User(other.user); 
        this.shoppingCarts = new ArrayList<>(other.shoppingCarts); 
        this.date = other.date;
        this.time = other.time;
    }


    public static int getLastSessionID() {
        return lastSessionID;
    }

    public static void setLastSessionID(int lastSessionID) {
        LoggingSession.lastSessionID = lastSessionID;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID() {
        getLastSessionID();
        this.sessionID = (getLastSessionID() + 1);
        setLastSessionID (this.sessionID);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShoppingCart getShoppingCart() {       
        return shoppingCarts.get(shoppingCarts.size() - 1);
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCarts.isEmpty()) {
            shoppingCarts.add(shoppingCart);
        } else {
            shoppingCarts.add(shoppingCarts.size(), shoppingCart);
        }
    }

    public ArrayList<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
}
