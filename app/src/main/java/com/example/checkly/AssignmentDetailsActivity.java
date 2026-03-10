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

    private Assignment assignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_details);

        TextView titleText = findViewById(R.id.detailsTitle);
        TextView courseText = findViewById(R.id.detailsCourse);
        TextView dueDateText = findViewById(R.id.detailsDueDate);
        TextView priorityText = findViewById(R.id.detailsPriority);
        Button toggleButton = findViewById(R.id.btnToggleComplete);

        String assignmentId = getIntent().getStringExtra("assignment_id");
        assignment = AssignmentRepository.getInstance().getById(assignmentId);

        if (assignment != null) {
            titleText.setText(assignment.getTitle());
            courseText.setText("Course: " + assignment.getCourse());

            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            dueDateText.setText("Due: " + sdf.format(new Date(assignment.getDueDateMillis())));

            priorityText.setText("Priority: " + assignment.getPriority());

            updateButtonText(toggleButton);
        }

        toggleButton.setOnClickListener(v -> {
            if (assignment != null) {
                assignment.setCompleted(!assignment.isCompleted());
                updateButtonText(toggleButton);
                finish();
            }
        });
    }

    private void updateButtonText(Button button) {
        if (assignment.isCompleted()) {
            button.setText("Mark as Active");
        } else {
            button.setText("Mark as Completed");
        }
    }
}
