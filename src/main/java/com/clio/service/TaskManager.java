package src.main.java.com.clio.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import src.main.java.com.clio.model.Task;

public class TaskManager {
    private List<Task> tasks; // holds all tasks

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    // remove by id
    public void removeTask(int id) {
        tasks.removeIf(t -> t.getId() == id);
    }

    // find by id
    public Task getTaskById(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    
    public void displayAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (Task t : tasks) {
            t.displayTask();
        }
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
    return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .toList();
}

public List<Task> getOverdueTasks() {
    LocalDate today = LocalDate.now();
    return tasks.stream()
                .filter(task -> task.getDueDate().isBefore(today))
                .toList();
}

public List<Task> getTasksSortedByDueDate() {
    return tasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .toList();
}

public List<Task> getTasksSortedByTitle() {
    return tasks.stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .toList();
}

    
}
