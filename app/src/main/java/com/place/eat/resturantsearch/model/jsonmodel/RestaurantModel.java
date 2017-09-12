package com.place.eat.resturantsearch.model.jsonmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aman on 12/9/17.
 */

public class RestaurantModel {

    @SerializedName("restaurant")
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
