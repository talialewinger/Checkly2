package com.example.checkly.data;

import com.example.checkly.models.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AssignmentRepository {

    // Singleton instance so the same data source is shared across the entire app
    private static AssignmentRepository instance;

    // List that stores all assignments in memory (this acts as a temporary database)
    private final List<Assignment> assignments = new ArrayList<>();

    // Private constructor ensures this class cannot be instantiated directly
    // It also initializes the app with sample assignment data
    private AssignmentRepository() {
        seedAssignments();
    }

    // Returns the single instance of the repository (Singleton pattern)
    // Ensures all activities use the same data
    public static AssignmentRepository getInstance() {
        if (instance == null) {
            instance = new AssignmentRepository();
        }
        return instance;
    }

    // Returns the full list of assignments
    // This is used by the RecyclerView adapter to display all items
    public List<Assignment> getAssignments() {
        return assignments;
    }

    // Finds and returns a specific assignment based on its unique ID
    // Used by the details screen to load the correct assignment
    public Assignment getById(String id) {
        for (Assignment a : assignments) {

            // Compare each assignment's ID to the given ID
            if (a.getId().equals(id)) {
                return a;
            }
        }

        // Return null if no matching assignment is found
        return null;
    }

    // This method creates sample assignments when the app starts
    // It simulates having data without using a real database
    private void seedAssignments() {

        // Get the current system time (used to calculate due dates)
        long now = System.currentTimeMillis();

        // Add assignment 1
        assignments.add(new Assignment(
                UUID.randomUUID().toString(), // Generate a unique ID
                "Mobile Apps Final Project",
                "Mobile Apps",
                now + 2 * 86400000L, // Due in 2 days (86400000 ms = 1 day)
                "High",
                false // Not completed
        ));

        // Add assignment 2
        assignments.add(new Assignment(
                UUID.randomUUID().toString(),
                "Marketing Presentation",
                "Marketing",
                now + 3 * 86400000L,
                "Medium",
                false
        ));

        // Add assignment 3
        assignments.add(new Assignment(
                UUID.randomUUID().toString(),
                "Research Paper",
                "Communications",
                now + 5 * 86400000L,
                "High",
                false
        ));

        // Add assignment 4 (already completed)
        assignments.add(new Assignment(
                UUID.randomUUID().toString(),
                "SQL Practice Homework",
                "Databases",
                now + 1 * 86400000L,
                "Low",
                true // Already marked as completed
        ));
    }
}
