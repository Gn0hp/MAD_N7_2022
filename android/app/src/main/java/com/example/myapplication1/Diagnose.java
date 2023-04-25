package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication1.models.Product;
import com.example.myapplication1.utils.Api;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Diagnose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose);
        Button btn1 = findViewById(R.id.chandoan);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText age = findViewById(R.id.age);
                EditText bmi = findViewById(R.id.bmi);
                EditText glucose = findViewById(R.id.glucose);
                String age_string = age.getText().toString();
                TextView ketqua = findViewById(R.id.ketqua);
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String data = Api.call_ai(age.getText().toString(),bmi.getText().toString(),glucose.getText().toString());
                        ketqua.setText("Kết quả: "+data);
                    }
                });
//                try{
//                    TimeUnit.SECONDS.sleep(3);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
            }
        });

    }
}