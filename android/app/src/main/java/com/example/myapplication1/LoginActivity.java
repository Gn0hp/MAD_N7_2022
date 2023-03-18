package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication1.models.User;

import java.io.FileInputStream;
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
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Please fill in the login form to continue", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User(username, password);

                boolean checkLogin = user.authenLogin(user.toJson().toString());
//                if(checkLogin) {
//
//                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                }
            }
        });
    }

}