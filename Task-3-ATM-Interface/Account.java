import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        // Add an initial transaction for the starting balance
        addTransaction(new Transaction("Initial Deposit", initialBalance));
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }
}