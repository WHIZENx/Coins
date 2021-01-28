package com.surrussent.coins.Modal;

import com.google.gson.annotations.SerializedName;

public class Collection {

    // This is Primary Data access object //

    // Initialize object by API object name
    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}