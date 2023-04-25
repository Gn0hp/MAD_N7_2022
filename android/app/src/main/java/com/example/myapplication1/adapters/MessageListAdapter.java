package com.example.myapplication1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;
import com.example.myapplication1.models.Message;
import com.example.myapplication1.models.bases.BaseMessage;

import java.util.Date;
import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private Context context;
    private List<BaseMessage> messageList;

    public MessageListAdapter(Context ctx, List<BaseMessage> listMessage){
        this.context=ctx;
        this.messageList = listMessage;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("creating ./////.....");
        View view;
        if(viewType == VIEW_TYPE_MESSAGE_SENT){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_me, parent, false);
            return new SentMessageHolder(view);
        }
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_other, parent, false);
            return new ReceivedMessageHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        System.out.println("bingding ./////.....");
        Message message = (Message) messageList.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
    @Override
    public int getItemViewType(int position){
        Message message = (Message) messageList.get(position);
        if(message.getType() == 0){
            return VIEW_TYPE_MESSAGE_SENT;
        }
        return VIEW_TYPE_MESSAGE_RECEIVED;
    }

    private static class ReceivedMessageHolder extends RecyclerView.ViewHolder{
        TextView messageText;
        TextView timeText;
        TextView nameText;
        ImageView profileImage;
        ReceivedMessageHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_gchat_message_other);
            timeText = itemView.findViewById(R.id.text_gchat_timestamp_other);
            nameText = itemView.findViewById(R.id.text_gchat_user_other);
            profileImage = itemView.findViewById(R.id.image_gchat_profile_other);
        }
        void bind(Message message){
            messageText.setText(message.getMessage());
            Date date = new Date(message.getCreatedAt());
            String hour = Integer.toString(date.getHours());
            String minute = Integer.toString(date.getMinutes());
            while(hour.length()<2){
                hour = "0"+hour;
            }
            while(minute.length()<2){
                minute = "0" +minute;
            }
            String toText = hour+":"+minute;
            System.out.println(toText);
            timeText.setText(toText);

        }
    }
    private static class SentMessageHolder extends RecyclerView.ViewHolder{
        TextView messageText;
        TextView timeText;
        SentMessageHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_gchat_message_me);
            timeText = itemView.findViewById(R.id.text_gchat_timestamp_me);

        }
        void bind(Message message){
            messageText.setText(message.getMessage());
            Date date = new Date(message.getCreatedAt());
            String hour = Integer.toString(date.getHours());
            String minute = Integer.toString(date.getMinutes());
            while(hour.length()<2){
                hour = "0"+hour;
            }
            while(minute.length()<2){
                minute = "0" +minute;
            }
            String toText = hour+":"+minute;
            System.out.println(toText);
            timeText.setText(toText);

        }
    }
}
