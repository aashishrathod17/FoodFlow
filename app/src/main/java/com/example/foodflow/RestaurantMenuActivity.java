package com.example.foodflow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.adapter.MenuAdapter;
import com.example.foodflow.model.MenuItem;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMenuActivity extends AppCompatActivity {

    RecyclerView recyclerMenuItems;
    TextView tvMenuRestaurantName;
    TextView tvMenuRestaurantInfo;
    Button btnViewCart;

    List<MenuItem> menuItemList;
    MenuAdapter menuAdapter;

    FirebaseFirestore db;

    int restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        // =========================
        // FIND VIEWS
        // =========================

        recyclerMenuItems = findViewById(R.id.recyclerMenuItems);
        tvMenuRestaurantName = findViewById(R.id.tvMenuRestaurantName);
        tvMenuRestaurantInfo = findViewById(R.id.tvMenuRestaurantInfo);
        btnViewCart = findViewById(R.id.btnViewCart);

        // =========================
        // FIREBASE
        // =========================

        db = FirebaseFirestore.getInstance();

        // =========================
        // GET RESTAURANT ID
        // =========================

        restaurantId = getIntent().getIntExtra(
                "restaurant_id",
                1
        );

        // =========================
        // MENU LIST
        // =========================

        menuItemList = new ArrayList<>();

        menuAdapter = new MenuAdapter(menuItemList);

        recyclerMenuItems.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerMenuItems.setAdapter(menuAdapter);

        // =========================
        // VIEW CART
        // =========================

        btnViewCart.setOnClickListener(v -> {

            Intent intent = new Intent(
                    RestaurantMenuActivity.this,
                    CartActivity.class
            );

            startActivity(intent);
        });

        // =========================
        // LOAD RESTAURANT
        // =========================

        loadRestaurant();

        // =========================
        // LOAD MENU
        // =========================

        loadMenuItems();
    }

    // =====================================================
    // LOAD RESTAURANT DETAILS
    // =====================================================

    private void loadRestaurant() {

        db.collection("restaurants")
                .document(String.valueOf(restaurantId))
                .get()
                .addOnSuccessListener(documentSnapshot -> {

                    if (documentSnapshot.exists()) {

                        String name =
                                documentSnapshot.getString("name");

                        String category =
                                documentSnapshot.getString("category");

                        String rating =
                                documentSnapshot.getString("rating");

                        String deliveryTime =
                                documentSnapshot.getString("deliveryTime");

                        tvMenuRestaurantName.setText(name);

                        tvMenuRestaurantInfo.setText(
                                "⭐ " + rating
                                        + "  •  "
                                        + deliveryTime
                                        + "  •  "
                                        + category
                        );
                    }
                })
                .addOnFailureListener(e -> {

                    Toast.makeText(
                            RestaurantMenuActivity.this,
                            "Failed to load restaurant",
                            Toast.LENGTH_SHORT
                    ).show();
                });
    }

    // =====================================================
    // LOAD MENU ITEMS FROM FIREBASE
    // =====================================================

    private void loadMenuItems() {

        db.collection("restaurants")
                .document(String.valueOf(restaurantId))
                .collection("menuItems")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    menuItemList.clear();

                    for (DocumentSnapshot document :
                            queryDocumentSnapshots.getDocuments()) {

                        // Firebase document ID
                        int id = Integer.parseInt(
                                document.getId()
                        );

                        String name =
                                document.getString("name");

                        String description =
                                document.getString("description");

                        String price =
                                document.getString("price");

                        String imageName =
                                document.getString("imageName");

                        // Convert Firebase imageName
                        // to local drawable
                        int imageResource =
                                getDrawableResource(imageName);

                        MenuItem menuItem =
                                new MenuItem(
                                        id,
                                        name,
                                        description,
                                        price,
                                        imageResource
                                );

                        menuItemList.add(menuItem);
                    }

                    menuAdapter.notifyDataSetChanged();

                    Toast.makeText(
                            RestaurantMenuActivity.this,
                            "Menu loaded from Firebase",
                            Toast.LENGTH_SHORT
                    ).show();
                })
                .addOnFailureListener(e -> {

                    Toast.makeText(
                            RestaurantMenuActivity.this,
                            "Failed to load menu",
                            Toast.LENGTH_LONG
                    ).show();
                });
    }

    // =====================================================
    // FIREBASE IMAGE NAME → DRAWABLE
    // =====================================================

    private int getDrawableResource(String imageName) {

        if (imageName == null) {
            return R.drawable.margherita_pizza;
        }

        int resourceId = getResources().getIdentifier(
                imageName,
                "drawable",
                getPackageName()
        );

        if (resourceId == 0) {
            return R.drawable.margherita_pizza;
        }

        return resourceId;
    }
}