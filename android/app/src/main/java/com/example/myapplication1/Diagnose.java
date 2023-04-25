package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication1.utils.Api;

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
                TextView ketqua = findViewById(R.id.ketqua);
                String data = Api.call_ai("100","10","11");
                System.out.println(data);
                ketqua.setText("Kết quả: "+data);
            }
        });

    }
}