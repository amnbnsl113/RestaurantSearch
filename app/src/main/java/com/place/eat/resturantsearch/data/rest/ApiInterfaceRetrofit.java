package com.place.eat.resturantsearch.data.rest;

import com.place.eat.resturantsearch.model.jsonmodel.SearchResultModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

/**
 * @author amanbansal
 * @version 1.0
 * @since 2/1/17
 */

public interface ApiInterfaceRetrofit {

    @GET("search")
    Call<SearchResultModel> getSearchResult(@Header(value = "user-key") String user, @QueryMap Map<String, String> queryParams);

}
