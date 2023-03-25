package com.example.myapplication1.models;

import com.example.myapplication1.models.bases.BaseMessage;

import org.json.JSONObject;
import java.util.Map;
import java.util.HashMap;
public class Message extends BaseMessage {
    private int type;

    public Message() {
        super();
    }
    public Message(String message, User sender, User receiver) {
        super(message, sender, receiver);
    }
    public Message(String message, Long createdAt, User u, int type){
        super(message, createdAt, u);
        this.type = type;
    }
    public void setType(int type){
        this.type= type;
    }
    public int getType(){
        return this.type;
    }
     public JSONObject toJson(){
        Map<String, String> res= new HashMap<>();
        res.put("id", this.id);
        res.put("message", this.message);
        res.put("sender", this.sender.getId());
        res.put("receiver", this.receiver.getId());
        res.put("createdAt", Long.toString(this.getCreatedAt()));
        res.put("updatedAt", Long.toString(this.getUpdatedAt()));
        return new JSONObject(res);
    }
    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
