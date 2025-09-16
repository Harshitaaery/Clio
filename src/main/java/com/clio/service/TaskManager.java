package src.main.java.com.clio.service;
import java.util.ArrayList;
import java.util.List;
import src.main.java.com.clio.model.Task;

public class TaskManager {
    private List<Task> tasks; // holds all tasks

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    // Add a new task
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Remove task by ID
    public void removeTask(int id) {
        tasks.removeIf(t -> t.getId() == id);
    }

    // Find a task by ID
    public Task getTaskById(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    // Show all tasks
    public void displayAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (Task t : tasks) {
            t.displayTask();
        }
    }
    
}
