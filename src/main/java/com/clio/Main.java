package src.main.java.com.clio;
import java.time.LocalDate;
import java.util.Scanner;
import src.main.java.com.clio.model.Task;
import src.main.java.com.clio.service.TaskManager;

public class Main {
   private static Scanner scanner = new Scanner(System.in);
    private static TaskManager manager = new TaskManager();
    private static int taskCounter = 1; // auto-increment task IDs

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> updateTaskStatus();
                case 4 -> deleteTask();
                case 5 -> System.out.println("Exiting... Bye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("\n==== Task Manager ====");
        System.out.println("1. Add Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. Update Task Status");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addTask() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter due date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();
        LocalDate dueDate = LocalDate.parse(dateStr);

        Task task = new Task(taskCounter++, title, description, dueDate);
        manager.addTask(task);

        System.out.println("✅ Task added successfully!");
    }

    private static void viewTasks() {
        manager.displayAllTasks();
    }

    private static void updateTaskStatus() {
        System.out.print("Enter Task ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Task task = manager.getTaskById(id);
        if (task == null) {
            System.out.println("❌ Task not found.");
            return;
        }

        System.out.print("Enter new status (TODO, IN_PROGRESS, DONE): ");
        String status = scanner.nextLine();
        task.setStatus(status);

        System.out.println("✅ Task status updated!");
    }

    private static void deleteTask() {
        System.out.print("Enter Task ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        manager.removeTask(id);
        System.out.println("✅ Task deleted (if it existed).");
    }
}
