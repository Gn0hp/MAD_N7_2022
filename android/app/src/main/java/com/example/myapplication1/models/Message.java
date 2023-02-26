package com.example.myapplication1.models;

import com.example.myapplication1.models.bases.BaseMessage;

public class Message extends BaseMessage {
    private long createdAt;

    public Message() {
        super();
    }
    public Message(String message, User sender, User receiver) {
        super(message, sender, receiver);
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
