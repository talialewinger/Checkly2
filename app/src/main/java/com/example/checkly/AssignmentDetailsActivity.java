package com.example.checkly;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkly.data.AssignmentRepository;
import com.example.checkly.models.Assignment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AssignmentDetailsActivity extends AppCompatActivity {

    // This variable stores the specific assignment being displayed on this screen
    private Assignment assignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect this activity to the XML layout (activity_assignment_details.xml)
        setContentView(R.layout.activity_assignment_details);

        // Link each UI element from the layout to a Java variable so we can modify it
        TextView titleText = findViewById(R.id.detailsTitle);
        TextView courseText = findViewById(R.id.detailsCourse);
        TextView dueDateText = findViewById(R.id.detailsDueDate);
        TextView priorityText = findViewById(R.id.detailsPriority);
        Button toggleButton = findViewById(R.id.btnToggleComplete);

        // Get the assignment ID that was passed from AssignmentsActivity
        String assignmentId = getIntent().getStringExtra("assignment_id");

        // Use the repository to find the assignment object that matches this ID
        assignment = AssignmentRepository.getInstance().getById(assignmentId);

        // Only update the UI if a valid assignment was found
        if (assignment != null) {

            // Display the assignment title
            titleText.setText(assignment.getTitle());

            // Display the course name with a label for clarity
            courseText.setText("Course: " + assignment.getCourse());

            // Convert the stored due date (in milliseconds) into a readable format
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());

            // Display the formatted due date
            dueDateText.setText("Due: " + sdf.format(new Date(assignment.getDueDateMillis())));

            // Display the assignment priority level (e.g., Low, Medium, High)
            priorityText.setText("Priority: " + assignment.getPriority());

            // Set the button text based on whether the assignment is completed or not
            updateButtonText(toggleButton);
        }

        // When the user clicks the button, toggle the completed status
        toggleButton.setOnClickListener(v -> {
            if (assignment != null) {

                // Reverse the current completed state:
                // true becomes false, false becomes true
                assignment.setCompleted(!assignment.isCompleted());

                // Update the button text so it reflects the new state
                updateButtonText(toggleButton);

                // Close this screen and return to the main list
                // The list will refresh in onResume()
                finish();
            }
        });
    }

    // This method updates the button text depending on whether the assignment is completed
    private void updateButtonText(Button button) {

        // If the assignment is completed, allow the user to mark it back as active
        if (assignment.isCompleted()) {
            button.setText("Mark as Active");
        } else {
            // If the assignment is not completed, allow the user to mark it as completed
            button.setText("Mark as Completed");
        }
    }
}