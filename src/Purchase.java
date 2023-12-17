import java.time.LocalDate;
import java.time.LocalTime;

public class Purchase {
    private static int lastPurchaseID = 0;
    private int purchaseID;
    private LoggingSession loggingSession;
    private LocalDate date;
    private LocalTime time;


    public Purchase(LoggingSession loggingSession) {
        setPurchaseID();
        this.purchaseID = getPurchaseID();
        this.loggingSession = loggingSession;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public static int getLastPurchaseID() {
        return lastPurchaseID;
    }

    public static void setLastPurchaseID(int lastPurchaseID) {
        Purchase.lastPurchaseID = lastPurchaseID;
    }

    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID() {
        getLastPurchaseID();
        this.purchaseID = (getLastPurchaseID() + 1);
        setLastPurchaseID (this.purchaseID);
    }

    public LoggingSession getLoggingSession() {
        return loggingSession;
    }

    public void setLoggingSession(LoggingSession loggingSession) {
        this.loggingSession = loggingSession;
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
