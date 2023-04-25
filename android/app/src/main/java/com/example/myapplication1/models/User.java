package com.example.myapplication1.models;

import android.content.Context;


import com.example.myapplication1.models.bases.IUser;
import com.example.myapplication1.utils.ApiClient;
import com.example.myapplication1.utils.ChatService;
import com.example.myapplication1.utils.HttpRequest;
import com.example.myapplication1.utils.OnResponseListener;
import com.example.myapplication1.utils.request.UserRequest;
import com.example.myapplication1.utils.response.UserLoginResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public User(String email, String username, String password, String phoneNumber){
        this.email = email;
        this.username = username;
        this.password= password;
        this.phoneNumber = phoneNumber;
    }
    public User(String id){
        super(id);
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

    public static Map<User,String> authenLogin(UserRequest user, Context context){
        ChatService chatService = ApiClient.createService(ChatService.class, context);
        Call<UserLoginResponse> call = chatService.authenLogin(user);
        final User[] u = new User[1];
        final String[] chatCompletionId = new String[1];
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                UserLoginResponse userLogin = response.body();
                u[0] = new User(userLogin.getId(), userLogin.getName(), userLogin.getEmail(), userLogin.getPhoneNumber(), userLogin.getUsername(), userLogin.getProfileURL(), userLogin.getUsername(), userLogin.getPassword());

                chatCompletionId[0] = userLogin.getChatCompletion();
            }
            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {

            }
        });

        try {
            Map<User, String> map  = new HashMap<>();
            map.put(u[0], chatCompletionId[0]);
            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean registerNewUser(String json){
        HttpRequest httpRequest = new HttpRequest("http://10.0.2.2:3124");
        final JSONObject[] jsonRes = new JSONObject[1];

        httpRequest.post(json, "/register/signup", new OnResponseListener() {
            @Override
            public void onResponse(JSONObject response) {
                jsonRes[0] = response;
            }

            @Override
            public void onResponseArray(JSONArray response) {

            }
        });
        try {
            if(jsonRes[0] == null){
                return false;
            }
            String resResult = jsonRes[0].get("response").toString();
            return resResult.equals("true");


        } catch ( JSONException e) {
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
                ", password='" + password + '\'' +

                '}';
    }
}
