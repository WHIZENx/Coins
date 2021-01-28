package com.surrussent.coins.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private static HttpManager instances;

    public static HttpManager getInstance() {
        if (instances == null)
            instances = new HttpManager();
        return instances;
    }
    private final ApiService service;

    private HttpManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.coinranking.com/v1/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }

    public  ApiService getService() {
        return service;
    }
}