package com.surrussent.coins.Modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    // This is Secondary Data access object //

    // Initialize object by API object name
    @SerializedName("coins")
    private List coins;

    public List getCoins() {
        return coins;
    }

    public void setCoins(List coins) {
        this.coins = coins;
    }
}