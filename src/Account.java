import java.util.ArrayList;

public class Account {
    private int id;
    private String holderName;
    private double balance;
    private ArrayList<String> transactionHistory;

    // Constructor
    public Account(int id, String holderName, double initialBalance) {
        this.id = id;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Account created with initial balance: $" + initialBalance);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    // Deposit money
    public void deposit(double amount) {
        balance += amount;
        addTransaction("Deposited: $" + amount + " | New Balance: $" + balance);
    }

    // Withdraw money
    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false; // Insufficient balance
        }
        balance -= amount;
        addTransaction("Withdrew: $" + amount + " | New Balance: $" + balance);
        return true;
    }

    // Add a transaction to the history
    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Holder: %s | Balance: $%.2f", id, holderName, balance);
    }
}
