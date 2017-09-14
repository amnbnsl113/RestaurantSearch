package com.place.eat.resturantsearch.view.fragment;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.place.eat.resturantsearch.R;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine;
import com.place.eat.resturantsearch.model.jsonmodel.CuisineModel;
import com.place.eat.resturantsearch.util.FragmentChange;
import com.place.eat.resturantsearch.view.adapter.CuisineSelectAdapter;

import org.parceler.Parcels;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 14/9/17.
 */

public class CuisineSelectFragment extends BaseFragment {

    private CuisineSelectAdapter adapter;
    private FragmentChange fragmentChange;

    @Override
    protected int getFragmentLayout() {
        return R.layout.cuisine_select_frag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentChange) {
            fragmentChange = (FragmentChange) context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Select Cuisine");
    }

    @Override
    protected void initViews(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
        adapter = new CuisineSelectAdapter(getActivity(), getCuisineList());
        recyclerView.setAdapter(adapter);

        Button submitButton = view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(onClickListener);
    }

    private List<Cuisine> getCuisineList() {
        InputStream in = getResources().openRawResource(R.raw.cuisine);
        Reader rd = new BufferedReader(new InputStreamReader(in));
        CuisineModel cuisineModel = new Gson().fromJson(rd, CuisineModel.class);

        return cuisineModel.getCuisines();
    }

    private void onSubmit() {
        if (adapter != null) {
            List<Cuisine> cuisines = adapter.getSelectedCuisines();
            if (cuisines.size() < 4) {
                Toast.makeText(getActivity(), getString(R.string.cuisine_select_text), Toast.LENGTH_SHORT).show();
            } else {
                ArrayList<Parcelable> parcelableList = new ArrayList<>();
                for (Cuisine cuisine : cuisines) {
                    parcelableList.add(Parcels.wrap(cuisine));
                }
                if (fragmentChange != null) {
                    fragmentChange.cuisineSelectFrag(parcelableList);
                }
            }
        }
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.submitButton) {
                onSubmit();
            }
        }
    };

}
