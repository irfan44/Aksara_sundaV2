package com.aksun.aksarasunda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView;

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.mainmenu);
    }

    MainMenuFragment mainMenuFragment = new MainMenuFragment();
    MateriFragment materiFragment = new MateriFragment();
    SoalFragment soalFragment = new SoalFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mainmenu:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, mainMenuFragment).commit();
                return true;

            case R.id.materimenu:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, materiFragment).commit();
                return true;

            case R.id.soalmenu:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, soalFragment).commit();
                return true;
        }
        return false;
    }
}