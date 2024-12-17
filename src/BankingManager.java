import java.util.ArrayList;
import java.util.Scanner;

public class BankingManager {
    private final ArrayList<Account> accounts = new ArrayList<>();
    private int nextId = 1; // Auto-increment ID for accounts

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("\nWelcome to the Banking System!");

        while (keepRunning) {
            System.out.println("\n--- Banking Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transfer Money");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> createAccount(scanner);
                case "2" -> viewAccounts();
                case "3" -> depositMoney(scanner);
                case "4" -> withdrawMoney(scanner);
                case "5" -> transferMoney(scanner);
                case "6" -> viewTransactionHistory(scanner);
                case "7" -> {
                    System.out.println("Exiting Banking System. Goodbye!");
                    keepRunning = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Create a new account
    private void createAccount(Scanner scanner) {
        System.out.print("Enter account holder name: ");
        String holderName = scanner.nextLine();

        System.out.print("Enter initial balance: ");
        double initialBalance = getValidDouble(scanner);

        Account account = new Account(nextId++, holderName, initialBalance);
        accounts.add(account);
        System.out.println("Account created successfully!");
    }

    // View all accounts
    private void viewAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            System.out.println("\n--- List of Accounts ---");
            for (Account account : accounts) {
                System.out.println(account);
            }
        }
    }

    // Deposit money into an account
    private void depositMoney(Scanner scanner) {
        Account account = findAccount(scanner);
        if (account == null) return;

        System.out.print("Enter deposit amount: ");
        double amount = getValidDouble(scanner);

        account.deposit(amount);
        System.out.println("Deposit successful!");
    }

    // Withdraw money from an account
    private void withdrawMoney(Scanner scanner) {
        Account account = findAccount(scanner);
        if (account == null) return;

        System.out.print("Enter withdrawal amount: ");
        double amount = getValidDouble(scanner);

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    // Transfer money between accounts
    private void transferMoney(Scanner scanner) {
        System.out.print("Enter the ID of the account to transfer FROM: ");
        Account fromAccount = findAccount(scanner);
        if (fromAccount == null) return;

        System.out.print("Enter the ID of the account to transfer TO: ");
        Account toAccount = findAccount(scanner);
        if (toAccount == null) return;

        System.out.print("Enter the amount to transfer: ");
        double amount = getValidDouble(scanner);

        if (fromAccount.withdraw(amount)) {
            toAccount.deposit(amount);
            fromAccount.addTransaction("Transferred: $" + amount + " to Account ID: " + toAccount.getId());
            toAccount.addTransaction("Received: $" + amount + " from Account ID: " + fromAccount.getId());
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Insufficient balance for the transfer!");
        }
    }

    // View transaction history for an account
    private void viewTransactionHistory(Scanner scanner) {
        Account account = findAccount(scanner);
        if (account == null) return;

        System.out.println("\n--- Transaction History ---");
        for (String transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    // Find an account by ID
    private Account findAccount(Scanner scanner) {
        System.out.print("Enter the account ID: ");
        int id = getValidInt(scanner);

        for (Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }

        System.out.println("No account found with the given ID.");
        return null;
    }

    // Get valid integer input
    private int getValidInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
    }

    // Get valid double input
    private double getValidDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}
