//package com.example.myapplication1.models;
//
//import android.text.format.DateUtils;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.myapplication1.R;
//
//
//public class ReceivedMessageHolder extends RecyclerView.ViewHolder {
//    TextView messageText, timeText, nameText;
//    ImageView profileImage;
//
//    ReceivedMessageHolder(View itemView) {
//        super(itemView);
//        messageText = (TextView) itemView.findViewById(R.id.text_gchat_user_other);
//        timeText = (TextView) itemView.findViewById(R.id.text_gchat_timestamp_other);
//        nameText = (TextView) itemView.findViewById(R.id.text_gchat_message_other);
//        profileImage = (ImageView) itemView.findViewById(R.id.image_gchat_profile_other);
//    }
//
//    void bind(Message message) {
//        messageText.setText(message.getMessage());
//
//        // Format the stored timestamp into a readable String using method.
//        timeText.setText(formatDateTime(message.getCreatedAt()));
//
//        nameText.setText(message.getSender().getNickName());
//
//        // Insert the profile image from the URL into the ImageView.
//        Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
//    }
//}