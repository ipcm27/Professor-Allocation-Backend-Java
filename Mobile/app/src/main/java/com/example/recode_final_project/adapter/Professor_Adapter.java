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
import com.example.recode_final_project.model.Professor;

import java.util.ArrayList;

public class Professor_Adapter extends ArrayAdapter<Professor> {

    private Context context;
    private int layout;
    private ArrayList<Professor> professors;

    public void setProfessors(ArrayList<Professor> list) {
        professors.addAll(list);
    }

    public Professor_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Professor> professors) {
        super(context, resource, professors);
        this.context = context;
        this.layout = resource;
        this.professors = professors;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = LayoutInflater.from(context).inflate(layout, parent, false);

        Professor professor = this.professors.get(position);

        TextView textViewId = itemView.findViewById(R.id.tvIdProf);
        textViewId.setText(Integer.toString(professor.getId()));

        TextView textViewName = itemView.findViewById(R.id.tvNameProf);
        textViewName.setText(professor.getName());

        TextView textViewCPF = itemView.findViewById(R.id.tvCpf);
        textViewCPF.setText(professor.getCpf());

        return itemView;
    }

    @Override
    public int getCount() {
        return professors.size();
    }
}
