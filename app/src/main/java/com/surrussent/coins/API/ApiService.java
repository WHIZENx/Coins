package com.surrussent.coins.API;

import com.surrussent.coins.Modal.Collection;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    // Use method GET with Path url
    @GET("coins/")
    Call<Collection> getAPICoins();

}
