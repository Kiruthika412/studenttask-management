package model;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String title;
    private String description;
    private String category;
    private int studentId;
    private boolean completed;

    public Task(int id, String title, String description, String category, int studentId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.studentId = studentId;
        this.completed = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public int getStudentId() { return studentId; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
