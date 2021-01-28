package com.surrussent.coins.Modal;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("coins")
    private List coins;

    public List getCoins() {
        return coins;
    }

    public void setCoins(List coins) {
        this.coins = coins;
    }
}