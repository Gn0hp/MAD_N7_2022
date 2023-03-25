package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication1.adapters.MessageListAdapter;
import com.example.myapplication1.models.Message;
import com.example.myapplication1.models.User;
import com.example.myapplication1.models.bases.BaseMessage;
import com.example.myapplication1.utils.Converter;
import com.example.myapplication1.utils.HttpRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class ChatActivity extends AppCompatActivity {
    RecyclerView messageRecyclerView;
    MessageListAdapter messageListAdapter;
    String userID = "";
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
            setContentView(R.layout.activity_chat);
            ImageButton sendMessageBtn = findViewById(R.id.button_gchat_send);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            FileInputStream fis = null;

            HttpRequest httpRequest = new HttpRequest("http://10.0.2.2:3124");
            try {
                fis = getApplicationContext().openFileInput("user_session_info");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[0];
            try {
                inputBuffer = new char[fis.available()];
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                isr.read(inputBuffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            userID = new String(inputBuffer);
            System.out.println(userID);
            List<BaseMessage> arrMessages = new ArrayList<BaseMessage>();
            if(!userID.equals("")){
                CountDownLatch countDownLatch = new CountDownLatch(2);
                final JSONArray[] messStrings = {null};
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{

                            String jsonString = "{\"user_id\":\""+userID+"\"}";
                            System.out.println(jsonString);
                            messStrings[0] = httpRequest.arrResPost(jsonString, "/querydb/messageByUser");
                            System.out.println(messStrings[0]);
                            for(int i =0 ; i < messStrings[0].length(); ++i){
                                JSONObject obj = messStrings[0].getJSONObject(i);
                                String role = obj.getString("role");

                                int type;
                                User u;
                                if(role.equals("user")){
                                    type = 0;
                                    u = new User(userID);
                                }
                                else {
                                    type =1;
                                    u = new User("assistant");
                                }

                                Message mess = new Message(obj.getString("content"), Converter.convertDateTimestamp(obj.getString("createdAt")), u, type);
                                arrMessages.add(mess);
                                countDownLatch.countDown();
                            }

                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                messageRecyclerView= (RecyclerView)findViewById(R.id.recycler_gchat);
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //get message
                messageListAdapter = new MessageListAdapter(this, arrMessages);
                messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                messageRecyclerView.setAdapter(messageListAdapter);

                sendMessageBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        final String[] resTest = {""};
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    resTest[0] = httpRequest.get("");

                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });

                        try {
                            Thread.sleep(500);
                            System.out.println(resTest[0]);
                            Toast.makeText(ChatActivity.this, resTest[0], Toast.LENGTH_SHORT).show();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
    }
}