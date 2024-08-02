package com.example.housingapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VillageAdapter adapter;
    private List<Village> villageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        villageList = new ArrayList<>();
        adapter = new VillageAdapter(villageList, this);
        recyclerView.setAdapter(adapter);

        fetchVillages();
    }

    private void fetchVillages() {
        ApiService apiService = RetrofitClient.getClient("http://192.168.44.69:8080/")
                .create(ApiService.class);

        Call<List<Village>> call = apiService.getVillages();
        call.enqueue(new Callback<List<Village>>() {
            @Override
            public void onResponse(Call<List<Village>> call, Response<List<Village>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    villageList.clear();
                    villageList.addAll(response.body());
                    adapter.notifyDataSetChanged();

                    for (Village village : response.body()) {
                        Log.d("VillageData", "HeadName: " + village.getHeadName());
                        Log.d("VillageData", "VillageName: " + village.getVillageName());
                        Log.d("VillageData", "District: " + village.getDistrict());
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Village>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
