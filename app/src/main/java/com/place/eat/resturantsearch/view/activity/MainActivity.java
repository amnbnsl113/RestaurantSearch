package com.place.eat.resturantsearch.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.place.eat.resturantsearch.R;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine_;
import com.place.eat.resturantsearch.util.Constants;
import com.place.eat.resturantsearch.util.FragmentChange;
import com.place.eat.resturantsearch.view.fragment.CuisineCarListFragment;
import com.place.eat.resturantsearch.view.fragment.CuisineSelectFragment;
import com.place.eat.resturantsearch.view.fragment.RestaurantListFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements FragmentChange {

    private Handler handler = new Handler(Looper.myLooper());
    private Runnable workRunnable;
    private SearchView searchView;
    private MenuItem sortItem;
    private BottomSheetBehavior<RelativeLayout> bottomSheetBehavior;
    private MenuItem searchMenuItem;

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        RelativeLayout bottomSheetSortLayout = (RelativeLayout) findViewById(R.id.bottom_sheet_sort_layout);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetSortLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

    }

    @Override
    public void onBackPressed() {
        if (bottomSheetBehavior != null && bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            return;
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);

        sortItem = menu.findItem(R.id.sort);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.searchView);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(onQueryTextListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sort) {
            showSortScreen();
        }
        return true;
    }

    private void showSortScreen() {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_SETTLING);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    @Override
    public void activityCreated() {
        CuisineSelectFragment cuisineSelectFragment;
        pushFragment(R.id.frame_container, cuisineSelectFragment = new CuisineSelectFragment(), false);
    }


    @Override
    public void cuisineSelectFrag(ArrayList<Parcelable> parcelables) {
        Fragment currFrag = CuisineCarListFragment.getInstance(parcelables);
        pushFragment(R.id.frame_container, currFrag, true);
    }

    @Override
    public void restaurantListFragment(Cuisine_ cuisine) {
        Fragment currFrag = RestaurantListFragment.getInstance(cuisine);
        pushFragment(R.id.frame_container, currFrag, true);
    }


    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            hitQuery(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(final String newText) {
            handler.removeCallbacks(workRunnable);
            workRunnable = new Runnable() {
                @Override
                public void run() {
                    hitQuery(newText);
                }
            };

            handler.postDelayed(workRunnable, 2000);
            return false;
        }
    };

    private void hitQuery(String query) {
        Fragment currFrag = getCurrentFragment();
        if (currFrag instanceof CuisineCarListFragment) {
            ((CuisineCarListFragment) currFrag).onTextSubmit(query);
        } else if (currFrag instanceof RestaurantListFragment) {
            ((RestaurantListFragment) currFrag).onTextSubmit(query);
        }
    }

    public void sortData(View view) {
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }

        switch (view.getId()) {
            case R.id.ratingAsc: {
                sortData(Constants.SORT_BY.RATING, Constants.SORT_ORDER.ASC);
                break;
            }
            case R.id.ratingDesc: {
                sortData(Constants.SORT_BY.RATING, Constants.SORT_ORDER.DESC);
                break;
            }
            case R.id.costAsc: {
                sortData(Constants.SORT_BY.COST, Constants.SORT_ORDER.ASC);
                break;
            }
            case R.id.costDesc: {
                sortData(Constants.SORT_BY.COST, Constants.SORT_ORDER.DESC);
                break;
            }
            case R.id.distanceAsc: {
                sortData(Constants.SORT_BY.DISTANCE, Constants.SORT_ORDER.ASC);
                break;
            }
            case R.id.distanceDesc: {
                sortData(Constants.SORT_BY.DISTANCE, Constants.SORT_ORDER.DESC);
                break;
            }
        }

    }

    private void sortData(String sortBy, String sortOrder) {
        Fragment fragment = getCurrentFragment();
        if (fragment instanceof RestaurantListFragment) {
            ((RestaurantListFragment) fragment).onSort(sortBy, sortOrder);
        }
    }

    public String getSearchViewText() {
        if (searchView != null) {
            return String.valueOf(searchView.getQuery());
        }
        return "";
    }

    public void setSortIconVisibility(boolean visibility) {
        if (sortItem != null) {
            sortItem.setVisible(visibility);
        }
    }

    public void setSearchIconVisibility(boolean visibility) {
        if (searchMenuItem != null) {
            searchMenuItem.setVisible(visibility);
        }
    }
}
