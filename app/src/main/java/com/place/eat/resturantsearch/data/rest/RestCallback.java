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

        boolean checkForActive();
    }

    private RestCallbacks<T> mCallbacks;

    public RestCallback(Context context, Call<T> call, RestCallbacks<T> callbacks) {
        mCall = call;
        mCallbacks = callbacks;
    }

    @Override
    public void onResponse(Call<T> call, retrofit2.Response<T> response) {
        try {
            if (mCallbacks.checkForActive()) {
                mCallbacks.onResponse(call, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isExecuted() {
        return mCall != null && mCall.isExecuted();
    }

    public void stop() {
        mCall.cancel();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (mCallbacks.checkForActive()) {
            mCallbacks.onFailure(call, t);
        }
    }

    public void executeRequest() {
        mCall.enqueue(this);
    }
}
