package com.place.eat.resturantsearch.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.place.eat.resturantsearch.R;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine_;
import com.place.eat.resturantsearch.util.FragmentChange;
import com.place.eat.resturantsearch.view.fragment.CuisineCarListFragment;
import com.place.eat.resturantsearch.view.fragment.CuisineSelectFragment;
import com.place.eat.resturantsearch.view.fragment.RestaurantListFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements FragmentChange {

    private Fragment currFrag;
    private CuisineCarListFragment cuisineCarListFragment;
    private CuisineSelectFragment cuisineSelectFragment;

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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.searchView);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();

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
        Toast.makeText(this, "Sort", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void activityCreated() {
        pushFragment(R.id.frame_container, cuisineSelectFragment = new CuisineSelectFragment(), false);
        currFrag = cuisineSelectFragment;
    }


    @Override
    public void cuisineSelectFrag(ArrayList<Parcelable> parcelables) {
        currFrag = CuisineCarListFragment.getInstance(parcelables);
        pushFragment(R.id.frame_container, currFrag, true);
    }

    @Override
    public void restaurantListFragment(Cuisine_ cuisine) {
        currFrag = RestaurantListFragment.getInstance(cuisine);
        pushFragment(R.id.frame_container, currFrag, true);
    }


    private Handler handler = new Handler(Looper.myLooper());
    private Runnable workRunnable;
    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if (currFrag instanceof CuisineCarListFragment) {
                ((CuisineCarListFragment) currFrag).onTextSubmit(query);
            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(final String newText) {
            handler.removeCallbacks(workRunnable);
            workRunnable = new Runnable() {
                @Override
                public void run() {
                    if (currFrag instanceof CuisineCarListFragment) {
                        ((CuisineCarListFragment) currFrag).onTextSubmit(newText);
                    }
                }
            };

            handler.postDelayed(workRunnable, 3000);


            return false;
        }
    };


}
