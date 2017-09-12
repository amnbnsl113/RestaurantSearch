package com.place.eat.resturantsearch.model.mapper;


import com.place.eat.resturantsearch.model.jsonmodel.Restaurant;
import com.place.eat.resturantsearch.model.jsonmodel.SearchResultModel;
import com.place.eat.resturantsearch.model.viewmodel.RestaurantViewModel;
import com.place.eat.resturantsearch.model.viewmodel.SearchResultViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 12/9/17.
 */

public class SearchResultMapper extends BaseMapper<SearchResultViewModel, SearchResultModel> {

    @Override
    public SearchResultViewModel toViewModel(SearchResultModel searchResultModel) {
        SearchResultViewModel searchResultViewModel = new SearchResultViewModel();
        List<RestaurantViewModel> restaurantViewModels = new ArrayList<>();


        List<Restaurant> restaurants = searchResultModel.getRestaurants();
        if (restaurants != null) {
            for (Restaurant restaurant : restaurants) {
                restaurantViewModels.add(new RestaurantViewModel());
            }
        }
        searchResultViewModel.setRestaurantList(restaurantViewModels);
        return searchResultViewModel;
    }
}
