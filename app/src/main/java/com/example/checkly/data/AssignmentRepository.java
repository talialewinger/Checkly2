package com.example.checkly.data;

import com.example.checkly.models.Assignment;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class AssignmentRepository {

    private static AssignmentRepository instance;

    private final List<Assignment> assignments = new ArrayList<>();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public interface AssignmentsLoadListener {
        void onAssignmentsLoaded();
    }

    private AssignmentRepository() {
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

    public void loadAssignmentsFromFirestore(AssignmentsLoadListener listener) {
        db.collection("assignments")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    assignments.clear();

                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        String id = document.getId();
                        String title = document.getString("title");
                        String course = document.getString("course");
                        String priority = document.getString("priority");

                        Long dueDateMillisValue = document.getLong("dueDateMillis");
                        long dueDateMillis = dueDateMillisValue != null ? dueDateMillisValue : 0;

                        Boolean completedValue = document.getBoolean("completed");
                        boolean completed = completedValue != null && completedValue;

                        Assignment assignment = new Assignment(
                                id,
                                title,
                                course,
                                dueDateMillis,
                                priority,
                                completed
                        );

                        assignments.add(assignment);
                    }

                    if (listener != null) {
                        listener.onAssignmentsLoaded();
                    }
                });
    }
    public void updateAssignmentCompleted(String assignmentId, boolean completed) {
        db.collection("assignments")
                .document(assignmentId)
                .update("completed", completed);
    }
}

