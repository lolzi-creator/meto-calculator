import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManager {
    private final ArrayList<Item> inventory = new ArrayList<>();
    private int nextId = 1; // Auto-increment ID for items

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("\nWelcome to the Inventory Management System!");

        while (keepRunning) {
            System.out.println("\n--- Inventory Management Menu ---");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Update Item Quantity");
            System.out.println("4. Delete Item");
            System.out.println("5. Search Item by Name");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addItem(scanner);
                case "2" -> viewItems();
                case "3" -> updateItemQuantity(scanner);
                case "4" -> deleteItem(scanner);
                case "5" -> searchItemByName(scanner);
                case "6" -> {
                    System.out.println("Exiting Inventory Management System. Goodbye!");
                    keepRunning = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Add a new item
    private void addItem(Scanner scanner) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter item quantity: ");
        int quantity = getValidInt(scanner);

        System.out.print("Enter item price: ");
        double price = getValidDouble(scanner);

        Item item = new Item(nextId++, name, quantity, price);
        inventory.add(item);
        System.out.println("Item added successfully!");
    }

    // View all items
    private void viewItems() {
        if (inventory.isEmpty()) {
            System.out.println("No items in inventory.");
        } else {
            System.out.println("\n--- Inventory List ---");
            for (Item item : inventory) {
                System.out.println(item);
            }
        }
    }

    // Update the quantity of an item
    private void updateItemQuantity(Scanner scanner) {
        System.out.print("Enter the ID of the item to update: ");
        int id = getValidInt(scanner);

        Item item = findItemById(id);
        if (item == null) {
            System.out.println("No item found with the given ID.");
            return;
        }

        System.out.print("Enter the new quantity: ");
        int quantity = getValidInt(scanner);
        item.setQuantity(quantity);
        System.out.println("Item quantity updated successfully!");
    }

    // Delete an item
    private void deleteItem(Scanner scanner) {
        System.out.print("Enter the ID of the item to delete: ");
        int id = getValidInt(scanner);

        Item item = findItemById(id);
        if (item == null) {
            System.out.println("No item found with the given ID.");
            return;
        }

        inventory.remove(item);
        System.out.println("Item deleted successfully!");
    }

    // Search for an item by name
    private void searchItemByName(Scanner scanner) {
        System.out.print("Enter the name of the item to search for: ");
        String name = scanner.nextLine().toLowerCase();

        boolean found = false;
        System.out.println("\n--- Search Results ---");
        for (Item item : inventory) {
            if (item.getName().toLowerCase().contains(name)) {
                System.out.println(item);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No items found matching the given name.");
        }
    }

    // Helper: Find an item by ID
    private Item findItemById(int id) {
        for (Item item : inventory) {
            if (item.getId() == id) {
                return item;
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
