package src.main.java.com.clio.model;
import java.time.LocalDate;

public class Task {
    // Fields (Encapsulation: keep private)
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String status; // could be "TODO", "IN_PROGRESS", "DONE"

    // Constructor
    public Task(int id, String title, String description, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = "TODO"; // default when created
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public String getStatus() { return status; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setStatus(String status) { this.status = status; }

    // Custom methods
    public void markAsDone() {
        this.status = "DONE";
    }

    public void displayTask() {
        System.out.println("Task ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Status: " + status);
        System.out.println("------------------------");
    }
}
