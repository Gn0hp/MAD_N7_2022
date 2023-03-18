package com.example.myapplication1.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
            String returnRes = res.body().string();

//            JSONObject jsonObject = new JSONObject(returnRes);

            res.close();
            return  returnRes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
    }
    public JSONObject post(String jsons, String endpoints){
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(jsons, JSON);

        Request req = new Request.Builder()
                .url(url.concat(endpoints))
                .post(body)
                .build();
        try {
            Response res = client.newCall(req).execute();
            assert res.body() != null;
            String returnRes = res.body().string();

            if(!returnRes.equals("") || returnRes != null){
                JSONObject jsonObject = new JSONObject(returnRes);

                res.close();
                return jsonObject;
            }
            return new JSONObject("");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
