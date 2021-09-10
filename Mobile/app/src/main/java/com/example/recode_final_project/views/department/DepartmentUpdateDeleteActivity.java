package com.example.recode_final_project.views.department;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recode_final_project.R;
import com.example.recode_final_project.model.Department;
import com.example.recode_final_project.retrofit.RetrofitConfiguration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentUpdateDeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_update_delete);

        getSupportActionBar().hide();

        EditText departmentId = findViewById(R.id.edDepartmentId);
        departmentId.setEnabled(false);
        EditText departmentName = findViewById(R.id.edProfessorName);

        Intent intent = getIntent();
        int id = intent.getIntExtra("ID", 0);

        Call<Department> call = new RetrofitConfiguration().getDepartmentService().getDepartmentById(id);

        call.enqueue(new Callback<Department>() {
            @Override
            public void onResponse(Call<Department> call, Response<Department> response) {
                Department department = response.body();
                departmentId.setText(Integer.toString(department.getId()));
                departmentName.setText(department.getName());
            }

            @Override
            public void onFailure(Call<Department> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateDepartment(View view) {

        Department department = new Department();

        EditText editTextId = findViewById(R.id.edDepartmentId);
        int departmentId = Integer.parseInt(editTextId.getText().toString());
        department.setId(departmentId);

        EditText editTextName = findViewById(R.id.edProfessorName);
        String departmentName = editTextName.getText().toString();
        department.setName(departmentName);

        Call<Department> call = new RetrofitConfiguration().getDepartmentService().updateDepartment(department.getId(), department);
        
        call.enqueue(new Callback<Department>() {
            @Override
            public void onResponse(Call<Department> call, Response<Department> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Departamento Atualizado", Toast.LENGTH_SHORT).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<Department> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    public void deleteDepartment(View view) {

        EditText editTextId = findViewById(R.id.edDepartmentId);
        int departmentId = Integer.parseInt(editTextId.getText().toString());
        
        Call<Void> call = new RetrofitConfiguration().getDepartmentService().deleteDepartment(departmentId);
        
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Departamento Apagado", Toast.LENGTH_SHORT).show();
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