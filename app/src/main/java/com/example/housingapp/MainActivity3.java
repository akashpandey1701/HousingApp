package com.example.housingapp;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        viewPager = findViewById(R.id.viewPager);
        buttonTab1 = findViewById(R.id.buttonTab1);
        buttonTab2 = findViewById(R.id.buttonTab2);

        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(this);
        viewPager.setAdapter(adapter);


        buttonTab1.setOnClickListener(v -> viewPager.setCurrentItem(0));
        buttonTab2.setOnClickListener(v -> viewPager.setCurrentItem(1));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private static class MyFragmentStateAdapter extends FragmentStateAdapter {
        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 1:
                    return new FragmentFinancial();
                case 0:
                default:
                    return new FragmentInspection();
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
