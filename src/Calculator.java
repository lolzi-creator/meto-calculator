import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    // History storage
    private static final ArrayList<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Calculator!");
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\nChoose an operation: add, subtract, multiply, divide, history, or exit");
            String operation = scanner.nextLine().toLowerCase();

            switch (operation.toLowerCase()) { // Convert operation to lowercase
                case "exit" -> {
                    System.out.println("Exiting the calculator. Goodbye!");
                    exportHistoryToFile(); // Save history to a file
                    keepRunning = false;
                }
                case "history" -> displayHistory();
                case "add", "subtract", "multiply", "divide" -> {
                    System.out.print("Enter the first number: ");
                    double num1 = getNumber(scanner);

                    System.out.print("Enter the second number: ");
                    double num2 = getNumber(scanner);

                    try {
                        double result = switch (operation.toLowerCase()) { // Normalize input
                            case "add" -> num1 + num2;
                            case "subtract" -> num1 - num2;
                            case "multiply" -> num1 * num2;
                            case "divide" -> {
                                if (num2 == 0) {
                                    throw new ArithmeticException("Cannot divide by zero!");
                                }
                                yield num1 / num2;
                            }
                            default -> throw new IllegalArgumentException("Invalid operation!");
                        };

                        System.out.println("The result is: " + result);
                        logHistory(num1, num2, operation, result);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                default -> System.out.println("Invalid operation!");
            }
        }

        scanner.close();
    }

    // Helper to get a valid number from the user
    private static double getNumber(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    // Log a calculation to the history
    private static void logHistory(double num1, double num2, String operation, double result) {
        String record = String.format("%.2f %s %.2f = %.2f", num1, operation, num2, result);
        history.add(record);
    }

    // Display the calculation history
    private static void displayHistory() {
        if (history.isEmpty()) {
            System.out.println("No calculations have been made yet.");
        } else {
            System.out.println("Calculation History:");
            for (String record : history) {
                System.out.println(record);
            }
        }
    }

    // Export the history to a file
    private static void exportHistoryToFile() {
        if (history.isEmpty()) {
            System.out.println("No calculations to save.");
            return;
        }

        try (FileWriter writer = new FileWriter("calculation_history.txt")) {
            for (String record : history) {
                writer.write(record + "\n");
            }
            System.out.println("Calculation history saved to calculation_history.txt.");
        } catch (IOException e) {
            System.out.println("Error saving history: " + e.getMessage());
        }
    }
}
