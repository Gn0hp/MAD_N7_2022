package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication1.adapters.ShopProductAdapter;
import com.example.myapplication1.models.Product;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);


        //data
        ArrayList<Product> products = new ArrayList<>();

        ListView cartProducts = findViewById(R.id.cartProducts);
        ShopProductAdapter adapter = new ShopProductAdapter(getApplicationContext(), products);
        cartProducts.setAdapter(adapter);
    }
}