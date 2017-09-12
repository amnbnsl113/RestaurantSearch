package com.place.eat.resturantsearch.model.jsonmodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by aman on 12/9/17.
 */

public class BaseResponseModel implements Serializable {

    @SerializedName("message")
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
