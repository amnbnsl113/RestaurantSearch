package com.place.eat.resturantsearch.data.rest;

import android.text.TextUtils;

import com.place.eat.resturantsearch.BuildConfig;
import com.place.eat.resturantsearch.model.jsonmodel.SearchResultModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * @author amanbansal
 * @version 1.0
 * @since 2/1/17
 */
public class RetrofitRequest {

    private static ApiInterfaceRetrofit requestInterface = RetrofitAdapter.getRetrofit(null).create(ApiInterfaceRetrofit.class);


    public static Call<SearchResultModel> getSearchResult(String query, String cuisineId, String count) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("q", query);
        queryParams.put("count", count);
        if (!TextUtils.isEmpty(cuisineId)) {
            queryParams.put("cuisines", cuisineId);
        }
        return requestInterface.getSearchResult(BuildConfig.API_KEY, queryParams);
    }

}
