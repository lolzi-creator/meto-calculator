import java.util.Scanner;

public class Calculator {
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("\nWelcome to the Calculator!");

        while (keepRunning) {
            System.out.println("\nChoose an operation: add, subtract, multiply, divide, or exit");
            String operation = scanner.nextLine().toLowerCase();

            if (operation.equals("exit")) {
                System.out.println("Exiting the calculator.");
                break;
            }

            System.out.print("Enter the first number: ");
            double num1 = getNumber(scanner);

            System.out.print("Enter the second number: ");
            double num2 = getNumber(scanner);

            try {
                double result = switch (operation) {
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
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static double getNumber(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}
