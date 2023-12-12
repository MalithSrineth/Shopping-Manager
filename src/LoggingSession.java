import java.time.LocalDate;
import java.time.LocalTime;

public class LoggingSession {
    private static int lastSessionID = 0;
    private int sessionID;
    private User user;
    private ShoppingCart shoppingCart;
    private LocalDate date;
    private LocalTime time;

    public LoggingSession(User user) {
        setSessionID();
        this.sessionID = getSessionID();
        this.user = user;
        this.shoppingCart = user.getShoppingCart();
        this.date = LocalDate.now();
        this.time = LocalTime.now();
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
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
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
