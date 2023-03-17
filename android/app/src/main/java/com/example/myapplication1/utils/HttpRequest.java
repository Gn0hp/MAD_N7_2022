package com.example.myapplication1.utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequest {
    private OkHttpClient client;
    private String url;

    public HttpRequest(String url){
        client = new OkHttpClient();
        this.url = url;
    }
    public String get(String params){
        String Url = url.concat(params);
        System.out.println(Url);
        Request req = new Request.Builder()
                .url(Url)
                .build();

        try{
            Response res = client.newCall(req).execute();
            assert res.body() != null;
            return res.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String post(){
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        RequestBody body = RequestBody.create("", JSON);

        Request req = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response res = client.newCall(req).execute();
            assert res.body() != null;
            return res.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
