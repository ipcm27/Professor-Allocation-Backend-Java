package com.example.recode_final_project.views.course;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.recode_final_project.R;
import com.example.recode_final_project.adapter.Course_Adapter;
import com.example.recode_final_project.model.Course;
import com.example.recode_final_project.retrofit.RetrofitConfiguration;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);;

        getSupportActionBar().hide();

        listView = findViewById(R.id.lvCourse);

        getAll();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAll();
    }

    public void getCourseById(View view) {

        EditText editText = findViewById(R.id.edCourse);

        if(!editText.getText().toString().equals("") && !editText.getText().toString().equals(null)){
            int idCourse = Integer.parseInt(editText.getText().toString());

            editText.setText("");

            Call<Course> call = new RetrofitConfiguration().getCourseService().getCourseById(idCourse);

            call.enqueue(new Callback<Course>() {
                @Override
                public void onResponse(Call<Course> call, Response<Course> response) {
                    Course course = response.body();

                    ArrayList<Course> list = new ArrayList<>();

                    list.add(course);

                    listView.setAdapter(new Course_Adapter(CourseActivity.this, R.layout.activity_course_item_list, list));

                    Toast.makeText(getApplicationContext(), "Curso encontrado", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Course> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void getAll() {

        Call<List<Course>> call = new RetrofitConfiguration().getCourseService().getCourses();

        call.enqueue(new Callback<List<Course>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {

                List<Course> courses = response.body();

                listView.setAdapter(new Course_Adapter(CourseActivity.this, R.layout.activity_course_item_list, (ArrayList<Course>) courses));

                Toast.makeText(getApplicationContext(), "Busca Realizada", Toast.LENGTH_SHORT).show();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(CourseActivity.this, CourseUpdateDeleteActivity.class);
                        intent.putExtra("ID", courses.get(i).getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Sem conexão!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void switchToCreate(View view) {

        Intent intent = new Intent(this, CourseCreateActivity.class);
        startActivity(intent);

    }

}