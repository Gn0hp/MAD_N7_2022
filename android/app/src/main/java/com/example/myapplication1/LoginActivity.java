package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication1.models.User;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
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

        setContentView(R.layout.login);

        EditText edUsername = findViewById(R.id.edUsername);
        EditText edPassword = findViewById(R.id.edPassword);

        Button btnLogin = findViewById(R.id.buttonLogin);




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircularProgressIndicator circleLoadingProgress = findViewById(R.id.circleLoadingProgress);
                btnLogin.setVisibility(View.GONE);
                circleLoadingProgress.setVisibility(View.VISIBLE);
                circleLoadingProgress.setIndeterminate(true);


                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Please fill in the login form to continue", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User(username, password);

                user = user.authenLogin(user.toJson().toString());
                System.out.println(user);
                if(user != null){
                    try{
                        FileOutputStream out = getApplicationContext().openFileOutput("user_session_info", Context.MODE_PRIVATE);
                        out.write(user.getId().getBytes());
                        out.close();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(getApplicationContext(), MainActivity2.class));

                                finish();
                            }
                        }, 2000);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    startActivity(new Intent(getApplicationContext(), this.getClass()));

                }
            }
        });
    }

}