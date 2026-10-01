package com.example.foodflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.adapter.CartAdapter;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerCartItems;

    TextView tvCartItemCount;
    TextView tvCartSubtotal;
    TextView tvDeliveryFee;
    TextView tvCartTotal;

    Button btnCheckout;
    Button btnBrowseFood;

    LinearLayout emptyCartLayout;
    LinearLayout cartSummaryLayout;

    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cart);

        recyclerCartItems = findViewById(R.id.recyclerCartItems);

        tvCartItemCount = findViewById(R.id.tvCartItemCount);
        tvCartSubtotal = findViewById(R.id.tvCartSubtotal);
        tvDeliveryFee = findViewById(R.id.tvDeliveryFee);
        tvCartTotal = findViewById(R.id.tvCartTotal);

        btnCheckout = findViewById(R.id.btnCheckout);
        btnBrowseFood = findViewById(R.id.btnBrowseFood);

        emptyCartLayout = findViewById(R.id.emptyCartLayout);
        cartSummaryLayout = findViewById(R.id.cartSummaryLayout);

        recyclerCartItems.setLayoutManager(
                new LinearLayoutManager(this)
        );

        cartAdapter = new CartAdapter(
                CartManager.getInstance().getCartItems(),
                this::updateCartSummary
        );

        recyclerCartItems.setAdapter(cartAdapter);

        updateCartSummary();

        btnCheckout.setOnClickListener(v -> {

            if (CartManager.getInstance()
                    .getCartItems()
                    .isEmpty()) {

                Toast.makeText(
                        this,
                        "Your cart is empty",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Intent intent = new Intent(
                    CartActivity.this,
                    CheckoutActivity.class
            );

            startActivity(intent);
        });

        btnBrowseFood.setOnClickListener(v -> {

            Intent intent = new Intent(
                    CartActivity.this,
                    MainActivity.class
            );

            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (cartAdapter != null) {
            cartAdapter.notifyDataSetChanged();
        }

        updateCartSummary();
    }

    private void updateCartSummary() {

        CartManager cartManager = CartManager.getInstance();

        int itemCount = cartManager.getCartItemCount();
        int subtotal = cartManager.getCartTotal();

        boolean isEmpty = cartManager
                .getCartItems()
                .isEmpty();

        if (isEmpty) {

            recyclerCartItems.setVisibility(View.GONE);
            emptyCartLayout.setVisibility(View.VISIBLE);
            cartSummaryLayout.setVisibility(View.GONE);

            tvCartItemCount.setText("0 items");

        } else {

            recyclerCartItems.setVisibility(View.VISIBLE);
            emptyCartLayout.setVisibility(View.GONE);
            cartSummaryLayout.setVisibility(View.VISIBLE);

            int deliveryFee = 40;
            int total = subtotal + deliveryFee;

            tvCartItemCount.setText(
                    itemCount + (itemCount == 1
                            ? " item"
                            : " items")
            );

            tvCartSubtotal.setText("₹" + subtotal);
            tvDeliveryFee.setText("₹" + deliveryFee);
            tvCartTotal.setText("₹" + total);
        }
    }
}
