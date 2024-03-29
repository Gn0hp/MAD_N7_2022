package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication1.adapters.ShopProductAdapter;
import com.example.myapplication1.models.Attributes;
import com.example.myapplication1.models.Product;
import com.example.myapplication1.utils.Api;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);



        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.ic_store_nav);

        ListView listView = findViewById(R.id.listProduct);

        // _______________________________Handle_______________________
//        ArrayList<Product> products= Api.get_all_product();
        ArrayList<Product> products= new ArrayList<>();
        ArrayList<Attributes> attributes = new ArrayList<>();
        attributes.add(new Attributes(1,"name 1", "val1"));
        attributes.add(new Attributes(2,"name 2", "val3"));

        products.add(new Product(1,"product1", "des1", 2, 300, attributes));
        products.add(new Product(2,"product2", "des2", 3, 600, attributes));

        ShopProductAdapter shopProductAdapter = new ShopProductAdapter(getApplicationContext(), products);
        listView.setAdapter(shopProductAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = products.get(position);
                startActivity(new Intent(getApplicationContext(), DetailActivity.class).putExtra("product", product)
                );
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.ic_home_nav:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        return true;
                    case R.id.ic_store_nav:
                        startActivity(new Intent(getApplicationContext(), ShopActivity.class));
                        return true;
                    case R.id.ic_cart_nav:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        return true;
                    case R.id.ic_more_nav:
                        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        return true;

                }
                return false;
            }
        });
    }
}