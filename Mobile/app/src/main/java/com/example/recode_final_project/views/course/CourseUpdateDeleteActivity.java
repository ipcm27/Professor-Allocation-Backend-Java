package com.example.recode_final_project.views.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recode_final_project.R;
import com.example.recode_final_project.model.Course;
import com.example.recode_final_project.retrofit.RetrofitConfiguration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseUpdateDeleteActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_update_delete);

        getSupportActionBar().hide();

        EditText courseId = findViewById(R.id.edCourseId);
        courseId.setEnabled(false);
        EditText courseName = findViewById(R.id.edCourseName);

        Intent intent = getIntent();
        String id = Integer.toString(intent.getIntExtra("ID", 0));

        Call<Course> call = new RetrofitConfiguration().getCourseService().getCourseById(Integer.parseInt(id));

        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                Course course = response.body();
                courseId.setText(Integer.toString(course.getId()));
                courseName.setText(course.getName());
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateCourse(View view) {

        Course course = new Course();

        EditText editTextId = findViewById(R.id.edCourseId);
        int courseId = Integer.parseInt(editTextId.getText().toString());
        course.setId(courseId);

        EditText editTextName = findViewById(R.id.edCourseName);
        String courseName = editTextName.getText().toString();
        course.setName(courseName);

        Call<Course> call = new RetrofitConfiguration().getCourseService().updateCourse(course.getId(), course);

        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Curso Atualizado", Toast.LENGTH_SHORT).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    public void deleteCourse(View view) {

        EditText editTextId = findViewById(R.id.edCourseId);
        int courseID = Integer.parseInt(editTextId.getText().toString());

        Call<Void> call = new RetrofitConfiguration().getCourseService().deleteCourse(courseID);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Curso Apagado", Toast.LENGTH_SHORT).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}