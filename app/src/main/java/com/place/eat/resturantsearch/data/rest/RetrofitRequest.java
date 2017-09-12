package com.place.eat.resturantsearch.data.rest;

import com.place.eat.resturantsearch.model.jsonmodel.SearchResultModel;

import java.util.Map;

import retrofit2.Call;

/**
 * @author amanbansal
 * @version 1.0
 * @since 2/1/17
 */
public class RetrofitRequest {

    private static ApiInterfaceRetrofit requestInterface = RetrofitAdapter.getRetrofit(null).create(ApiInterfaceRetrofit.class);


    public static Call<SearchResultModel> getSearchResult(String userKey, Map<String, String> queryParams) {
        return requestInterface.getSearchResult(userKey, queryParams);
    }

}
