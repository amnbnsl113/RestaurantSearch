package com.place.eat.resturantsearch.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.place.eat.resturantsearch.R;
import com.place.eat.resturantsearch.model.viewmodel.RestaurantViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aman on 12/9/17.
 */

public class CuisineRestaurantAdapter extends RecyclerView.Adapter<CuisineRestaurantAdapter.MyViewHolder> {
    private List<RestaurantViewModel> restaurantViewModels;
    private Context context;
    private LayoutInflater inflater;
    private boolean isGrid;

    public CuisineRestaurantAdapter(List<RestaurantViewModel> restaurantViewModelList, Context context, boolean isGrid) {
        this.restaurantViewModels = restaurantViewModelList;
        this.context = context;
        this.isGrid = isGrid;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (isGrid) {
            view = inflater.inflate(R.layout.restaurant_item_grid, parent, false);
        } else {
            view = inflater.inflate(R.layout.restaurant_item, parent, false);
        }

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RestaurantViewModel restaurantViewModel = restaurantViewModels.get(position);

        holder.titleText.setText(restaurantViewModel.getName());
        holder.descText.setText(restaurantViewModel.getPriceForTwo());

        Glide.with(context)
                .load(restaurantViewModel.getThumpUrl())
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.thumb);

    }

    @Override
    public int getItemCount() {
        return restaurantViewModels == null ? 0 : restaurantViewModels.size();
    }

    public void swapItems(List<RestaurantViewModel> restaurantList) {
        if (restaurantList != null) {
            restaurantViewModels = restaurantList;
            notifyDataSetChanged();
        }
    }

    public void addItems(List<RestaurantViewModel> restaurantList) {
        if (restaurantViewModels == null) {
            restaurantViewModels = new ArrayList<>();
        }
        restaurantViewModels.addAll(restaurantList);
        notifyDataSetChanged();

    }

    public void clearItems() {
        if (restaurantViewModels != null) {
            int size = restaurantViewModels.size();
            restaurantViewModels.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumb;
        private TextView titleText;
        private TextView descText;

        public MyViewHolder(View itemView) {
            super(itemView);
            thumb = (ImageView) itemView.findViewById(R.id.thumb);
            titleText = (TextView) itemView.findViewById(R.id.titleText);
            descText = (TextView) itemView.findViewById(R.id.descText);

        }
    }
}
