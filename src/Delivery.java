class Delivery {
    private int id;
    private String destination;
    private double weight;
    private String priority;
    private String status;

    // Constructor
    public Delivery(int id, String destination, double weight, String priority) {
        this.id = id;
        this.destination = destination;
        this.weight = weight;
        this.priority = priority;
        this.status = "Pending"; // Default status
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString Method for Display
    @Override
    public String toString() {
        return String.format("ID: %d | Destination: %s | Weight: %.2f kg | Priority: %s | Status: %s",
                id, destination, weight, priority, status);
    }
}
