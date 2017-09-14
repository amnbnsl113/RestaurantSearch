package com.place.eat.resturantsearch.model.mapper;


import com.place.eat.resturantsearch.model.jsonmodel.Restaurant;
import com.place.eat.resturantsearch.model.jsonmodel.RestaurantModel;
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

        if (searchResultModel != null) {
            searchResultViewModel.setNextCount(getNextCount(searchResultModel));

            List<RestaurantModel> restaurants = searchResultModel.getRestaurants();
            if (restaurants != null) {
                for (RestaurantModel restaurant : restaurants) {
                    RestaurantViewModel restaurantViewModel = new RestaurantViewModel();
                    restaurantViewModel.setId(restaurant.getRestaurant().getId());
                    restaurantViewModel.setName(restaurant.getRestaurant().getName());
                    restaurantViewModel.setThumpUrl(restaurant.getRestaurant().getThumb());
                    restaurantViewModel.setPriceForTwo(restaurant.getRestaurant().getCurrency() + " " + restaurant.getRestaurant().getAverageCostForTwo());
                    restaurantViewModels.add(restaurantViewModel);
                }
            }
        }
        searchResultViewModel.setRestaurantList(restaurantViewModels);
        return searchResultViewModel;
    }

    private int getNextCount(SearchResultModel searchResultModel) {
        try {
            return Integer.parseInt(searchResultModel.getResultsStart()) + Integer.parseInt(searchResultModel.getResultsShown());
        } catch (Exception e) {
            return 0;
        }
    }
}
