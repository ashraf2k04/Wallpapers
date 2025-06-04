package com.example.javaxml.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.javaxml.R;
import com.example.javaxml.databinding.ActivityMainBinding;
import com.example.javaxml.fragments.HomeFragment;
import com.example.javaxml.fragments.SearchFragment;
import com.example.javaxml.fragments.UserFragment;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    TabLayout tabLayout;

    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        var binding = ActivityMainBinding.inflate(getLayoutInflater());
        var rootView = binding.getRoot();

        setContentView(rootView);


        view = binding.main;

        frameLayout = binding.framelayout;
        tabLayout = binding.tabLayout;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout, new HomeFragment())
                .commit();


        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {

                        Fragment fragment = null;
                        switch (tab.getPosition()){
                            case 0:
                                fragment = new HomeFragment();
                                break;
                            case 1:
                                fragment = new SearchFragment();
                                break;
                            case 2:
                                fragment = new UserFragment();
                        }
                        assert fragment != null;
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.framelayout, fragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .commit();
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                }
        );


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}