package com.example.checkly;

import com.google.firebase.analytics.FirebaseAnalytics;
import android.os.Bundle;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkly.adapters.AssignmentAdapter;
import com.example.checkly.data.AssignmentRepository;
import com.example.checkly.models.Assignment;

public class AssignmentsActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;

    // Adapter is responsible for taking assignment data and displaying it in the RecyclerView
    private AssignmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString("screen_name", "Assignments Screen");

        mFirebaseAnalytics.logEvent("assignments_screen_opened", bundle);

        // Connect this activity to the XML layout file (activity_assignments.xml)
        setContentView(R.layout.activity_assignments);

        // Find the RecyclerView in the layout so we can display the list of assignments
        RecyclerView recyclerView = findViewById(R.id.recyclerViewAssignments);

        // Set the layout manager so items appear in a vertical scrolling list
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create the adapter:
        // - It receives the list of assignments from the repository (data source)
        // - It also defines what happens when a user clicks an assignment
        adapter = new AssignmentAdapter(
                AssignmentRepository.getInstance().getAssignments(),

                // This listener runs whenever a user taps an assignment item
                new AssignmentAdapter.OnAssignmentClickListener() {
                    @Override
                    public void onAssignmentClick(Assignment assignment) {

                        // Create an Intent to move from this screen to the details screen
                        Intent intent = new Intent(AssignmentsActivity.this, AssignmentDetailsActivity.class);

                        // Pass the selected assignment's ID to the next activity
                        // so it knows which assignment to display
                        intent.putExtra("assignment_id", assignment.getId());

                        // Start the AssignmentDetailsActivity
                        startActivity(intent);
                    }
                }
        );

        // Attach the adapter to the RecyclerView so the data appears on screen
        recyclerView.setAdapter(adapter);

        AssignmentRepository.getInstance().loadAssignmentsFromFirestore(() -> {
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // This method runs when the user returns to this screen (for example, after leaving the details screen)
        // We refresh the RecyclerView so any changes (like marking an assignment complete) are shown
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}