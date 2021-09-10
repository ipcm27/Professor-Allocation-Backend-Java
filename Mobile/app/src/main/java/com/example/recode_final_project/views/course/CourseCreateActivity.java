package com.example.recode_final_project.views.course;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recode_final_project.R;
import com.example.recode_final_project.dto.CourseDTO;
import com.example.recode_final_project.model.Course;
import com.example.recode_final_project.retrofit.RetrofitConfiguration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseCreateActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_create);

        getSupportActionBar().hide();

    }

    public void createCourse(View view) {

        EditText editText = findViewById(R.id.addCourse);
        String nameCourse = editText.getText().toString();

        CourseDTO newCourse = new CourseDTO();
        newCourse.setName(nameCourse);

        Call<Course> call = new RetrofitConfiguration().getCourseService().createCourse(newCourse);

        if(!nameCourse.equals("") && !nameCourse.equals(null)){

            call.enqueue(new Callback<Course>() {
                @Override
                public void onResponse(Call<Course> call, Response<Course> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Curso Cadastrado!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Requisição Falhou!", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }

                @Override
                public void onFailure(Call<Course> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro no cadastro!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

        }else{
            Toast.makeText(getApplicationContext(), "Informação obrigatória não digitada", Toast.LENGTH_SHORT).show();
        }
    }



}