import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String type;
    private double amount;
    private String timestamp;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Amount: $" + String.format("%.2f", amount) + ", Time: " + timestamp;
    }
}