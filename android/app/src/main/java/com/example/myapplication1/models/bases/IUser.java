package com.example.myapplication1.models.bases;

public abstract class IUser {
    protected String id;
    protected String name;
    protected String email;

    public IUser(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public IUser(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}