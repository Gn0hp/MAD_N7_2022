package com.example.myapplication1.models;

import com.example.myapplication1.models.bases.BaseMessage;

public class Message extends BaseMessage {


    public Message() {
        super();
    }
    public Message(String message, User sender, User receiver) {
        super(message, sender, receiver);
    }

     public JSONObject toJson(){
        Map<String, String> res= new HashMap<>();
        res.put("id", this.id);
        res.put("message", this.name);
        res.put("sender", this.email);
        res.put("receiver", this.phoneNumber);
        res.put("createdAt", this.createdAt);
        res.put("updatedAt", this.updatedAt);
        return new JSONObject(res);
    }
    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
