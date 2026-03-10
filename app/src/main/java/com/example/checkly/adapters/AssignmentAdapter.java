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

    public interface OnAssignmentClickListener {
        void onAssignmentClick(Assignment assignment);
    }

    private final List<Assignment> assignments;
    private final OnAssignmentClickListener listener;

    public AssignmentAdapter(List<Assignment> assignments, OnAssignmentClickListener listener) {
        this.assignments = assignments;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleText;
        TextView courseText;
        ImageView checkIcon;

        public ViewHolder(View view) {
            super(view);

            titleText = view.findViewById(R.id.assignmentTitle);
            courseText = view.findViewById(R.id.assignmentCourse);
            checkIcon = view.findViewById(R.id.checkIcon);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_assignment, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Assignment assignment = assignments.get(position);

        holder.titleText.setText(assignment.getTitle());
        holder.courseText.setText(assignment.getCourse());

        if (assignment.isCompleted()) {

            holder.checkIcon.setVisibility(View.VISIBLE);

            holder.titleText.setPaintFlags(
                    holder.titleText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
            );

        } else {

            holder.checkIcon.setVisibility(View.GONE);

            holder.titleText.setPaintFlags(
                    holder.titleText.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG)
            );
        }

        holder.itemView.setOnClickListener(v -> listener.onAssignmentClick(assignment));
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }
}