package service;

import model.Student;
import model.Task;
import exception.TaskNotFoundException;
import exception.InvalidInputException;
import java.util.*;
import java.io.*;

public class TaskManager implements Serializable {
    private List<Task> tasks = new ArrayList<>();
    private Map<Integer, Student> students = new HashMap<>();
    private Map<Integer, List<Task>> studentTasks = new HashMap<>();
    private LinkedList<String> activityHistory = new LinkedList<>();
    private Set<String> categories = new HashSet<>();
    
    public TaskManager() {}

    // Add, remove, update, get methods for students and tasks
    // File I/O methods for saving/loading
    // Activity history methods
    // ...methods to be implemented...

    public List<Task> getTasks() { return tasks; }
    public Map<Integer, Student> getStudents() { return students; }
    public LinkedList<String> getActivityHistory() { return activityHistory; }
    public Set<String> getCategories() { return categories; }
}
