package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication1.adapters.AttributeAdapter;
import com.example.myapplication1.models.Attributes;
import com.example.myapplication1.models.Product;
import com.example.myapplication1.utils.Api;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_product);

        Product product = (Product) getIntent().getSerializableExtra("product");

        TextView name = findViewById(R.id.detailProductName);
        TextView des = findViewById(R.id.detailProductDes);
        TextView price = findViewById(R.id.detailProductPrice);

        name.setText(product.getName());
        des.setText(product.getDescription());
        price.setText(Integer.toString(product.getPrice()));
        Toast.makeText(this, product.getName(), Toast.LENGTH_SHORT).show();

        ListView listView = findViewById(R.id.attrArr);

        ArrayList<Attributes> attrArr = product.getAttributes();

        AttributeAdapter attributeAdapter = new AttributeAdapter(getApplicationContext(), attrArr);
        listView.setAdapter(attributeAdapter);

        Button btn_add_to_cart = findViewById(R.id.btn_add_to_cart);

        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Thêm sản phẩm vào giỏ hàng thành công";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {

                        Api.post_cart(product.getId(),1);
                    }

                });
            }
        });
    }
}