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
import com.example.myapplication1.models.bases.BaseMessage;
import com.example.myapplication1.utils.HttpRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {
    RecyclerView messageRecyclerView;
    MessageListAdapter messageListAdapter;
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


        messageRecyclerView= (RecyclerView)findViewById(R.id.recycler_gchat);

        System.out.println("-------"+ messageRecyclerView);

        //get message
        List<BaseMessage> arr = new ArrayList<BaseMessage>();
        messageListAdapter = new MessageListAdapter(this, arr);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRecyclerView.setAdapter(messageListAdapter);
        HttpRequest httpRequest = new HttpRequest("http://10.0.2.2:3124");
        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] resTest = {""};
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            resTest[0] = httpRequest.get("");

                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                try {
                    Thread.sleep(500);
                    System.out.println(resTest[0]);
                    Toast.makeText(ChatActivity.this, resTest[0], Toast.LENGTH_SHORT).show();
                    thread.destroy();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}