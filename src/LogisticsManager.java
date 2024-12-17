import java.util.ArrayList;
import java.util.Scanner;

public class LogisticsManager {
    private final ArrayList<Delivery> deliveries = new ArrayList<>();
    private int nextId = 1; // Auto-increment ID for deliveries

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("\nWelcome to the Logistics Management System!");

        while (keepRunning) {
            System.out.println("\n--- Logistics Management Menu ---");
            System.out.println("1. Add Delivery");
            System.out.println("2. View Deliveries");
            System.out.println("3. Update Delivery Status");
            System.out.println("4. Filter Deliveries by Status");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addDelivery(scanner);
                case "2" -> viewDeliveries();
                case "3" -> updateDeliveryStatus(scanner);
                case "4" -> filterDeliveriesByStatus(scanner);
                case "5" -> {
                    System.out.println("Exiting Logistics Management System. Goodbye!");
                    keepRunning = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Add a new delivery
    private void addDelivery(Scanner scanner) {
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter weight (kg): ");
        double weight = getValidDouble(scanner);

        System.out.print("Enter priority (High/Medium/Low): ");
        String priority = scanner.nextLine();

        Delivery delivery = new Delivery(nextId++, destination, weight, priority);
        deliveries.add(delivery);
        System.out.println("Delivery added successfully!");
    }

    // View all deliveries
    private void viewDeliveries() {
        if (deliveries.isEmpty()) {
            System.out.println("No deliveries recorded.");
        } else {
            System.out.println("\n--- List of Deliveries ---");
            for (Delivery delivery : deliveries) {
                System.out.println(delivery);
            }
        }
    }

    // Update the status of a delivery
    private void updateDeliveryStatus(Scanner scanner) {
        System.out.print("Enter the ID of the delivery to update: ");
        int id = getValidInt(scanner);

        Delivery delivery = findDeliveryById(id);
        if (delivery == null) {
            System.out.println("No delivery found with the given ID.");
            return;
        }

        System.out.print("Enter new status (Pending/In Transit/Delivered): ");
        String status = scanner.nextLine();
        delivery.setStatus(status);
        System.out.println("Delivery status updated successfully!");
    }

    // Filter deliveries by status
    private void filterDeliveriesByStatus(Scanner scanner) {
        System.out.print("Enter status to filter by (Pending/In Transit/Delivered): ");
        String status = scanner.nextLine();

        boolean found = false;
        System.out.println("\n--- Deliveries with Status: " + status + " ---");
        for (Delivery delivery : deliveries) {
            if (delivery.getStatus().equalsIgnoreCase(status)) {
                System.out.println(delivery);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No deliveries found with the given status.");
        }
    }

    // Helper: Find a delivery by ID
    private Delivery findDeliveryById(int id) {
        for (Delivery delivery : deliveries) {
            if (delivery.getId() == id) {
                return delivery;
            }
        }
        return null;
    }

    // Helper: Get valid integer input
    private int getValidInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
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