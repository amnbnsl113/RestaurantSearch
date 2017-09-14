package com.place.eat.resturantsearch.model.viewmodel;

import java.util.List;

/**
 * Created by aman on 12/9/17.
 */

public class SearchResultViewModel extends BaseViewModel {
    private int nextCount;
    private List<RestaurantViewModel> restaurantList;


    public int getNextCount() {
        return nextCount;
    }

    public void setNextCount(int nextCount) {
        this.nextCount = nextCount;
    }

    public List<RestaurantViewModel> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<RestaurantViewModel> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
