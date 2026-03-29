package com.example.checkly.models;

// This class represents a single assignment object in the app.
// It stores all the information related to one assignment.
public class Assignment {

    // Unique identifier used to distinguish each assignment
    private String id;

    // Title or name of the assignment
    private String title;

    // Course that the assignment belongs to
    private String course;

    // Due date stored in milliseconds (used for formatting later)
    private long dueDateMillis;

    // Priority level of the assignment (e.g., Low, Medium, High)
    private String priority;

    // Tracks whether the assignment is completed or still active
    private boolean completed;

    // Constructor used to create a new Assignment object with all required fields
    public Assignment(String id, String title, String course, long dueDateMillis, String priority, boolean completed) {
        this.id = id;
        this.title = title;
        this.course = course;
        this.dueDateMillis = dueDateMillis;
        this.priority = priority;
        this.completed = completed;
    }

    // Returns the unique ID of the assignment
    public String getId() {
        return id;
    }

    // Returns the assignment title
    public String getTitle() {
        return title;
    }

    // Returns the course name
    public String getCourse() {
        return course;
    }

    // Returns the due date in milliseconds
    public long getDueDateMillis() {
        return dueDateMillis;
    }

    // Returns the priority level
    public String getPriority() {
        return priority;
    }

    // Returns whether the assignment is completed
    public boolean isCompleted() {
        return completed;
    }

    // Updates the completed status when the user marks an assignment as complete or active
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
