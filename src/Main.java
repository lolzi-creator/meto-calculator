import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\n--- Project Menu ---");
            System.out.println("1. Calculator");
            System.out.println("2. Calculator with GUI");
            System.out.println("3. Banking Management Simulator");
            System.out.println("4. Library Management System");
            System.out.println("5. Task Management System");
            System.out.println("6. Expense Management System");
            System.out.println("7. Logistics Management System");
            System.out.println("8. Student Management System");
            System.out.println("9. Inventory Management System");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> Calculator.run();
                case "2" -> Calculator.run();
                case "3" -> new BankingManager().run();
                case "4" -> new LibraryManager().run();
                case "5" -> new TaskManager().run();
                case "6" -> new ExpenseTracker().run();
                case "7" -> new LogisticsManager().run();
                case "8" -> new StudentManager().run();
                case "9" -> new InventoryManager().run();
                case "10" -> {
                    System.out.println("Exiting the program. Goodbye!");
                    keepRunning = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
