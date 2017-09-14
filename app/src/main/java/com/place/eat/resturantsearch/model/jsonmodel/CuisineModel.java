
package com.place.eat.resturantsearch.model.jsonmodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CuisineModel {

    @SerializedName("cuisines")
    @Expose
    private List<Cuisine> cuisines = null;

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

}
