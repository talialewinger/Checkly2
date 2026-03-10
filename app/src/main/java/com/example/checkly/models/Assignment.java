package com.example.checkly.models;

public class Assignment {

    private String id;
    private String title;
    private String course;
    private long dueDateMillis;
    private String priority;
    private boolean completed;

    public Assignment(String id, String title, String course, long dueDateMillis, String priority, boolean completed) {
        this.id = id;
        this.title = title;
        this.course = course;
        this.dueDateMillis = dueDateMillis;
        this.priority = priority;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCourse() {
        return course;
    }

    public long getDueDateMillis() {
        return dueDateMillis;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
