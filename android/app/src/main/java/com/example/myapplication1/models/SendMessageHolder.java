//package com.example.myapplication1.models;
//
//
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//public class SentMessageHolder extends RecyclerView.ViewHolder {
//    TextView messageText, timeText;
//
//    SentMessageHolder(View itemView) {
//        super(itemView);
//
//        messageText = (TextView) itemView.findViewById(R.id.text_gchat_message_me);
//        timeText = (TextView) itemView.findViewById(R.id.text_gchat_timestamp_me);
//    }
//
//    void bind(Message message) {
//        messageText.setText(message.getMessage());
//
//        // Format the stored timestamp into a readable String using method.
//        timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
//    }
//}