package com.example.myapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class ChatActivity extends AppCompatActivity {
    RecyclerView messageRecyclerView;
    MessageListAdapter messageListAdapter;
    String userID = "";
    String chatCompletionId = "";
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
            EditText edtChatMessage = findViewById(R.id.edit_gchat_message);

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
                CountDownLatch countDownLatch = new CountDownLatch(1);
                final JSONArray[] messStrings = {null};
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            String jsonString = "{\"user_id\":\""+userID+"\"}";
                            System.out.println(jsonString);
                            messStrings[0] = httpRequest.arrResPost(jsonString, "/querydb/messageByUser");

                            if(messStrings[0].length()>0){
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
                                }
                            }
                            countDownLatch.countDown();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                messageRecyclerView= findViewById(R.id.recycler_gchat);
                TextView emptyView = findViewById(R.id.empty_view);
                try {
                    countDownLatch.await();
                    System.out.println("arrMessages:  ------------------" +arrMessages);
                    if(arrMessages.isEmpty()){
                        System.out.println("-------------here");
                        emptyView.setVisibility(View.VISIBLE);
                        messageRecyclerView.setVisibility(View.GONE);
                    }
                    else {
                        System.out.println("-------------or here");
                        messageListAdapter = new MessageListAdapter(this, arrMessages);
                        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                        messageRecyclerView.setAdapter(messageListAdapter);
                    }
                    countDownLatch.countDown();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                //get message



                sendMessageBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String meMessage =edtChatMessage.getText().toString();
                        Message newMeMessage = new Message(meMessage, System.currentTimeMillis(),new User(userID), 0 );
                        arrMessages.add(newMeMessage);

                        edtChatMessage.setText("");
                        edtChatMessage.clearFocus();
                        messageRecyclerView.scrollToPosition(arrMessages.size()-1);
                        CountDownLatch countDownLatch = new CountDownLatch(2);
                        final JSONArray[] resTest = {null};
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    if(arrMessages.size() == 1){
                                        String jsonString = "{\"user_id\":\""+userID+"\"}";
                                        resTest[0] = httpRequest.arrResPost(jsonString, "chat");
                                        countDownLatch.countDown();
                                        JSONObject messageJSON = resTest[0].getJSONObject(0);
                                        JSONObject chatCompletionJSON = resTest[0].getJSONObject(1);

                                        chatCompletionId = chatCompletionJSON.getString("chat_completion");
                                        Message messageResponse = new Message(messageJSON.getString("content"),System.currentTimeMillis(),new User("assistant"), 1 );
                                        arrMessages.add(messageResponse);
                                    }
                                    else {
                                        String jsonString = "{\"user_id\":\""+userID+"\"}";
                                        resTest[0] = httpRequest.arrResPost(jsonString, "continueChat");
                                        countDownLatch.countDown();

                                    }

                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();
                        try {
                            countDownLatch.await();
                            Thread.sleep(500);
                            System.out.println(resTest[0]);
                            messageRecyclerView.setAdapter(messageListAdapter);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
    }
}