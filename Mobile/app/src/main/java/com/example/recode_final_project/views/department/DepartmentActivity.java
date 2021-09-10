package com.example.recode_final_project.views.department;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recode_final_project.R;
import com.example.recode_final_project.adapter.Department_Adapter;
import com.example.recode_final_project.model.Department;
import com.example.recode_final_project.retrofit.RetrofitConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentActivity extends AppCompatActivity {

    private ListView listView;
    List<Department> departments =new ArrayList<>();
    Department_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        getSupportActionBar().hide();

        adapter = new Department_Adapter(DepartmentActivity.this, R.layout.activity_department_item_list, (ArrayList<Department>) departments);
        listView = findViewById(R.id.lvDepartment);
        listView.setAdapter(adapter);

        getAll();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAll();
    }

    public void getDepartmentById(View view) {

        EditText editText = findViewById(R.id.edDepartment);

        if(!editText.getText().toString().equals("") && !editText.getText().toString().equals(null)){
            int idDepartment = Integer.parseInt(editText.getText().toString());

            editText.setText("");

            Call<Department> call = new RetrofitConfiguration().getDepartmentService().getDepartmentById(idDepartment);

            call.enqueue(new Callback<Department>() {
                @Override
                public void onResponse(Call<Department> call, Response<Department> response) {
                    Department department = response.body();

                    ArrayList<Department> list = new ArrayList<>();

                    list.add(department);

                    listView.setAdapter(new Department_Adapter(DepartmentActivity.this, R.layout.activity_department_item_list, list));

                    Toast.makeText(getApplicationContext(), "Departamento encontrado", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Department> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void getAll() {

        Call<List<Department>> call = new RetrofitConfiguration().getDepartmentService().getDepartments();

        call.enqueue(new Callback<List<Department>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {

                departments = response.body();
                adapter.notifyDataSetChanged();

                listView.setAdapter(new Department_Adapter(DepartmentActivity.this, R.layout.activity_department_item_list, (ArrayList<Department>) departments));

                Toast.makeText(getApplicationContext(), "Busca Realizada", Toast.LENGTH_SHORT).show();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(DepartmentActivity.this, DepartmentUpdateDeleteActivity.class);
                        intent.putExtra("ID", departments.get(i).getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Department>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Sem conexão!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void switchToCreate(View view) {

        Intent intent = new Intent(this, DepartmentCreateActivity.class);
        startActivity(intent);
    }

}