import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private Map<String, User> users;
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        // Initialize a simulated database of users
        users = new HashMap<>();
        users.put("user123", new User("user123", "1234", 1000.00));
        users.put("user456", new User("user456", "5678", 500.00));
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("--- Welcome to the ATM ---");
        login();
    }

    private void login() {
        while (true) {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            User user = users.get(userId);
            if (user != null && user.validatePin(pin)) {
                currentUser = user;
                System.out.println("\nLogin successful! Welcome " + currentUser.getUserId() + ".");
                showMenu();
                break;
            } else {
                System.out.println("Invalid User ID or PIN. Please try again.");
            }
        }
    }

    private void showMenu() {
        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close(); // Close the scanner when quitting
                    return; // Exit the menu loop
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        for (Transaction t : currentUser.getAccount().getTransactionHistory()) {
            System.out.println(t);
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        if (currentUser.getAccount().withdraw(amount)) {
            currentUser.getAccount().addTransaction(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal successful. New balance: $" + String.format("%.2f", currentUser.getAccount().getBalance()));
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        currentUser.getAccount().deposit(amount);
        currentUser.getAccount().addTransaction(new Transaction("Deposit", amount));
        System.out.println("Deposit successful. New balance: $" + String.format("%.2f", currentUser.getAccount().getBalance()));
    }

    private void transfer() {
        System.out.print("Enter recipient User ID: ");
        String recipientId = scanner.nextLine();
        User recipient = users.get(recipientId);

        if (recipient == null) {
            System.out.println("Recipient user not found.");
            return;
        }

        if (recipientId.equals(currentUser.getUserId())) {
            System.out.println("You cannot transfer money to yourself.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (currentUser.getAccount().withdraw(amount)) {
            recipient.getAccount().deposit(amount);
            currentUser.getAccount().addTransaction(new Transaction("Transfer to " + recipientId, amount));
            recipient.getAccount().addTransaction(new Transaction("Transfer from " + currentUser.getUserId(), amount));
            System.out.println("Transfer successful. New balance: $" + String.format("%.2f", currentUser.getAccount().getBalance()));
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}