import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {
    private final ArrayList<Expense> expenses = new ArrayList<>();
    private int nextId = 1; // Auto-increment ID for expenses

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("\nWelcome to the Expense Tracker!");

        while (keepRunning) {
            System.out.println("\n--- Expense Tracker Menu ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Total by Category");
            System.out.println("4. Export Expenses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addExpense(scanner);
                case "2" -> viewExpenses();
                case "3" -> viewTotalByCategory();
                case "4" -> exportExpenses();
                case "5" -> {
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    keepRunning = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Add a new expense
    private void addExpense(Scanner scanner) {
        System.out.print("Enter expense description: ");
        String description = scanner.nextLine();

        System.out.print("Enter expense category (e.g., Food, Travel, Utilities): ");
        String category = scanner.nextLine();

        System.out.print("Enter expense amount: ");
        double amount = getValidDouble(scanner);

        Expense expense = new Expense(nextId++, description, category, amount);
        expenses.add(expense);
        System.out.println("Expense added successfully!");
    }

    // View all expenses
    private void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            System.out.println("\n--- List of Expenses ---");
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }

    // View total expenses by category
    private void viewTotalByCategory() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.println("\n--- Total by Category ---");
        expenses.stream()
                .map(Expense::getCategory)
                .distinct()
                .forEach(category -> {
                    double total = expenses.stream()
                            .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                            .mapToDouble(Expense::getAmount)
                            .sum();
                    System.out.printf("Category: %s | Total: $%.2f%n", category, total);
                });
    }

    // Export expenses to a file
    private void exportExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to export.");
            return;
        }

        try (java.io.FileWriter writer = new java.io.FileWriter("expenses.txt")) {
            for (Expense expense : expenses) {
                writer.write(expense.toString() + "\n");
            }
            System.out.println("Expenses exported to expenses.txt.");
        } catch (java.io.IOException e) {
            System.out.println("Error exporting expenses: " + e.getMessage());
        }
    }

    // Helper: Get valid double input
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