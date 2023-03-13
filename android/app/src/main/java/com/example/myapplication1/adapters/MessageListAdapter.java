package com.example.myapplication1.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;
import com.example.myapplication1.models.bases.BaseMessage;

import org.w3c.dom.Text;

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

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder{
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
        void bind(){}
    }
    private class SentMessageHolder extends RecyclerView.ViewHolder{
        TextView messageText;
        TextView timeText;
        SentMessageHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_gchat_message_me);
            timeText = itemView.findViewById(R.id.text_gchat_timestamp_me);

        }
        void bind(){}
    }
}
