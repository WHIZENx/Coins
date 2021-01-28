package com.surrussent.coins.Modal;

import com.google.gson.annotations.SerializedName;

public class Collection {

    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}