package com.example.foodflow;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.adapter.RestaurantAdapter;
import com.example.foodflow.model.Restaurant;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerRestaurants;
    EditText etSearch;

    List<Restaurant> restaurantList;
    List<Restaurant> filteredRestaurantList;

    RestaurantAdapter restaurantAdapter;
    TextView tvNoResults;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // =========================
        // FIND VIEWS
        // =========================

        recyclerRestaurants = findViewById(R.id.recyclerRestaurants);
        etSearch = findViewById(R.id.etSearch);
        tvNoResults = findViewById(R.id.tvNoResults);

        Button btnPizza = findViewById(R.id.btnPizza);
        Button btnBurger = findViewById(R.id.btnBurger);
        Button btnChinese = findViewById(R.id.btnChinese);

        LinearLayout navCart = findViewById(R.id.navCart);

        // =========================
        // FIREBASE
        // =========================

        db = FirebaseFirestore.getInstance();

        // =========================
        // LISTS
        // =========================

        restaurantList = new ArrayList<>();
        filteredRestaurantList = new ArrayList<>();

        // =========================
        // RECYCLER VIEW
        // =========================

        restaurantAdapter = new RestaurantAdapter(
                this,
                filteredRestaurantList
        );

        recyclerRestaurants.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerRestaurants.setAdapter(restaurantAdapter);

        // =========================
        // CART NAVIGATION
        // =========================

        navCart.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    CartActivity.class
            );

            startActivity(intent);
        });

        // =========================
        // CATEGORY BUTTONS
        // =========================

        btnPizza.setOnClickListener(v -> {
            filterRestaurants("pizza");
        });

        btnBurger.setOnClickListener(v -> {
            filterRestaurants("burger");
        });

        btnChinese.setOnClickListener(v -> {
            filterRestaurants("chinese");
        });

        // =========================
        // SEARCH
        // =========================

        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence s,
                    int start,
                    int count,
                    int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s,
                    int start,
                    int before,
                    int count) {

                filterRestaurants(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // =========================
        // LOAD RESTAURANTS
        // =========================

        loadRestaurantsFromFirebase();
    }

    // =====================================================
    // LOAD RESTAURANTS FROM FIREBASE
    // =====================================================

    private void loadRestaurantsFromFirebase() {

        db.collection("restaurants")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    restaurantList.clear();

                    for (QueryDocumentSnapshot document :
                            queryDocumentSnapshots) {

                        // Firebase document ID
                        int id = Integer.parseInt(
                                document.getId()
                        );

                        String name = document.getString("name");
                        String category = document.getString("category");
                        String rating = document.getString("rating");
                        String deliveryTime =
                                document.getString("deliveryTime");

                        String imageName =
                                document.getString("imageName");

                        // Convert Firebase imageName
                        // into local drawable resource
                        int imageResource =
                                getDrawableResource(imageName);

                        Restaurant restaurant =
                                new Restaurant(
                                        id,
                                        name,
                                        category,
                                        rating,
                                        deliveryTime,
                                        imageResource
                                );

                        restaurantList.add(restaurant);
                    }

                    // Show all restaurants initially
                    filteredRestaurantList.clear();
                    filteredRestaurantList.addAll(
                            restaurantList
                    );

                    restaurantAdapter.notifyDataSetChanged();

                    // Empty result handling
                    if (filteredRestaurantList.isEmpty()) {

                        tvNoResults.setVisibility(
                                TextView.VISIBLE
                        );

                        recyclerRestaurants.setVisibility(
                                RecyclerView.GONE
                        );

                    } else {

                        tvNoResults.setVisibility(
                                TextView.GONE
                        );

                        recyclerRestaurants.setVisibility(
                                RecyclerView.VISIBLE
                        );
                    }

                    Toast.makeText(
                            MainActivity.this,
                            "Restaurants loaded from Firebase",
                            Toast.LENGTH_SHORT
                    ).show();
                })
                .addOnFailureListener(e -> {

                    Toast.makeText(
                            MainActivity.this,
                            "Failed to load restaurants",
                            Toast.LENGTH_LONG
                    ).show();

                    tvNoResults.setText(
                            "Unable to load restaurants"
                    );

                    tvNoResults.setVisibility(
                            TextView.VISIBLE
                    );

                    recyclerRestaurants.setVisibility(
                            RecyclerView.GONE
                    );
                });
    }

    // =====================================================
    // FILTER RESTAURANTS
    // =====================================================

    private void filterRestaurants(String query) {

        filteredRestaurantList.clear();

        String searchText =
                query.toLowerCase().trim();

        if (searchText.isEmpty()) {

            filteredRestaurantList.addAll(
                    restaurantList
            );

        } else {

            for (Restaurant restaurant :
                    restaurantList) {

                if (restaurant.getName()
                        .toLowerCase()
                        .contains(searchText)
                        ||
                        restaurant.getCategory()
                                .toLowerCase()
                                .contains(searchText)) {

                    filteredRestaurantList.add(
                            restaurant
                    );
                }
            }
        }

        restaurantAdapter.notifyDataSetChanged();

        if (filteredRestaurantList.isEmpty()) {

            tvNoResults.setVisibility(
                    TextView.VISIBLE
            );

            recyclerRestaurants.setVisibility(
                    RecyclerView.GONE
            );

        } else {

            tvNoResults.setVisibility(
                    TextView.GONE
            );

            recyclerRestaurants.setVisibility(
                    RecyclerView.VISIBLE
            );
        }
    }

    // =====================================================
    // FIREBASE IMAGE NAME → DRAWABLE RESOURCE
    // =====================================================

    private int getDrawableResource(String imageName) {

        if (imageName == null) {
            return R.drawable.pizza_palace;
        }

        int resourceId = getResources().getIdentifier(
                imageName,
                "drawable",
                getPackageName()
        );

        // If image is not found
        if (resourceId == 0) {
            return R.drawable.pizza_palace;
        }

        return resourceId;
    }
}