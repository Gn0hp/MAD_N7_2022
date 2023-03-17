package com.example.myapplication1.models;

import com.example.myapplication1.models.bases.IUser;

public class User extends IUser {
    private String phoneNumber;
    private String nickName;
    private String profileUrl;
    public User(){
        super();
    }
    public User(String id, String name, String email, String phoneNumber, String nickName, String profileUrl){
        super(id,name,email);
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.profileUrl = profileUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", nickName='" + nickName + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
