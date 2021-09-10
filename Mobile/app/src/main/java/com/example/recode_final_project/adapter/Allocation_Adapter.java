
package com.example.recode_final_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.recode_final_project.R;
import com.example.recode_final_project.model.Allocation;

import java.util.ArrayList;

public class Allocation_Adapter extends ArrayAdapter<Allocation> {

    private Context context;
    private int layout;
    private ArrayList<Allocation> allocations;

    public void setCourses(ArrayList<Allocation> list) {
        allocations.addAll(list);
    }

    public Allocation_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Allocation> allocations) {
        super(context, resource, allocations);
        this.context = context;
        this.layout = resource;
        this.allocations = allocations;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = LayoutInflater.from(context).inflate(layout, parent, false);

        Allocation allocation = this.allocations.get(position);

        TextView textViewId = itemView.findViewById(R.id.tvIdAlloc);
        textViewId.setText(Integer.toString(allocation.getId()));

        TextView textViewProf = itemView.findViewById(R.id.tvProfAlloc);
        textViewProf.setText(allocation.getProfessor().getName());

        TextView textViewCourse = itemView.findViewById(R.id.tvCourseAlloc);
        textViewCourse.setText(allocation.getCourse().getName());

        TextView textViewDay = itemView.findViewById(R.id.tvDayOfWeek);
        textViewDay.setText(allocation.getDayofweek());

        TextView textViewStartHour = itemView.findViewById(R.id.tvStartHour);
        textViewStartHour.setText(allocation.getStart().substring(0,5));

        TextView textViewEndHour = itemView.findViewById(R.id.tvEndHour);
        textViewEndHour.setText(allocation.getEnd().substring(0,5));

        return itemView;
    }

    @Override
    public int getCount() {
        return allocations.size();
    }

}
