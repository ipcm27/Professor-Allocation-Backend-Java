package com.example.recode_final_project.views.professor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfessorUpdateDeleteActivity extends AppCompatActivity {

    private Spinner spinner;
    String departmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_update_delete);

        getSupportActionBar().hide();

        EditText professorId = findViewById(R.id.edProfessorId);
        professorId.setEnabled(false);
        EditText professorName = findViewById(R.id.edProfessorName);
        EditText professorCpf = findViewById(R.id.edCpfProfessor);

        Intent intent = getIntent();
        int idProfessor = intent.getIntExtra("ID_PROFESSOR", 0);

        Call<Professor> call = new RetrofitConfiguration().getProfessorService().getProfessorById(idProfessor);

        call.enqueue(new Callback<Professor>() {
            @Override
            public void onResponse(Call<Professor> call, Response<Professor> response) {
                Professor professor = response.body();
                professorId.setText(Integer.toString(professor.getId()));
                professorName.setText(professor.getName());
                professorCpf.setText(professor.getCpf());
            }

            @Override
            public void onFailure(Call<Professor> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();
            }
        });

        buildSpinner();

    }

    public void updateProfessor(View view) {
        Professor professor = new Professor();

        EditText editTextId = findViewById(R.id.edProfessorId);
        int professorId = Integer.parseInt(editTextId.getText().toString());
        professor.setId(professorId);

        EditText editTextName = findViewById(R.id.edProfessorName);
        String nameProf = editTextName.getText().toString();

        EditText editTextCpf = findViewById(R.id.edCpfProfessor);
        String cpfProf = editTextCpf.getText().toString();

        ProfessorDTO newProfessor = new ProfessorDTO();
        newProfessor.setName(nameProf);
        newProfessor.setCpf(cpfProf);
        newProfessor.setDepartment(new DepartmentProfessorDTO(departmentId));

        Call<Professor> call = new RetrofitConfiguration().getProfessorService().updateProfessor(professor.getId(), newProfessor);

        call.enqueue(new Callback<Professor>() {
            @Override
            public void onResponse(Call<Professor> call, Response<Professor> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Professor Atualizado", Toast.LENGTH_SHORT).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<Professor> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    public void deleteProfessor(View view) {
        EditText editTextId = findViewById(R.id.edProfessorId);
        int professorId = Integer.parseInt(editTextId.getText().toString());

        Call<Void> call = new RetrofitConfiguration().getProfessorService().deleteProfessor(professorId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Professor Apagado", Toast.LENGTH_SHORT).show();
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


    public void buildSpinner(){

        Call<List<Department>> call = new RetrofitConfiguration().getDepartmentService().getDepartments();

        call.enqueue(new Callback<List<Department>>() {
            @Override
            public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {
                List<Department> departments = response.body();

                List<String> nameDepartments = getListNameDepartments(departments);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProfessorUpdateDeleteActivity.this, android.R.layout.simple_spinner_dropdown_item, nameDepartments);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner = findViewById(R.id.spinnerDepartmentProfesssor);
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