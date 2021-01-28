package com.surrussent.coins.Modal;

import com.google.gson.annotations.SerializedName;

public class AllTimeHigh {

    @SerializedName("price")
    private float price;
    @SerializedName("timestamp")
    private double timestamp;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }
}
