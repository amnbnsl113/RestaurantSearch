package com.place.eat.resturantsearch.view.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.place.eat.resturantsearch.R;
import com.place.eat.resturantsearch.data.rest.RestCallback;
import com.place.eat.resturantsearch.data.rest.RetrofitRequest;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine_;
import com.place.eat.resturantsearch.model.jsonmodel.SearchResultModel;
import com.place.eat.resturantsearch.model.mapper.SearchResultMapper;
import com.place.eat.resturantsearch.model.viewmodel.SearchResultViewModel;
import com.place.eat.resturantsearch.view.activity.MainActivity;
import com.place.eat.resturantsearch.view.adapter.CuisineRestaurantAdapter;

import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aman on 12/9/17.
 */

public class RestaurantListFragment extends BaseFragment {
    private String queryToSearch;
    private RestCallback<SearchResultModel> restCallback;
    private CuisineRestaurantAdapter adapter;
    private Integer cuisineId = null;
    private int start;
    private LinearLayoutManager manager;
    private boolean isLoading;
    private static final int visibleThreshold = 4;
    private TextView errorText;
    private String cuisineName;
    private String sortBy;
    private String sortOrder;


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
                cuisineName = cuisine.getCuisineName();
            }
        }
        errorText = view.findViewById(R.id.errorText);
        adapter = new CuisineRestaurantAdapter(null, getActivity(), false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(listener);
        recyclerView.setLayoutManager(manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        (getActivity()).setTitle(cuisineName);

        String query = "";
        if (getActivity() instanceof MainActivity) {
            query = ((MainActivity) getActivity()).getSearchViewText();
            ((MainActivity) getActivity()).setSortIconVisibility(true);

        }
        onTextSubmit(query);
    }

    public void onSort(String sortBy, String sortOrder) {
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
        start = 0;
        fetchRestaurants();

    }


    public void onTextSubmit(String query) {
        queryToSearch = query;
        start = 0;
        fetchRestaurants();
    }

    void fetchRestaurants() {
        if (restCallback != null && restCallback.isExecuted()) {
            restCallback.stop();
        }
        if (start == 0) {
            adapter.clearItems();
            showProgress();
        }
        isLoading = true;
        Call<SearchResultModel> searchResultModelCall = RetrofitRequest.getSearchResult(queryToSearch, String.valueOf(cuisineId), "9", start, sortBy, sortOrder);
        restCallback = new RestCallback<>(getActivity(), searchResultModelCall, callback);
        restCallback.executeRequest();
    }


    private void populateUi(SearchResultViewModel searchResultViewModel) {
        if (searchResultViewModel != null) {
            adapter.addItems(searchResultViewModel.getRestaurantList());
        }
        if (adapter.getItemCount() == 0) {
            showMessage();
        } else {
            hideMessage();
        }
    }

    private void showMessage() {
        errorText.setVisibility(View.VISIBLE);
    }

    private void hideMessage() {
        errorText.setVisibility(View.GONE);
    }

    private void detectForNextCall() {

        int totalItemCount = manager.getItemCount();
        int lastVisibleItem = manager.findLastVisibleItemPosition();

        if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
            fetchRestaurants();
        }
    }

    private RestCallback.RestCallbacks<SearchResultModel> callback = new RestCallback.RestCallbacks<SearchResultModel>() {
        @Override
        public void onResponse(Call<SearchResultModel> call, Response<SearchResultModel> response) {
            isLoading = false;
            SearchResultViewModel searchResultViewModel = new SearchResultMapper().toViewModel(response.body());
            start = searchResultViewModel.getNextCount();
            populateUi(searchResultViewModel);
            hideProgress();
        }

        @Override
        public void onFailure(Call<SearchResultModel> call, Throwable t) {
            isLoading = false;
            hideProgress();
        }

        @Override
        public boolean checkForActive() {
            // to check if fragment is still visible
            return active;
        }
    };

    private RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            detectForNextCall();
        }
    };

}
