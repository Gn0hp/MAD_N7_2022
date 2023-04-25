package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication1.utils.Api;
import com.example.myapplication1.utils.HttpRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        System.out.println(1);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.test_homepage);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_home_nav);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
//                    case R.id.ic_diagnose_nav:
//                        Toast.makeText(MainActivity2.this, "Diagnose", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.ic_chat_nav:
//                        Toast.makeText(MainActivity2.this, "Chat", Toast.LENGTH_SHORT).show();
//                        return true;
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

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                System.out.println(Api.get_product_in_cart(1));
                // All your networking logic

                // should be here

            }

        });



    }


}