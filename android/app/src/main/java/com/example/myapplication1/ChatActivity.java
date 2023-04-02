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
import com.example.myapplication1.utils.OnResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
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
    String chatCompletionId;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
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
        FileInputStream fis = null, fisChatCompletion =null;

        HttpRequest httpRequest = new HttpRequest("http://10.0.2.2:3124");
        try {
            fis = getApplicationContext().openFileInput("user_session_info");
            fisChatCompletion = getApplicationContext().openFileInput("chat_completion_session_info");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        InputStreamReader isr = new InputStreamReader(fis);
        InputStreamReader isr1 = new InputStreamReader(fisChatCompletion);
        char[] inputBuffer = new char[0];
        char[] inputBuffer1 = new char[0];
        try {
            inputBuffer = new char[fis.available()];
            inputBuffer1 = new char[fisChatCompletion.available()];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            isr.read(inputBuffer);
            isr1.read(inputBuffer1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userID = new String(inputBuffer);
        chatCompletionId = new String(inputBuffer1);
        System.out.println(userID);
        List<BaseMessage> arrMessages = new ArrayList<BaseMessage>();
        if (!userID.equals("")) {

            final JSONArray[] messStrings = {null};

            try {
                String jsonString = "{\"user_id\":\"" + userID + "\"}";
                System.out.println(jsonString);
                httpRequest.arrResPost(jsonString, "/querydb/messageByUser", new OnResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                    @Override
                    public void onResponseArray(JSONArray response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                messStrings[0] = response;
                                if (messStrings[0].length() > 0) {
                                    for (int i = 0; i < messStrings[0].length(); ++i) {
                                        try {
                                            JSONObject obj = messStrings[0].getJSONObject(i);
                                            String role = obj.getString("role");

                                            int type;
                                            User u;
                                            if (role.equals("user")) {
                                                type = 0;
                                                u = new User(userID);
                                            } else {
                                                type = 1;
                                                u = new User("assistant");
                                            }

                                            Message mess = new Message(obj.getString("content"), Converter.convertDateTimestamp(obj.getString("createdAt")), u, type);
                                            arrMessages.add(mess);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    messageRecyclerView = findViewById(R.id.recycler_gchat);
                                    TextView emptyView = findViewById(R.id.empty_view);
                                    try {


                                        if (arrMessages.isEmpty()) {
                                            System.out.println("-------------here");
                                            emptyView.setVisibility(View.VISIBLE);
                                            messageRecyclerView.setVisibility(View.GONE);
                                        } else {

                                            messageListAdapter = new MessageListAdapter(getApplicationContext(), arrMessages);
                                            messageRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                            messageRecyclerView.setAdapter(messageListAdapter);
                                            messageRecyclerView.scrollToPosition(arrMessages.size() - 1);
                                        }
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        });
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
            //get message

            sendMessageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String meMessage = edtChatMessage.getText().toString();
                    Message newMeMessage = new Message(meMessage, System.currentTimeMillis(), new User(userID), 0);
                    arrMessages.add(newMeMessage);
                    messageRecyclerView.setAdapter(messageListAdapter);
                    edtChatMessage.setText("");
                    edtChatMessage.clearFocus();
                    messageRecyclerView.scrollToPosition(arrMessages.size() - 1);
//                    CountDownLatch cdLatch = new CountDownLatch(2);
                    final JSONArray[] resTest = {null};

                            try {
                                String jsonString = "{\"user_id\":\"" + userID + "\"," +
                                        " \n\"chat_id\":\"" + chatCompletionId + "\"," +
                                        "\n\"prompt\":\"" + meMessage + "\"}";
                                if (arrMessages.size() == 1) {
                                    httpRequest.arrResPost(jsonString, "/chatgpt/chat", new OnResponseListener() {
                                        @Override
                                        public void onResponse(JSONObject response) {

                                        }

                                        @Override
                                        public void onResponseArray(JSONArray response) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    resTest[0] = response;
                                                    try{
                                                        JSONObject messageJSON = resTest[0].getJSONObject(0);
                                                        JSONObject chatCompletionJSON = resTest[0].getJSONObject(1);

                                                        chatCompletionId = chatCompletionJSON.getString("chat_completion");
                                                        Message messageResponse = new Message(messageJSON.getString("content"), System.currentTimeMillis(), new User("assistant"), 1);
                                                        arrMessages.add(messageResponse);
                                                        messageRecyclerView.setAdapter(messageListAdapter);
                                                        messageRecyclerView.scrollToPosition(arrMessages.size() - 1);
                                                    }catch (JSONException e){
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                } else {
                                    httpRequest.arrResPost(jsonString, "/chatgpt/continueChat", new OnResponseListener() {
                                        @Override
                                        public void onResponse(JSONObject response) {

                                        }

                                        @Override
                                        public void onResponseArray(JSONArray response) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    resTest[0] = response;
                                                    try {
                                                    JSONObject messageJSON = resTest[0].getJSONObject(0);
                                                    JSONObject chatCompletionJSON = resTest[0].getJSONObject(1);

                                                        chatCompletionId = chatCompletionJSON.getString("chat_completion");
                                                        Message messageResponse = new Message(messageJSON.getString("content"), System.currentTimeMillis(), new User("assistant"), 1);
                                                        arrMessages.add(messageResponse);
                                                        messageRecyclerView.setAdapter(messageListAdapter);
                                                        messageRecyclerView.scrollToPosition(arrMessages.size() - 1);
                                                    }catch (JSONException e){
                                                        e.printStackTrace();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                }


//                                cdLatch.countDown();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                    //                        cdLatch.await();
                    messageRecyclerView.setAdapter(messageListAdapter);
                }
            });
        }
    }
}