package com.example.myapplication1.models;

import android.content.Context;

import com.example.myapplication1.models.bases.IUser;
import com.example.myapplication1.utils.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class User extends IUser {
    private String phoneNumber;
    private String nickName;
    private String profileUrl;
    private String username;
    private String password;
    public User(){
        super();
    }
    public User(String id, String name, String email, String phoneNumber, String nickName, String profileUrl, String username, String password){
        super(id,name,email);
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
        this.profileUrl = profileUrl;
        this.username = username;
        this.password = password;
    }
    public User(String username, String password){
        this.username = username;
        this.password= password;
    }
    public User(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password= password;
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

    public User authenLogin(String json){
        HttpRequest httpRequest = new HttpRequest("http://10.0.2.2:3124");
        final JSONObject[] jsonRes = new JSONObject[1];
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                 jsonRes[0] = httpRequest.post(json,"/register/login");

            }
        });
        thread.start();
        try {
            Thread.sleep(500);
            if(jsonRes[0] == null){
                return null;
            }

            User u = new User(
                    (String) jsonRes[0].get("_id"),
                    (String) jsonRes[0].get("name"),
                    (String) jsonRes[0].get("email"),
                    (String) jsonRes[0].get("phoneNumber"),
                    (String) jsonRes[0].get("username"),
                    (String) jsonRes[0].get("profileURL"),
                    (String) jsonRes[0].get("username"),
                    (String) jsonRes[0].get("password")

            );
            return u;

        } catch (InterruptedException | JSONException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean registerNewUser(String json){
        HttpRequest httpRequest = new HttpRequest("http://10.0.2.2:3124");
        final JSONObject[] jsonRes = new JSONObject[1];
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                jsonRes[0] = httpRequest.post(json,"/register/signup");

            }
        });
        thread.start();
        try {
            Thread.sleep(500);
            if(jsonRes[0] == null){
                return false;
            }
            String resResult = jsonRes[0].get("response").toString();
            return resResult.equals("true");


        } catch (InterruptedException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public JSONObject toJson(){
        Map<String, String> res= new HashMap<>();
        res.put("id", this.id);
        res.put("name", this.name);
        res.put("email", this.email);
        res.put("phone_number", this.phoneNumber);
        res.put("profile_url", this.profileUrl);
        res.put("nick_name", this.nickName);
        res.put("username", this.username);
        res.put("password", this.password);
        return new JSONObject(res);
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
