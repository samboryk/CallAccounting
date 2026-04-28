package com.example.callaccounting;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AbonentsFragment())
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_abonents) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new AbonentsFragment())
                        .commit();
                return true;
            }
            else if (id == R.id.nav_cities) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CitiesFragment())
                        .commit();
                return true;
            }
            else if (id == R.id.nav_calls) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CallsFragment())
                        .commit();
                return true;
            }
            return false;
        });
    }
}