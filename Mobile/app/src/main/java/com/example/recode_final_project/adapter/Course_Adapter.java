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
import com.example.recode_final_project.model.Course;

import java.util.ArrayList;

public class Course_Adapter extends ArrayAdapter<Course>  {

    private Context context;
    private int layout;
    private ArrayList<Course> courses;

    public void setCourses(ArrayList<Course> list) {
        courses.addAll(list);
    }

    public Course_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Course> courses) {
        super(context, resource, courses);
        this.context = context;
        this.layout = resource;
        this.courses = courses;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = LayoutInflater.from(context).inflate(layout, parent, false);

        Course course = this.courses.get(position);

        TextView textViewId = itemView.findViewById(R.id.tvIdCourse);
        textViewId.setText(Integer.toString(course.getId()));

        TextView textViewName = itemView.findViewById(R.id.tvNameCourse);
        textViewName.setText(course.getName());

        return itemView;
    }

    @Override
    public int getCount() {
        return courses.size();
    }

}