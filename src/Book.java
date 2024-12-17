public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private boolean isAvailable;

    // Constructor
    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isAvailable = true; // Default availability
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // toString Method for Display
    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Author: %s | Price: $%.2f | Available: %s",
                id, title, author, price, isAvailable ? "Yes" : "No");
    }
}
