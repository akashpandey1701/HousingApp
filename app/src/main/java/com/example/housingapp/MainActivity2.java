package com.example.housingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private static final String KEY_HEAD_NAME = "headName";
    private static final String KEY_VILLAGE_NAME = "villageName";
    private static final String KEY_DISTRICT = "district";
    private static final String KEY_RATION_CARD_NUMBER = "rationCardNumber";
    private static final String KEY_AADHAR_CARD_NUMBER = "aadharCardNumber";

    private String headName;
    private String villageName;
    private String district;
    private String rationCardNumber;
    private String aadharCardNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        TextView headNameTextView = findViewById(R.id.name);
        TextView villageNameTextView = findViewById(R.id.village);
        TextView districtTextView = findViewById(R.id.district);
        TextView rationCardTextView = findViewById(R.id.ration);
        TextView aadharCardTextView = findViewById(R.id.aadhar);
        ImageView backButton = findViewById(R.id.backButton);

        if (savedInstanceState != null) {
            headName = savedInstanceState.getString(KEY_HEAD_NAME);
            villageName = savedInstanceState.getString(KEY_VILLAGE_NAME);
            district = savedInstanceState.getString(KEY_DISTRICT);
            rationCardNumber = savedInstanceState.getString(KEY_RATION_CARD_NUMBER);
            aadharCardNumber = savedInstanceState.getString(KEY_AADHAR_CARD_NUMBER);
        } else {

            Intent intent = getIntent();
            headName = intent.getStringExtra("headName");
            villageName = intent.getStringExtra("villageName");
            district = intent.getStringExtra("district");
            rationCardNumber = intent.getStringExtra("rationCardNumber");
            aadharCardNumber = intent.getStringExtra("aadharCardNumber");
        }

        headNameTextView.setText("मुखिया का नाम: " + headName);
        villageNameTextView.setText("ग्राम: " + villageName);
        districtTextView.setText("जिला: " + district);
        rationCardTextView.setText("राशन कार्ड नंबर: " + rationCardNumber);
        aadharCardTextView.setText("आधार नंबर: " + aadharCardNumber);

        backButton.setOnClickListener(v -> {
            Intent backIntent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(backIntent);
        });

        Button button = findViewById(R.id.proposed_site_button);
        Button button1 = findViewById(R.id.plinth_level_button);
        Button button2 = findViewById(R.id.roof_slab_button);
        Button button3 = findViewById(R.id.completed_site_button);

        button.setOnClickListener(v -> {
            Intent siteIntent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(siteIntent);
        });

        button1.setOnClickListener(v -> {
            Intent siteIntent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(siteIntent);
        });

        button2.setOnClickListener(v -> {
            Intent siteIntent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(siteIntent);
        });

        button3.setOnClickListener(v -> {
            Intent siteIntent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(siteIntent);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_HEAD_NAME, headName);
        outState.putString(KEY_VILLAGE_NAME, villageName);
        outState.putString(KEY_DISTRICT, district);
        outState.putString(KEY_RATION_CARD_NUMBER, rationCardNumber);
        outState.putString(KEY_AADHAR_CARD_NUMBER, aadharCardNumber);
    }
}
