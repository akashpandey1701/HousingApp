package com.example.housingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager2.widget.ViewPager2;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.annotation.NonNull;

public class MainActivity3 extends AppCompatActivity {

    private ViewPager2 viewPager;
    private AppCompatButton buttonTab1, buttonTab2;
    private ImageView backButton;
    private String aadhaarCardNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        aadhaarCardNumber = intent.getStringExtra("aadhaarCardNumber");

        viewPager = findViewById(R.id.viewPager);
        buttonTab1 = findViewById(R.id.buttonTab1);
        buttonTab2 = findViewById(R.id.buttonTab2);
        backButton = findViewById(R.id.backButton);
        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(this, aadhaarCardNumber);
        viewPager.setAdapter(adapter);

        buttonTab1.setOnClickListener(v -> viewPager.setCurrentItem(0));
        buttonTab2.setOnClickListener(v -> viewPager.setCurrentItem(1));
        backButton.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity3.this, MainActivity2.class);
            startActivity(i);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private static class MyFragmentStateAdapter extends FragmentStateAdapter {
        private final String aadhaarCardNumber;

        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity, String aadhaarCardNumber) {
            super(fragmentActivity);
            this.aadhaarCardNumber = aadhaarCardNumber;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment;
            switch (position) {
                case 1:
                    fragment = new FragmentFinancial();
                    break;
                case 0:
                default:
                    fragment = new FragmentInspection();
                    break;
            }

            Bundle args = new Bundle();
            args.putString("aadhaarCardNumber", aadhaarCardNumber);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
