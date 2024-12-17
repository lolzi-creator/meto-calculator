public class Task {
    private int id;
    private String description;
    private String priority;
    private boolean isComplete;

    // Constructor
    public Task(int id, String description, String priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.isComplete = false; // Default status
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    // toString method for displaying task details
    @Override
    public String toString() {
        return String.format("ID: %d | Description: %s | Priority: %s | Completed: %s",
                id, description, priority, isComplete ? "Yes" : "No");
    }
}
