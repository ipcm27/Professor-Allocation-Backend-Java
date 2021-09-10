package com.example.recode_final_project.views.allocation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recode_final_project.R;
import com.example.recode_final_project.adapter.Allocation_Adapter;
import com.example.recode_final_project.model.Allocation;
import com.example.recode_final_project.retrofit.RetrofitConfiguration;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllocationActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation);

        getSupportActionBar().hide();

        listView = findViewById(R.id.lvAllocation);

        getAll();

    }

    @Override
    protected void onResume() {
        super.onResume();

        getAll();
    }

    public void getAll(){

        Call<List<Allocation>> call = new RetrofitConfiguration().getAllocationService().getAllocations();

        call.enqueue(new Callback<List<Allocation>>() {
            @Override
            public void onResponse(Call<List<Allocation>> call, Response<List<Allocation>> response) {
                List<Allocation> allocations = response.body();

                listView.setAdapter(new Allocation_Adapter(AllocationActivity.this, R.layout.activity_allocation_item_list, (ArrayList<Allocation>) allocations));

                Toast.makeText(getApplicationContext(), "Busca Realizada", Toast.LENGTH_SHORT).show();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://elegant-knuth-94245c.netlify.app/allocations/")));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Allocation>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void getAllocationById(View view) {
        EditText editText = findViewById(R.id.edAllocation);

        if(!editText.getText().toString().equals("") && !editText.getText().toString().equals(null)){
            int idAllocation = Integer.parseInt(editText.getText().toString());

            editText.setText("");

            Call<Allocation> call = new RetrofitConfiguration().getAllocationService().getAllocationById(idAllocation);

            call.enqueue(new Callback<Allocation>() {
                @Override
                public void onResponse(Call<Allocation> call, Response<Allocation> response) {
                    Allocation allocation = response.body();

                    ArrayList<Allocation> list = new ArrayList<>();

                    list.add(allocation);

                    listView.setAdapter(new Allocation_Adapter(AllocationActivity.this, R.layout.activity_allocation_item_list, list));

                    Toast.makeText(getApplicationContext(), "Alocação encontrada", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<Allocation> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    public void switchToCreateAllocation(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://elegant-knuth-94245c.netlify.app/allocations/")));
    }

}