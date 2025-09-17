package src.main.java.com.clio;
import java.time.LocalDate;
import java.util.Scanner;
import src.main.java.com.clio.model.Task;
import src.main.java.com.clio.service.TaskManager;
import java.time.format.DateTimeParseException;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static TaskManager manager = new TaskManager();
    private static int taskCounter = 1;

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> updateTaskStatus();
                case 4 -> deleteTask();
                case 5 -> filterTasksByStatus();
                case 6 -> sortTasksMenu();
                case 7 -> System.out.println("Exiting... Bye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 7);
    }

    private static void showMenu() {
        System.out.println("\n==== Task Manager ====");
        System.out.println("1. Add Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. Update Task Status");
        System.out.println("4. Delete Task");
        System.out.println("5. Filter Tasks by Status");
        System.out.println("6. Sort Tasks");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addTask() {
        String title;
        do {
            System.out.print("Enter title: ");
            title = scanner.nextLine().trim();
            if (title.isEmpty()) System.out.println("Title cannot be empty.");
        } while (title.isEmpty());

        String description;
        do {
            System.out.print("Enter description: ");
            description = scanner.nextLine().trim();
            if (description.isEmpty()) System.out.println("Description cannot be empty.");
        } while (description.isEmpty());

        LocalDate dueDate = null;
        while (dueDate == null) {
            System.out.print("Enter due date (yyyy-mm-dd): ");
            String dateStr = scanner.nextLine();
            try {
                dueDate = LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Try again.");
            }
        }

        Task task = new Task(taskCounter++, title, description, dueDate);
        manager.addTask(task);
        System.out.println("Task added successfully!");
    }

    private static void viewTasks() {
        manager.displayAllTasks();
    }

    private static void updateTaskStatus() {
        System.out.print("Enter Task ID to update: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.nextLine();
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        Task task = manager.getTaskById(id);
        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        String status;
        do {
            System.out.print("Enter new status (TODO, IN_PROGRESS, DONE): ");
            status = scanner.nextLine().trim().toUpperCase();
            if (!(status.equals("TODO") || status.equals("IN_PROGRESS") || status.equals("DONE"))) {
                System.out.println("Invalid status. Please choose TODO, IN_PROGRESS, or DONE.");
            }
        } while (!(status.equals("TODO") || status.equals("IN_PROGRESS") || status.equals("DONE")));

        task.setStatus(status);
        System.out.println("Task status updated!");
    }

    private static void deleteTask() {
        System.out.print("Enter Task ID to delete: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.nextLine();
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        manager.removeTask(id);
        System.out.println("Task deleted (if it existed).");
    }

    private static void filterTasksByStatus() {
        System.out.print("Enter status to filter (TODO, IN_PROGRESS, DONE): ");
        String status = scanner.nextLine().trim().toUpperCase();
        manager.displayTasksByStatus(status);
    }

    private static void sortTasksMenu() {
        System.out.println("1. Sort by Due Date");
        System.out.println("2. Sort by Title");
        System.out.print("Choose sorting option: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> manager.sortTasksByDueDate();
            case 2 -> manager.sortTasksByTitle();
            default -> System.out.println("Invalid option.");
        }
    }
}
