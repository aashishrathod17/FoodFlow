package com.example.foodflow.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.R;
import com.example.foodflow.RestaurantMenuActivity;
import com.example.foodflow.model.Restaurant;

import android.widget.ImageView;

import java.util.List;

public class RestaurantAdapter
        extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurantList;
    private Context context;

    public RestaurantAdapter(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);

        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull RestaurantViewHolder holder, int position) {

        Restaurant restaurant = restaurantList.get(position);

        holder.tvName.setText(restaurant.getName());
        holder.tvCategory.setText(restaurant.getCategory());

        holder.ivRestaurantImage.setImageResource(
                restaurant.getImageResource()
        );

        holder.tvInfo.setText(
                "⭐ " + restaurant.getRating()
                        + "  •  " + restaurant.getDeliveryTime()
        );

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(
                    context,
                    RestaurantMenuActivity.class
            );

            intent.putExtra("restaurant_id", restaurant.getId());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class RestaurantViewHolder
            extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvCategory;
        TextView tvInfo;
        ImageView ivRestaurantImage;


        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvRestaurantName);
            tvCategory = itemView.findViewById(R.id.tvRestaurantCategory);
            tvInfo = itemView.findViewById(R.id.tvRestaurantInfo);

            ivRestaurantImage = itemView.findViewById(R.id.ivRestaurantImage);
        }
    }
}