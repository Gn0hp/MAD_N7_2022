package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication1.adapters.AttributeAdapter;
import com.example.myapplication1.models.Attributes;
import com.example.myapplication1.models.Product;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_product);

        Product product = (Product) getIntent().getSerializableExtra("product");

        ListView listView = findViewById(R.id.attrArr);

        ArrayList<Attributes> attrArr = product.getAttributes();

        AttributeAdapter attributeAdapter = new AttributeAdapter(getApplicationContext(), attrArr);
        listView.setAdapter(attributeAdapter);
    }
}