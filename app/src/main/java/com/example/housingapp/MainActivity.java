package com.example.housingapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
        villageList.add(new Village("मुखिया 1", "ग्राम 1", "जिला 1"));
        villageList.add(new Village("मुखिया 2", "ग्राम 2", "जिला 2"));
        villageList.add(new Village("मुखिया 3", "ग्राम 3", "जिला 3"));
        villageList.add(new Village("मुखिया 1", "ग्राम 1", "जिला 1"));
        villageList.add(new Village("मुखिया 2", "ग्राम 2", "जिला 2"));
        villageList.add(new Village("मुखिया 3", "ग्राम 3", "जिला 3"));
        villageList.add(new Village("मुखिया 1", "ग्राम 1", "जिला 1"));
        villageList.add(new Village("मुखिया 2", "ग्राम 2", "जिला 2"));
        villageList.add(new Village("मुखिया 3", "ग्राम 3", "जिला 3"));
        villageList.add(new Village("मुखिया 1", "ग्राम 1", "जिला 1"));
        villageList.add(new Village("मुखिया 2", "ग्राम 2", "जिला 2"));
        villageList.add(new Village("मुखिया 3", "ग्राम 3", "जिला 3"));
        villageList.add(new Village("मुखिया 1", "ग्राम 1", "जिला 1"));
        villageList.add(new Village("मुखिया 2", "ग्राम 2", "जिला 2"));
        villageList.add(new Village("मुखिया 3", "ग्राम 3", "जिला 3"));

        adapter = new VillageAdapter(villageList, this);
        recyclerView.setAdapter(adapter);
    }
}
