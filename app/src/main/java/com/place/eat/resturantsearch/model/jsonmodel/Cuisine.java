
package com.place.eat.resturantsearch.model.jsonmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;

@Parcel
public class Cuisine {

    @SerializedName("cuisine")
    @Expose
    private Cuisine_ cuisine;

    public Cuisine_ getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine_ cuisine) {
        this.cuisine = cuisine;
    }

}
