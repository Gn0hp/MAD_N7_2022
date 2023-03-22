package com.example.myapplication1.models.bases;

import com.example.myapplication1.models.User;

public abstract class BaseMessage {
    protected String id;
    protected String message;
    protected User sender;
    protected User receiver;
    private long createdAt;
    private long updatedAt;

    public BaseMessage(String message, User sender, User receiver){
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }
    public BaseMessage(){}
    public BaseMessage(String id, String message, User sender, User receiver){
        this.id = id;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public long getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
    public long getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
