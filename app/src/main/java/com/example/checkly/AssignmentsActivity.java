package com.example.checkly;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkly.adapters.AssignmentAdapter;
import com.example.checkly.data.AssignmentRepository;
import com.example.checkly.models.Assignment;

public class AssignmentsActivity extends AppCompatActivity {

    private AssignmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewAssignments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AssignmentAdapter(
                AssignmentRepository.getInstance().getAssignments(),
                new AssignmentAdapter.OnAssignmentClickListener() {
                    @Override
                    public void onAssignmentClick(Assignment assignment) {
                        Intent intent = new Intent(AssignmentsActivity.this, AssignmentDetailsActivity.class);
                        intent.putExtra("assignment_id", assignment.getId());
                        startActivity(intent);
                    }
                }
        );

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}