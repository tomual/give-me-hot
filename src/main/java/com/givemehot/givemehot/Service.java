package com.givemehot.givemehot;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

@org.springframework.stereotype.Service
public class Service {

    private OkHttpClient client = new OkHttpClient();
    Response response;

    public JSONObject getHot() {
        JSONObject json = new JSONObject();
        Request request = new Request.Builder()
                .url("https://www.reddit.com/r/all/hot/.json?count=20")
                .build();

        try {
            response = client.newCall(request).execute();
            json = new JSONObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
