package com.example.myapplication1.utils;

import com.example.myapplication1.models.User;
import com.example.myapplication1.utils.request.UserRequest;
import com.example.myapplication1.utils.response.RegisterResponse;
import com.example.myapplication1.utils.response.UserLoginResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

// register/login
// register/signup
// querydb/messageByUser
// chatgpt/chat
// chatgpt/continueChat
public interface ChatService {

    @POST("register/login")
    Call<UserLoginResponse> authenLogin(@Body UserRequest user);


    @POST("register/signup")
    Call<RegisterResponse> authenSignup(@Body User user);


    @POST("querydb/mesageByUser")
    Call<ArrayList<Integer>> queryMessage(@Body User user);


    @POST("chatgpt/chat")
    Call<ArrayList<Integer>> gptChat(@Body User user);


    @POST("chatgpt/continueChat")
    Call<ArrayList<Integer>> gptContinueChat(@Body User user);
}
