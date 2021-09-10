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
import com.example.recode_final_project.model.Department;

import java.util.ArrayList;

public class Department_Adapter extends ArrayAdapter<Department> {

    private Context context;
    private int layout;
    private ArrayList<Department> departments;

    public void setDepartments(ArrayList<Department> list) {
        departments.addAll(list);
    }

    public Department_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Department> departments) {
        super(context, resource, departments);
        this.context = context;
        this.layout = resource;
        this.departments = departments;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = LayoutInflater.from(context).inflate(layout, parent, false);

        Department department = this.departments.get(position);

        TextView textViewId = itemView.findViewById(R.id.tvIdDep);
        textViewId.setText(Integer.toString(department.getId()));

        TextView textViewName = itemView.findViewById(R.id.tvNameDep);
        textViewName.setText(department.getName());

        return itemView;
    }

    @Override
    public int getCount() {
        return departments.size();
    }

}


