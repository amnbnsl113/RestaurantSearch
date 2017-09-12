package com.place.eat.resturantsearch.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.place.eat.resturantsearch.BuildConfig;
import com.place.eat.resturantsearch.R;
import com.place.eat.resturantsearch.data.rest.RestCallback;
import com.place.eat.resturantsearch.data.rest.RetrofitRequest;
import com.place.eat.resturantsearch.model.jsonmodel.SearchResultModel;
import com.place.eat.resturantsearch.model.mapper.SearchResultMapper;
import com.place.eat.resturantsearch.model.viewmodel.SearchResultViewModel;
import com.place.eat.resturantsearch.view.adapter.RestaurantAdapter;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aman on 12/9/17.
 */

public class RestaurantListFragment extends BaseFragment {
    private String queryToSearch;
    private RestCallback<SearchResultModel> restCallback;
    private RestaurantAdapter adapter;

    @Override
    protected int getFragmentLayout() {
        return R.layout.resturant_list_frag;
    }

    @Override
    protected void initViews(View view) {
        adapter = new RestaurantAdapter(null, getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    public void onTextChange(String query) {

    }

    public void onTextSubmit(String query) {
        queryToSearch = query;
        fetchRestaurants();
    }

    void fetchRestaurants() {
        if (TextUtils.isEmpty(queryToSearch)) {
            return;
        }
        if (restCallback != null && restCallback.isExecuted()) {
            restCallback.stop();
        }

        Call<SearchResultModel> searchResultModelCall = RetrofitRequest.getSearchResult(queryToSearch);
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
        }

        @Override
        public void onFailure(Call<SearchResultModel> call, Throwable t) {

        }

        @Override
        public boolean checkForActive() {
            return active;
        }
    };

}
