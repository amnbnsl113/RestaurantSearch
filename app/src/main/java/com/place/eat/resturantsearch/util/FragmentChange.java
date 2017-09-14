package com.place.eat.resturantsearch.util;

import android.os.Parcelable;

import com.place.eat.resturantsearch.model.jsonmodel.Cuisine;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine_;

import java.util.ArrayList;

/**
 * Created by aman on 14/9/17.
 */

public interface FragmentChange{
    void cuisineSelectFrag(ArrayList<Parcelable> parcelables);
    void restaurantListFragment(Cuisine_ cuisine);
}
