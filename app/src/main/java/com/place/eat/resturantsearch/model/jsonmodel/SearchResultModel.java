
package com.place.eat.resturantsearch.model.jsonmodel;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SearchResultModel extends BaseResponseModel {

    @SerializedName("results_found")
    private String resultsFound;

    @SerializedName("results_start")
    private String resultsStart;

    @SerializedName("results_shown")
    private String resultsShown;

    @SerializedName("restaurants")
    private List<RestaurantModel> restaurants = null;


    public String getResultsFound() {
        return resultsFound;
    }

    public void setResultsFound(String resultsFound) {
        this.resultsFound = resultsFound;
    }

    public String getResultsStart() {
        return resultsStart;
    }

    public void setResultsStart(String resultsStart) {
        this.resultsStart = resultsStart;
    }

    public String getResultsShown() {
        return resultsShown;
    }

    public void setResultsShown(String resultsShown) {
        this.resultsShown = resultsShown;
    }

    public List<RestaurantModel> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantModel> restaurants) {
        this.restaurants = restaurants;
    }

}
