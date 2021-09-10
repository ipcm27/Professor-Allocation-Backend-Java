package com.example.recode_final_project.views.department;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recode_final_project.R;
import com.example.recode_final_project.dto.DepartmentDTO;
import com.example.recode_final_project.model.Department;
import com.example.recode_final_project.retrofit.RetrofitConfiguration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_create);

        getSupportActionBar().hide();

    }

    public void createDepartment(View view) {

        EditText editText = findViewById(R.id.addDepartment);
        String nameDepartment = editText.getText().toString();

        DepartmentDTO newDepartment = new DepartmentDTO();
        newDepartment.setName(nameDepartment);

        Call<Department> call = new RetrofitConfiguration().getDepartmentService().createDepartment(newDepartment);

        if(!nameDepartment.equals("") && !nameDepartment.equals(null)){

            call.enqueue(new Callback<Department>() {
                @Override
                public void onResponse(Call<Department> call, Response<Department> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Departamento Cadastrado!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Requisição Falhou!", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }

                @Override
                public void onFailure(Call<Department> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro no cadastro!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });



        }else{
            Toast.makeText(getApplicationContext(), "Informação obrigatória não digitada", Toast.LENGTH_SHORT).show();
        }

    }

}