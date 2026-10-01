package com.example.foodflow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.CartManager;
import com.example.foodflow.R;
import com.example.foodflow.model.CartItem;

import java.util.List;
import android.widget.ImageView;

public class CartAdapter
        extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItemList;
    private Runnable onCartChanged;

    public CartAdapter(List<CartItem> cartItemList, Runnable onCartChanged) {
        this.cartItemList = cartItemList;
        this.onCartChanged = onCartChanged;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);

        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull CartViewHolder holder, int position) {

        CartItem cartItem = cartItemList.get(position);

        holder.ivCartFoodImage.setImageResource(
                cartItem.getMenuItem().getImageResource()
        );

        holder.tvFoodName.setText(
                cartItem.getMenuItem().getName()
        );

        holder.tvFoodPrice.setText(
                "₹" + cartItem.getMenuItem().getPrice()
        );

        holder.tvQuantity.setText(
                String.valueOf(cartItem.getQuantity())
        );

        holder.tvItemTotal.setText(
                "Item Total: ₹" + cartItem.getTotalPrice()
        );

        holder.btnIncrease.setOnClickListener(v -> {

            cartItem.increaseQuantity();

            notifyItemChanged(position);

            onCartChanged.run();
        });

        holder.btnDecrease.setOnClickListener(v -> {

            cartItem.decreaseQuantity();

            notifyItemChanged(position);

            onCartChanged.run();
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class CartViewHolder
            extends RecyclerView.ViewHolder {

        TextView tvFoodName;
        TextView tvFoodPrice;
        TextView tvQuantity;
        TextView tvItemTotal;

        Button btnDecrease;
        Button btnIncrease;
        ImageView ivCartFoodImage;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFoodName = itemView.findViewById(R.id.tvCartFoodName);
            tvFoodPrice = itemView.findViewById(R.id.tvCartFoodPrice);
            tvQuantity = itemView.findViewById(R.id.tvCartQuantity);
            tvItemTotal = itemView.findViewById(R.id.tvCartItemTotal);

            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            ivCartFoodImage = itemView.findViewById(R.id.ivCartFoodImage);
        }
    }
}