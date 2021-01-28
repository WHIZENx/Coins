package com.surrussent.coins.API;

import com.surrussent.coins.Modal.Collection;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("coins/")
    Call<Collection> getAPICoins();

}
