package com.surrussent.coins.API;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    // Use method GET with Path url
    @GET("coins/")
    Call<Map> getAPICoins();

}
