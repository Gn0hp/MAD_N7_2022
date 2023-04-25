package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication1.adapters.ShopProductAdapter;
import com.example.myapplication1.models.Product;
import com.example.myapplication1.utils.Api;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);


        //data
        ArrayList<Product> products= new ArrayList<>();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<Product> products2= Api.get_product_in_cart(1);
                System.out.println(products2);
                for (int i=0;i<products2.size();i++) {
                    products.add(products2.get(i));
                }
            }

        });
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        int total=0;
        for (int i=0;i<products.size();i++){
            total+= products.get(i).getPrice();
        }
        TextView total_view = findViewById(R.id.total);
        total_view.setText(Integer.toString(total)+" vnd");
        System.out.println(products);
        ListView cartProducts = findViewById(R.id.cartProducts);
        ShopProductAdapter adapter = new ShopProductAdapter(getApplicationContext(), products);
        cartProducts.setAdapter(adapter);
        Button payment = findViewById(R.id.btn_buy_now);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Diagnose.class));
            }
        });
    }
}