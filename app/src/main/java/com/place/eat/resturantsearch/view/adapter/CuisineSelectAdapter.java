package com.place.eat.resturantsearch.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.place.eat.resturantsearch.R;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine;
import com.place.eat.resturantsearch.model.jsonmodel.Cuisine_;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 14/9/17.
 */

public class CuisineSelectAdapter extends RecyclerView.Adapter<CuisineSelectAdapter.MyViewHolder> {

    private Context context;
    private List<Cuisine> cuisines;
    private LayoutInflater inflater;

    public CuisineSelectAdapter(Context context, List<Cuisine> cuisines) {
        this.context = context;
        this.cuisines = cuisines;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cuisine_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Cuisine c = cuisines.get(position);
        if (c == null) {
            return;
        }

        final Cuisine_ cuisine = c.getCuisine();
        holder.checkBox.setText(cuisine.getCuisineName());
        holder.checkBox.setChecked(cuisine.isChecked());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean enabled = !cuisine.isChecked();
                holder.checkBox.setChecked(enabled);
                cuisine.setChecked(enabled);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cuisines == null ? 0 : cuisines.size();
    }

    public List<Cuisine> getSelectedCuisines() {
        List<Cuisine> cuisines = new ArrayList<>();
        for (Cuisine cuisine : this.cuisines) {
            if (cuisine.getCuisine().isChecked()) {
                cuisines.add(cuisine);
            }
        }
        return cuisines;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
