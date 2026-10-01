package com.example.foodflow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.R;
import com.example.foodflow.model.MenuItem;

import java.util.List;
import android.widget.Toast;

import com.example.foodflow.CartManager;

import android.widget.ImageView;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<MenuItem> menuItemList;

    public MenuAdapter(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull MenuViewHolder holder, int position) {

        MenuItem item = menuItemList.get(position);

        holder.tvFoodName.setText(item.getName());
        holder.tvFoodDescription.setText(item.getDescription());
        holder.tvFoodPrice.setText("₹" + item.getPrice());

        holder.ivFoodImage.setImageResource(
                item.getImageResource()
        );

        holder.btnAddFood.setOnClickListener(v -> {

            CartManager.getInstance().addToCart(item);

            Toast.makeText(
                    v.getContext(),
                    item.getName() + " added to cart",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvFoodName;
        TextView tvFoodDescription;
        TextView tvFoodPrice;
        Button btnAddFood;
        ImageView ivFoodImage;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvFoodDescription = itemView.findViewById(R.id.tvFoodDescription);
            tvFoodPrice = itemView.findViewById(R.id.tvFoodPrice);
            btnAddFood = itemView.findViewById(R.id.btnAddFood);
            ivFoodImage = itemView.findViewById(R.id.ivFoodImage);
        }
    }
}