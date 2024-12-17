import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManager {
    private final ArrayList<Book> books = new ArrayList<>();
    private int nextId = 1; // Auto-increment ID for books

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("\nWelcome to the Library Management System!");

        while (keepRunning) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book by Title");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addBook(scanner);
                case "2" -> viewBooks();
                case "3" -> borrowBook(scanner);
                case "4" -> returnBook(scanner);
                case "5" -> searchBook(scanner);
                case "6" -> {
                    System.out.println("Exiting Library System. Goodbye!");
                    keepRunning = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Add a new book
    private void addBook(Scanner scanner) {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();

        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();

        System.out.print("Enter the price of the book: ");
        double price = getValidDouble(scanner);

        Book book = new Book(nextId++, title, author, price);
        books.add(book);
        System.out.println("Book added successfully!");
    }

    // View all books
    private void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("\n--- List of Books ---");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Borrow a book
    private void borrowBook(Scanner scanner) {
        System.out.print("Enter the ID of the book to borrow: ");
        int id = getValidInt(scanner);

        Book book = findBookById(id);
        if (book == null) {
            System.out.println("No book found with the given ID.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is already borrowed.");
        } else {
            book.setAvailable(false);
            System.out.println("Book borrowed successfully!");
        }
    }

    // Return a book
    private void returnBook(Scanner scanner) {
        System.out.print("Enter the ID of the book to return: ");
        int id = getValidInt(scanner);

        Book book = findBookById(id);
        if (book == null) {
            System.out.println("No book found with the given ID.");
            return;
        }

        if (book.isAvailable()) {
            System.out.println("Book is not currently borrowed.");
        } else {
            book.setAvailable(true);
            System.out.println("Book returned successfully!");
        }
    }

    // Search for a book by title
    private void searchBook(Scanner scanner) {
        System.out.print("Enter the title of the book to search for: ");
        String title = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with the given title.");
        }
    }

    // Helper: Find a book by ID
    private Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
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
