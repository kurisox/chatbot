package org.aiconnection;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.ResponseEntitity.ResponseEntitity;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AIConnector {
    private static AIConnector instance;
    private final String API_ENDPOINT = "http://localhost:8000/v1/chat/completions";
    private final String content_type = "Content-Type";
    private final String json = "application/json";
    private final int timeout = 1200;
    private final OkHttpClient httpClient;
    private ObjectMapper objectMapper;

    private AIConnector() {
        this.objectMapper = new ObjectMapper();
        this.httpClient = new OkHttpClient.Builder()
                .readTimeout(this.timeout, TimeUnit.SECONDS)
                .build();
    }

    public static AIConnector getInstance() {
        if (instance == null) {
            instance = new AIConnector();
        }
        return instance;
    }

    public ResponseEntitity callAPI(String content) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), content);
             
        Request request = new Request.Builder()
                .url(API_ENDPOINT).addHeader(API_ENDPOINT, API_ENDPOINT)
                .header(content_type,json)
                .post(requestBody)
                .build();
        Call call = this.httpClient.newCall(request);
        try {
            Response response = call.execute();
            if (response.code() == 200) {
                ResponseBody responseBody = response.body();
                ResponseEntitity responseEntitity = this.objectMapper.readValue(responseBody.string(), ResponseEntitity.class);
                return responseEntitity;
            }
        } catch (IOException e) {
            System.out.println("Irgendwas ist schief gelaufen!");
            e.printStackTrace();
        }
        return null;
    }
}
