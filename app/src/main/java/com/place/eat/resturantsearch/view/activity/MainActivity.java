package com.place.eat.resturantsearch.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.place.eat.resturantsearch.R;
import com.place.eat.resturantsearch.view.fragment.RestaurantListFragment;

public class MainActivity extends BaseActivity {

    private RestaurantListFragment restaurantListFragment;

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
        pushFragment(R.id.frame_container, restaurantListFragment = new RestaurantListFragment(), false);
    }


    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            restaurantListFragment.onTextSubmit(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            restaurantListFragment.onTextSubmit(newText);
            return false;
        }
    };


}
