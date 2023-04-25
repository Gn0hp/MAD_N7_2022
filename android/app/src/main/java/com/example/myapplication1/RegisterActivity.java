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
import android.widget.Toast;

import com.example.myapplication1.models.User;
import com.example.myapplication1.utils.ApiClient;
import com.example.myapplication1.utils.ChatService;
import com.example.myapplication1.utils.response.RegisterResponse;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
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
        setContentView(R.layout.register);

        Button registerBtn = findViewById(R.id.buttonRegistration);
        TextInputLayout tilEmail = findViewById(R.id.edEmail);
        TextInputLayout tilUsername = findViewById(R.id.edUsername);
        TextInputLayout tilPassword = findViewById(R.id.edPassword);
        TextInputLayout tilConfirmPassword = findViewById(R.id.edRepeatPassword);
        TextInputLayout tilPhone = findViewById(R.id.edPhone);
        CircularProgressIndicator circularProgressIndicator = findViewById(R.id.circleLoadingProgress);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerBtn.setVisibility(View.GONE);
                circularProgressIndicator.setVisibility(View.VISIBLE);

                String email = tilEmail.getEditText().getText().toString();
                String username = tilUsername.getEditText().getText().toString();
                String password = tilPassword.getEditText().getText().toString();
                String confirmPassword = tilConfirmPassword.getEditText().getText().toString();
                String phoneNumber =  tilPhone.getEditText().getText().toString();
                if(!password.equals(confirmPassword)){
                    registerBtn.setVisibility(View.GONE);
                    circularProgressIndicator.setVisibility(View.VISIBLE);
                    Toast.makeText(RegisterActivity.this, "Confirm password not match", Toast.LENGTH_SHORT).show();
                    registerBtn.setVisibility(View.VISIBLE);
                    circularProgressIndicator.setVisibility(View.GONE);
                    return;
                }
                if(username.equals("") || password.equals("") || email.equals("") || phoneNumber.equals("")){
                    registerBtn.setVisibility(View.GONE);
                    circularProgressIndicator.setVisibility(View.VISIBLE);
                    Toast.makeText(RegisterActivity.this, "Please fill in all field of this form", Toast.LENGTH_SHORT).show();
                    registerBtn.setVisibility(View.VISIBLE);
                    circularProgressIndicator.setVisibility(View.GONE);

                    //restart an activity
                    return;
                }
                User u = new User(email, username, password, phoneNumber);

                Context context = getApplicationContext();
                ChatService chatService = ApiClient.createService(ChatService.class, context);
                Call<RegisterResponse> call = chatService.authenSignup(u);
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        RegisterResponse registerResponse = response.body();
                        if(registerResponse.getResponse().equals("true")) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                                    finish();
                                }
                            }, 2000);
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "not success", Toast.LENGTH_SHORT).show();
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                    }
                });
            }
        });

    }
}