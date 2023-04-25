package com.example.myapplication1.utils.request;

public class UserRequest {
    private String name;
    private String username;
    private String email;
    private String password;
    private String profileURL;
    private String phoneNumber;

    public UserRequest(String name, String username, String email, String password, String profileURL, String phoneNumber) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profileURL = profileURL;
        this.phoneNumber = phoneNumber;
    }

    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserRequest(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
