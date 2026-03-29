package com.example.checkly.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.checkly.R;
import com.example.checkly.models.Assignment;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {

    // This interface allows the activity (AssignmentsActivity) to define
    // what should happen when a user clicks on an assignment item
    public interface OnAssignmentClickListener {
        void onAssignmentClick(Assignment assignment);
    }

    // The list of assignment data that will be displayed in the RecyclerView
    private final List<Assignment> assignments;

    // Listener used to handle click events on each item
    private final OnAssignmentClickListener listener;

    // Constructor: receives the assignment data and the click listener from the activity
    public AssignmentAdapter(List<Assignment> assignments, OnAssignmentClickListener listener) {
        this.assignments = assignments;
        this.listener = listener;
    }

    // ViewHolder stores references to the views inside one item row
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // TextView for the assignment title
        TextView titleText;

        // TextView for the course name
        TextView courseText;

        // ImageView used to show a checkmark when the assignment is completed
        ImageView checkIcon;

        public ViewHolder(View view) {
            super(view);

            // Link the views from item_assignment.xml to these variables
            titleText = view.findViewById(R.id.assignmentTitle);
            courseText = view.findViewById(R.id.assignmentCourse);
            checkIcon = view.findViewById(R.id.checkIcon);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate (create) the layout for a single item in the list
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_assignment, parent, false);

        // Return a new ViewHolder containing this layout
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Get the assignment object for the current row
        Assignment assignment = assignments.get(position);

        // Set the title and course text for this row
        holder.titleText.setText(assignment.getTitle());
        holder.courseText.setText(assignment.getCourse());

        // Check if the assignment is marked as completed
        if (assignment.isCompleted()) {

            // Show the checkmark icon
            holder.checkIcon.setVisibility(View.VISIBLE);

            // Add a strikethrough effect to the title to visually indicate completion
            holder.titleText.setPaintFlags(
                    holder.titleText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
            );

        } else {

            // Hide the checkmark icon if the assignment is not completed
            holder.checkIcon.setVisibility(View.GONE);

            // Remove the strikethrough effect if it was previously applied
            holder.titleText.setPaintFlags(
                    holder.titleText.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG)
            );
        }

        // Set a click listener on the entire row
        // When clicked, it calls the listener defined in AssignmentsActivity
        holder.itemView.setOnClickListener(v -> listener.onAssignmentClick(assignment));
    }

    @Override
    public int getItemCount() {

        // Return the total number of items in the list
        // This tells the RecyclerView how many rows to display
        return assignments.size();
    }
}