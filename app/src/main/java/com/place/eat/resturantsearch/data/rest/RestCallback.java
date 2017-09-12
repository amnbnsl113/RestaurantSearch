package com.place.eat.resturantsearch.data.rest;

import android.content.Context;

import com.place.eat.resturantsearch.model.jsonmodel.BaseResponseModel;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author amanbansal
 * @version 1.0
 * @since 2/1/17
 */
public class RestCallback<T extends BaseResponseModel> implements Callback<T> {

    private Call<T> mCall;

    public interface RestCallbacks<T> {
        void onResponse(Call<T> call, retrofit2.Response<T> response);

        void onFailure(Call<T> call, Throwable t);
    }

    private RestCallbacks<T> mCallbacks;

    public RestCallback(Context context, Call<T> call, RestCallbacks<T> callbacks) {
        mCall = call;
        mCallbacks = callbacks;
    }

    @Override
    public void onResponse(Call<T> call, retrofit2.Response<T> response) {
        try {
//            Log.e("API Response==:", response.body().toString());
            mCallbacks.onResponse(call, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        mCallbacks.onFailure(call, t);
    }

    public void executeRequest() {
        mCall.enqueue(this);
    }
}
