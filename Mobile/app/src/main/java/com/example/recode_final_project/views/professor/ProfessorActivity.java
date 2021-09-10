package com.example.recode_final_project.views.professor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.recode_final_project.R;
import com.example.recode_final_project.adapter.Professor_Adapter;
import com.example.recode_final_project.model.Professor;
import com.example.recode_final_project.retrofit.RetrofitConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfessorActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        getSupportActionBar().hide();

        listView = findViewById(R.id.lvProfessor);

        getAll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAll();
    }

    public void getAll(){

        Call<List<Professor>> call = new RetrofitConfiguration().getProfessorService().getProfessors();

        call.enqueue(new Callback<List<Professor>>() {
            @Override
            public void onResponse(Call<List<Professor>> call, Response<List<Professor>> response) {

                List<Professor> professors = response.body();

                listView.setAdapter(new Professor_Adapter(ProfessorActivity.this, R.layout.activity_professor_item_list, (ArrayList<Professor>) professors));

                Toast.makeText(getApplicationContext(), "Busca Realizada", Toast.LENGTH_SHORT).show();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(ProfessorActivity.this, ProfessorUpdateDeleteActivity.class);
                        intent.putExtra("ID_PROFESSOR", professors.get(i).getId());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Professor>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getProfessorById(View view) {

        EditText editText = findViewById(R.id.edProfessor);

        if(!editText.getText().toString().equals("") && !editText.getText().toString().equals(null)){
            int idProfessor = Integer.parseInt(editText.getText().toString());

            editText.setText("");

            Call<Professor> call = new RetrofitConfiguration().getProfessorService().getProfessorById(idProfessor);

            call.enqueue(new Callback<Professor>() {
                @Override
                public void onResponse(Call<Professor> call, Response<Professor> response) {
                    Professor professor = response.body();

                    ArrayList<Professor> list = new ArrayList<>();

                    list.add(professor);

                    listView.setAdapter(new Professor_Adapter(ProfessorActivity.this, R.layout.activity_professor_item_list, list));

                    Toast.makeText(getApplicationContext(), "Professor encontrado", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Professor> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void switchToCreateProfessor(View view) {
        Intent intent = new Intent(this, ProfessorCreateActivity.class);
        startActivity(intent);
    }
}