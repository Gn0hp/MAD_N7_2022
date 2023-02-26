package com.example.myapplication1.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.models.bases.BaseMessage;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<BaseMessage> messageList;

    public MessageListAdapter(Context ctx, List<BaseMessage> listMessage){
        this.context=ctx;
        this.messageList = listMessage;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
