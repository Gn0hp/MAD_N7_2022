package com.example.myapplication1.models.bases;

import com.example.myapplication1.models.User;

public abstract class BaseMessage {
    protected String message;
    protected User sender;
    protected User receiver;

    public BaseMessage(String message, User sender, User receiver){
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }
    public BaseMessage(){}
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



}
