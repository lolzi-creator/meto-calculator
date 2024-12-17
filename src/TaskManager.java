import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int nextId = 1; // Auto-increment ID for tasks

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("\nWelcome to the Task Management System!");

        while (keepRunning) {
            System.out.println("\n--- Task Management Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Mark Task as Complete");
            System.out.println("5. Filter Tasks by Status");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addTask(scanner);
                case "2" -> viewTasks();
                case "3" -> updateTask(scanner);
                case "4" -> markTaskComplete(scanner);
                case "5" -> filterTasksByStatus(scanner);
                case "6" -> {
                    System.out.println("Exiting Task Management System. Goodbye!");
                    keepRunning = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Add a new task
    private void addTask(Scanner scanner) {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.print("Enter task priority (High/Medium/Low): ");
        String priority = scanner.nextLine();

        Task task = new Task(nextId++, description, priority);
        tasks.add(task);
        System.out.println("Task added successfully!");
    }

    // View all tasks
    private void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\n--- List of Tasks ---");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    // Update a task
    private void updateTask(Scanner scanner) {
        System.out.print("Enter the ID of the task to update: ");
        int id = getValidInt(scanner);

        Task task = findTaskById(id);
        if (task == null) {
            System.out.println("No task found with the given ID.");
            return;
        }

        System.out.print("Enter new description (leave blank to keep current): ");
        String newDescription = scanner.nextLine();
        if (!newDescription.isEmpty()) {
            task.setDescription(newDescription);
        }

        System.out.print("Enter new priority (High/Medium/Low, leave blank to keep current): ");
        String newPriority = scanner.nextLine();
        if (!newPriority.isEmpty()) {
            task.setPriority(newPriority);
        }

        System.out.println("Task updated successfully!");
    }

    // Mark a task as complete
    private void markTaskComplete(Scanner scanner) {
        System.out.print("Enter the ID of the task to mark as complete: ");
        int id = getValidInt(scanner);

        Task task = findTaskById(id);
        if (task == null) {
            System.out.println("No task found with the given ID.");
            return;
        }

        task.setComplete(true);
        System.out.println("Task marked as complete!");
    }

    // Filter tasks by status
    private void filterTasksByStatus(Scanner scanner) {
        System.out.print("Filter by status (complete/incomplete): ");
        String status = scanner.nextLine().toLowerCase();

        boolean isComplete = status.equals("complete");

        System.out.println("\n--- Filtered Tasks ---");
        for (Task task : tasks) {
            if (task.isComplete() == isComplete) {
                System.out.println(task);
            }
        }
    }

    // Helper: Find a task by ID
    private Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
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
}
