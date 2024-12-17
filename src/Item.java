class Item {
    private int id;
    private String name;
    private int quantity;
    private double price;

    // Constructor
    public Item(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString Method for Display
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Quantity: %d | Price: $%.2f",
                id, name, quantity, price);
    }
}
