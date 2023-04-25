package com.example.myapplication1.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public interface OnResponseListener {
    void onResponse(JSONObject response);
    void onResponseArray(JSONArray response);
//    void onResponseString(String response);
}
