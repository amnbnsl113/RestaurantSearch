package com.place.eat.resturantsearch.view.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.place.eat.resturantsearch.R;
import com.place.eat.resturantsearch.data.rest.RestCallback;
import com.place.eat.resturantsearch.data.rest.RetrofitRequest;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine_;
import com.place.eat.resturantsearch.model.jsonmodel.SearchResultModel;
import com.place.eat.resturantsearch.model.mapper.SearchResultMapper;
import com.place.eat.resturantsearch.model.viewmodel.SearchResultViewModel;
import com.place.eat.resturantsearch.view.activity.MainActivity;
import com.place.eat.resturantsearch.view.adapter.RestaurantAdapter;

import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aman on 12/9/17.
 */

public class RestaurantListFragment extends BaseFragment {
    private String queryToSearch;
    private RestCallback<SearchResultModel> restCallback;
    private RestaurantAdapter adapter;
    private Integer cuisineId = null;


    public static RestaurantListFragment getInstance(Cuisine_ cuisine) {

        RestaurantListFragment restaurantListFragment = new RestaurantListFragment();
        if (cuisine != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("cuisine", Parcels.wrap(cuisine));
            restaurantListFragment.setArguments(bundle);
        }
        return restaurantListFragment;
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.resturant_list_frag;
    }

    @Override
    protected void initViews(View view) {
        Parcelable parcelable = getArguments().getParcelable("cuisine");
        if (parcelable != null) {
            Cuisine_ cuisine = Parcels.unwrap(parcelable);
            if (cuisine != null) {
                cuisineId = cuisine.getCuisineId();
            }
        }

        adapter = new RestaurantAdapter(null, getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onResume() {
        super.onResume();

        String query = "";
        if (getActivity() instanceof MainActivity) {
            query = ((MainActivity) getActivity()).getSearchViewText();
        }
        onTextSubmit(query);
    }

    public void onTextSubmit(String query) {
        queryToSearch = query;
        fetchRestaurants();
    }

    void fetchRestaurants() {
        if (restCallback != null && restCallback.isExecuted()) {
            restCallback.stop();
        }

        adapter.clearItems();
        showProgress();

        Call<SearchResultModel> searchResultModelCall = RetrofitRequest.getSearchResult(queryToSearch, String.valueOf(cuisineId), "9");
        restCallback = new RestCallback<>(getActivity(), searchResultModelCall, callback);
        restCallback.executeRequest();
    }


    private void populateUi(SearchResultViewModel searchResultViewModel) {
        if (searchResultViewModel != null)
            adapter.swapItems(searchResultViewModel.getRestaurantList());
    }


    private RestCallback.RestCallbacks<SearchResultModel> callback = new RestCallback.RestCallbacks<SearchResultModel>() {
        @Override
        public void onResponse(Call<SearchResultModel> call, Response<SearchResultModel> response) {
            SearchResultViewModel searchResultViewModel = new SearchResultMapper().toViewModel(response.body());
            populateUi(searchResultViewModel);
            hideProgress();
        }

        @Override
        public void onFailure(Call<SearchResultModel> call, Throwable t) {
            hideProgress();
        }

        @Override
        public boolean checkForActive() {
            // to check if fragment is still visible
            return active;
        }
    };


}
