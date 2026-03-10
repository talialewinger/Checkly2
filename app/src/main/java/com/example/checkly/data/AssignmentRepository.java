package com.example.checkly.data;

import com.example.checkly.models.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AssignmentRepository {

    private static AssignmentRepository instance;
    private final List<Assignment> assignments = new ArrayList<>();

    private AssignmentRepository() {
        seedAssignments();
    }

    public static AssignmentRepository getInstance() {
        if (instance == null) {
            instance = new AssignmentRepository();
        }
        return instance;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public Assignment getById(String id) {
        for (Assignment a : assignments) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    private void seedAssignments() {

        long now = System.currentTimeMillis();

        assignments.add(new Assignment(
                UUID.randomUUID().toString(),
                "Mobile Apps Final Project",
                "Mobile Apps",
                now + 2 * 86400000L,
                "High",
                false
        ));

        assignments.add(new Assignment(
                UUID.randomUUID().toString(),
                "Marketing Presentation",
                "Marketing",
                now + 3 * 86400000L,
                "Medium",
                false
        ));

        assignments.add(new Assignment(
                UUID.randomUUID().toString(),
                "Research Paper",
                "Communications",
                now + 5 * 86400000L,
                "High",
                false
        ));

        assignments.add(new Assignment(
                UUID.randomUUID().toString(),
                "SQL Practice Homework",
                "Databases",
                now + 1 * 86400000L,
                "Low",
                true
        ));
    }
}
