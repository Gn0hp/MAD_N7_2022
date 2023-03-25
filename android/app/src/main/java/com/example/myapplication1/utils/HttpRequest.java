package com.example.myapplication1.utils;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequest {
    private OkHttpClient client;
    private String url;

    public HttpRequest(String url) {
        client = new OkHttpClient();
        this.url = url;
    }

    public String get(String params) {
        String Url = url.concat(params);
        System.out.println(Url);
        Request req = new Request.Builder()
                .url(Url)
                .build();

        try {
            Response res = client.newCall(req).execute();
            if (res.code() == 200 && res.body() != null) {
                String returnRes = res.body().string();

                //            JSONObject jsonObject = new JSONObject(returnRes);

                res.close();
                return returnRes;
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public JSONObject post(String jsons, String endpoints) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(jsons, JSON);

        Request req = new Request.Builder()
                .url(url.concat(endpoints))
                .post(body)
                .build();
        try {
            Response res = client.newCall(req).execute();
            if (res.code() == 200 && res.body() != null) {
                String resBody = res.body().string();
                if (!resBody.trim().isEmpty()) {
                    JSONObject jsonObject;
                    try {
                        jsonObject = new JSONObject(resBody);
                    } catch (JSONException e) {
                        jsonObject = null;
                    }
                    return jsonObject;
                }
                return null;
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public JSONArray arrResPost(String jsons, String endpoints) throws IOException {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(jsons, JSON);

        Request req = new Request.Builder()
                .url(url.concat(endpoints))
                .post(body)
                .build();
        final JSONArray[] jsonArray = {null};
        Response res = client.newCall(req).execute();
        if (res.isSuccessful() && res.code() == 200 && res.body() != null) {
            try {
                String json = res.body().string();
                jsonArray[0] = new JSONArray(json);
                System.out.println("Response length: "+ jsonArray[0].length());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonArray[0];
    }
}
