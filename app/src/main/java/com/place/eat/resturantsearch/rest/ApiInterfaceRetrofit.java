package com.place.eat.resturantsearch.rest;

import com.place.eat.resturantsearch.model.GeneralResponseModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @author amanbansal
 * @version 1.0
 * @since 2/1/17
 */

public interface ApiInterfaceRetrofit {

    @GET("search")
    Call<GeneralResponseModel> getSearchResult(@Header(value = "user-key") String user, @QueryMap Map<String, String> queryParams);

}
