package com.example.recode_final_project.views.professor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recode_final_project.R;
import com.example.recode_final_project.dto.DepartmentProfessorDTO;
import com.example.recode_final_project.dto.ProfessorDTO;
import com.example.recode_final_project.model.Department;
import com.example.recode_final_project.model.Professor;
import com.example.recode_final_project.retrofit.RetrofitConfiguration;
import com.example.recode_final_project.views.department.DepartmentActivity;
import com.example.recode_final_project.views.department.DepartmentUpdateDeleteActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfessorCreateActivity extends AppCompatActivity {

    private Spinner spinner;
    String departmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_create);

        getSupportActionBar().hide();

        buildSpinner();

    }

    public void createProfessor(View view) {

        EditText editTextName = findViewById(R.id.edNameProfessor);
        String nameProfessor = editTextName.getText().toString();

        EditText editTextProfCPF = findViewById(R.id.edProfessorCPF);
        String profCPF = editTextProfCPF.getText().toString();

        Spinner spinnerDepProf = findViewById(R.id.spinnerProfessorDep);
        String profDep = spinnerDepProf.toString();
        Department department = new Department();
        department.setName(profDep);

        ProfessorDTO newProfessor = new ProfessorDTO();
        newProfessor.setName(nameProfessor);
        newProfessor.setCpf(profCPF);
        newProfessor.setDepartment(new DepartmentProfessorDTO(departmentId));

        Call<Professor> call = new RetrofitConfiguration().getProfessorService().createProfessor(newProfessor);

        if(!nameProfessor.equals("") && !nameProfessor.equals(null) && !profCPF.equals("") && !profCPF.equals(null) && !profDep.equals("") && !profDep.equals(null)){
            call.enqueue(new Callback<Professor>() {
                @Override
                public void onResponse(Call<Professor> call, Response<Professor> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Professor Cadastrado!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Requisição Falhou!", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }

                @Override
                public void onFailure(Call<Professor> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro no cadastro!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });


        }else{
            Toast.makeText(getApplicationContext(), "Informação obrigatória não digitada", Toast.LENGTH_SHORT).show();
        }

    }

    public void buildSpinner(){

        Call<List<Department>> call = new RetrofitConfiguration().getDepartmentService().getDepartments();

        call.enqueue(new Callback<List<Department>>() {
            @Override
            public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {
                List<Department> departments = response.body();

                List<String> nameDepartments = getListNameDepartments(departments);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProfessorCreateActivity.this, android.R.layout.simple_spinner_dropdown_item, nameDepartments);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner = findViewById(R.id.spinnerProfessorDep);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                        String selectedItemText = adapterView.getItemAtPosition(position).toString();

                        for (Department department : departments) {
                            if(selectedItemText.equals(department.getName())){
                                departmentId = Integer.toString(department.getId());
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Department>> call, Throwable t) {

            }
        });
    }

    public List<String> getListNameDepartments(List<Department> departments){
        List<String> nameDepartments = new ArrayList<>();
        for (Department department : departments) {
            nameDepartments.add(department.getName());
        }
        return nameDepartments;
    }

}