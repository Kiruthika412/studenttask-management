import model.Student;
import model.Task;
import service.TaskManager;
import util.FileUtil;
import exception.TaskNotFoundException;
import exception.InvalidInputException;
import java.util.*;
import java.io.*;

public class Main {
    private static final String DATA_FILE = "tasks.dat";
    private static TaskManager manager;
    
    public static void main(String[] args) {
        // Load tasks on startup
        try {
            manager = FileUtil.loadTaskManager(DATA_FILE);
            System.out.println("Loaded existing data.");
        } catch (Exception e) {
            manager = new TaskManager();
            System.out.println("No existing data found. Starting fresh.");
        }
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "1": addStudent(sc); break;
                    case "2": addTask(sc); break;
                    case "3": listTasks(); break;
                    case "4": markTaskCompleted(sc); break;
                    case "5": showActivityHistory(); break;
                    case "0": running = false; break;
                    default: throw new InvalidInputException("Invalid menu option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        // Save tasks on exit
        try {
            FileUtil.saveTaskManager(manager, DATA_FILE);
            System.out.println("Data saved. Exiting.");
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Student Task & Progress Management System ---");
        System.out.println("1. Add Student");
        System.out.println("2. Add Task");
        System.out.println("3. List Tasks");
        System.out.println("4. Mark Task as Completed");
        System.out.println("5. Show Activity History");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    // --- Menu Actions ---
    private static void addStudent(Scanner sc) {
        System.out.print("Enter student ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        manager.getStudents().put(id, new Student(id, name));
        manager.getActivityHistory().add("Added student: " + name);
        System.out.println("Student added.");
    }

    private static void addTask(Scanner sc) {
        System.out.print("Enter task ID: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter description: ");
        String desc = sc.nextLine();
        System.out.print("Enter category: ");
        String cat = sc.nextLine();
        System.out.print("Enter student ID: ");
        int sid = Integer.parseInt(sc.nextLine());
        if (!manager.getStudents().containsKey(sid)) throw new InvalidInputException("Student not found.");
        Task t = new Task(id, title, desc, cat, sid);
        manager.getTasks().add(t);
        manager.getCategories().add(cat);
        manager.getActivityHistory().add("Added task: " + title);
        System.out.println("Task added.");
    }

    private static void listTasks() {
        System.out.println("\nAll Tasks:");
        for (Task t : manager.getTasks()) {
            System.out.println("ID: " + t.getId() + ", Title: " + t.getTitle() + ", Student: " + t.getStudentId() + ", Completed: " + t.isCompleted());
        }
    }

    private static void markTaskCompleted(Scanner sc) throws TaskNotFoundException {
        System.out.print("Enter task ID to mark completed: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean found = false;
        for (Task t : manager.getTasks()) {
            if (t.getId() == id) {
                t.setCompleted(true);
                manager.getActivityHistory().add("Marked task completed: " + t.getTitle());
                System.out.println("Task marked as completed.");
                found = true;
                break;
            }
        }
        if (!found) throw new TaskNotFoundException("Task not found.");
    }

    private static void showActivityHistory() {
        System.out.println("\nActivity History:");
        Iterator<String> it = manager.getActivityHistory().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
