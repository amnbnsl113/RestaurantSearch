package com.place.eat.resturantsearch.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.place.eat.resturantsearch.R;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine;
import com.place.eat.resturantsearch.model.jsonmodel.CuisineModel;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine_;
import com.place.eat.resturantsearch.util.FragmentChange;
import com.place.eat.resturantsearch.util.SearchQueryChanged;
import com.place.eat.resturantsearch.view.RestaurantWidget;
import com.place.eat.resturantsearch.view.activity.MainActivity;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 12/9/17.
 */

public class CuisineCarListFragment extends BaseFragment {
    private LinearLayout baseContainer;
    private List<SearchQueryChanged> searchQueryChangedList = new ArrayList<>();

    public static CuisineCarListFragment getInstance(ArrayList<Parcelable> cuisines) {
        CuisineCarListFragment cuisineCarListFragment = new CuisineCarListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("cuisines", cuisines);
        cuisineCarListFragment.setArguments(bundle);
        return cuisineCarListFragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.cuisine_list_frag;
    }

    @Override
    protected void initViews(View view) {
        baseContainer = view.findViewById(R.id.base_container);

        ArrayList<Parcelable> parcelableList = getArguments().getParcelableArrayList("cuisines");
        if (parcelableList != null) {
            for (Parcelable parcelable : parcelableList) {
                Cuisine cuisine = Parcels.unwrap(parcelable);
                Cuisine_ cuisine_ = cuisine.getCuisine();
                if (cuisine_ != null) {
                    addCard(cuisine_);
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        String query = "";
        getActivity().setTitle("Restaurants");

        if (getActivity() instanceof MainActivity) {
            query = ((MainActivity) getActivity()).getSearchViewText();
        }
        onTextSubmit(query);
    }

    private void addCard(Cuisine_ cuisine) {
        RestaurantWidget restaurantWidget = new RestaurantWidget(getActivity(), cuisine);
        searchQueryChangedList.add(restaurantWidget);
        baseContainer.addView(restaurantWidget);
    }

    public void onTextSubmit(String query) {
        for (SearchQueryChanged searchQueryChanged : searchQueryChangedList) {
            searchQueryChanged.onChange(query);
        }
    }


}
