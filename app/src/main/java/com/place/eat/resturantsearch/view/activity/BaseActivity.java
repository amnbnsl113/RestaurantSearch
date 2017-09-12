package com.place.eat.resturantsearch.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;

import com.place.eat.resturantsearch.R;

/**
 * Created by aman on 12/9/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());

        initView();
        activityCreated();
    }

    protected abstract void initView();

    public void activityCreated() {

    }

    protected abstract int getActivityLayout();


    protected void pushFragment(int containerId, Fragment destinationFragment, boolean addToBackStack) {
        pushFragment(containerId, null, destinationFragment, null, addToBackStack);
    }

    protected void pushFragment(int containerId, Bundle bundle, Fragment destinationFragment,
                                String tag, boolean addToBackStack) {
        if (destinationFragment == null) {
            throw new IllegalArgumentException("Destination Fragment is not defined.");
        }
        String backStateName = destinationFragment.getClass().getName();
        if (TextUtils.isEmpty(tag)) {
            tag = backStateName;
        }

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (fragmentPopped) {
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        if (bundle != null) {
            destinationFragment.setArguments(bundle);
        }

        ft.replace(containerId, destinationFragment, tag);
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }
}
