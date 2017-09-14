package com.place.eat.resturantsearch.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.place.eat.resturantsearch.R;
import com.place.eat.resturantsearch.data.rest.RestCallback;
import com.place.eat.resturantsearch.data.rest.RetrofitRequest;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine_;
import com.place.eat.resturantsearch.model.jsonmodel.SearchResultModel;
import com.place.eat.resturantsearch.model.mapper.SearchResultMapper;
import com.place.eat.resturantsearch.model.viewmodel.RestaurantViewModel;
import com.place.eat.resturantsearch.model.viewmodel.SearchResultViewModel;
import com.place.eat.resturantsearch.util.FragmentChange;
import com.place.eat.resturantsearch.util.SearchQueryChanged;
import com.place.eat.resturantsearch.view.adapter.CuisineRestaurantAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aman on 12/9/17.
 */

public class RestaurantWidget extends LinearLayout implements SearchQueryChanged {
    private CuisineRestaurantAdapter adapter;
    private Cuisine_ cuisine;
    private String queryToSearch;
    private RestCallback<SearchResultModel> restCallback;
    private ProgressBar progressBar;


    public RestaurantWidget(Context context, Cuisine_ cuisine) {
        super(context);
        init(context, cuisine);
        this.cuisine = cuisine;
    }


    public RestaurantWidget(Context context, @Nullable AttributeSet attrs, Cuisine_ cuisine) {
        super(context, attrs);
        init(context, cuisine);
        this.cuisine = cuisine;
    }

    private void init(Context context, Cuisine_ cuisine) {
        inflate(context, R.layout.restaurant_widget, this);
        TextView titleText = findViewById(R.id.titleText);
        titleText.setText(cuisine.getCuisineName());

        progressBar = findViewById(R.id.progressBar);
        RecyclerView recyclerView = findViewById(R.id.restaurantList);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new CuisineRestaurantAdapter(null, context, true);
        recyclerView.setAdapter(adapter);

        Button viewMoreButton = findViewById(R.id.viewMoreButton);
        viewMoreButton.setOnClickListener(viewMoreClick);
        setVisibility(GONE);
    }


    public void setValues(List<RestaurantViewModel> restaurantViewModelList) {
        adapter.swapItems(restaurantViewModelList);
    }


    void fetchRestaurants() {
        if (restCallback != null && restCallback.isExecuted()) {
            restCallback.stop();
        }

        adapter.clearItems();
        showProgress();

        Call<SearchResultModel> searchResultModelCall = RetrofitRequest.getSearchResult(queryToSearch, String.valueOf(cuisine.getCuisineId()), "3");
        restCallback = new RestCallback<>(getContext(), searchResultModelCall, callback);
        restCallback.executeRequest();
    }

    private void showProgress() {
        progressBar.setVisibility(VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(GONE);
    }


    @Override
    public void onChange(String newQuery) {
        queryToSearch = newQuery;
        fetchRestaurants();
        setVisibility(VISIBLE);
    }

    private RestCallback.RestCallbacks<SearchResultModel> callback = new RestCallback.RestCallbacks<SearchResultModel>() {
        @Override
        public void onResponse(Call<SearchResultModel> call, Response<SearchResultModel> response) {
            SearchResultViewModel searchResultViewModel = new SearchResultMapper().toViewModel(response.body());
            if (searchResultViewModel == null || searchResultViewModel.getRestaurantList() == null || searchResultViewModel.getRestaurantList().isEmpty()) {
                setVisibility(GONE);
            } else {
                setVisibility(VISIBLE);
                setValues(searchResultViewModel.getRestaurantList());
            }
            hideProgress();
        }

        @Override
        public void onFailure(Call<SearchResultModel> call, Throwable t) {
            hideProgress();
        }

        @Override
        public boolean checkForActive() {
            return true;
        }
    };

    private OnClickListener viewMoreClick = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (getContext() instanceof FragmentChange) {
                ((FragmentChange) getContext()).restaurantListFragment(cuisine);
            }
        }
    };

}
