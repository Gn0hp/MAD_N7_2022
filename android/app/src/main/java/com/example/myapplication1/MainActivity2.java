package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.test_homepage);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.ic_home_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.ic_diagnose_nav:
                        Toast.makeText(MainActivity2.this, "Diagnose", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.ic_chat_nav:
                        Toast.makeText(MainActivity2.this, "Chat", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.ic_home_nav:
                        Toast.makeText(MainActivity2.this, "Home", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.ic_store_nav:
                        Toast.makeText(MainActivity2.this, "Store", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.ic_more_nav:
                        Toast.makeText(MainActivity2.this, "More", Toast.LENGTH_SHORT).show();
                        return true;

                }
                return false;
            }
        });


    }
}