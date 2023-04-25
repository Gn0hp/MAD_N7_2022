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
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication1.models.User;
import com.example.myapplication1.utils.ApiClient;
import com.example.myapplication1.utils.ChatService;
import com.example.myapplication1.utils.request.UserRequest;
import com.example.myapplication1.utils.response.UserLoginResponse;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        TextInputLayout edUsername = findViewById(R.id.edUsername);
        TextInputLayout edPassword = findViewById(R.id.edPassword);

        Button btnLogin = findViewById(R.id.buttonLogin);

        TextView txtSignup = findViewById(R.id.txtSignup);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircularProgressIndicator circleLoadingProgress = findViewById(R.id.circleLoadingProgress);
                btnLogin.setVisibility(View.GONE);
                circleLoadingProgress.setVisibility(View.VISIBLE);
                circleLoadingProgress.setIndeterminate(true);


                String username = edUsername.getEditText().getText().toString();
                String password = edPassword.getEditText().getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Please fill in the login form to continue", Toast.LENGTH_SHORT).show();
                    btnLogin.setVisibility(View.VISIBLE);
                    circleLoadingProgress.setVisibility(View.GONE);
                    circleLoadingProgress.setIndeterminate(false);
                    return;
                }
                UserRequest userReq = new UserRequest(username, password);
                Context context = getApplicationContext();
                ChatService chatService = ApiClient.createService(ChatService.class, context);
                Call<UserLoginResponse> call = chatService.authenLogin(userReq);
                final User[] u = new User[1];
                final String[] chatCompletionId = new String[1];
                call.enqueue(new Callback<UserLoginResponse>() {
                    @Override
                    public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                        UserLoginResponse userLogin = response.body();
                        u[0] = new User(userLogin.getId(), userLogin.getName(), userLogin.getEmail(), userLogin.getPhoneNumber(), userLogin.getUsername(), userLogin.getProfileURL(), userLogin.getUsername(), userLogin.getPassword());

                        chatCompletionId[0] = userLogin.getChatCompletion();
                        System.out.println(u[0]);
                        if(u[0] != null){
                            try{
                                FileOutputStream out = getApplicationContext().openFileOutput("user_session_info", Context.MODE_PRIVATE);
                                FileOutputStream out1 = getApplicationContext().openFileOutput("chat_completion_session_info", Context.MODE_PRIVATE);
                                out.write(u[0].getId().getBytes());
                                out1.write(chatCompletionId[0].getBytes());
                                out.close();
                                out1.close();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                                        finish();
                                    }
                                }, 2000);

                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }else{
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

}