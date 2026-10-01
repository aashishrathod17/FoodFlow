package com.example.foodflow;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseSeeder {

    private static final String TAG = "FirebaseSeeder";

    public static void seedData() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // =========================
        // PIZZA PALACE
        // =========================

        Map<String, Object> pizza = new HashMap<>();
        pizza.put("name", "Pizza Palace");
        pizza.put("category", "Italian • Pizza");
        pizza.put("rating", "4.5");
        pizza.put("deliveryTime", "25 min");
        pizza.put("imageName", "pizza_palace");

        db.collection("restaurants")
                .document("1")
                .set(pizza)
                .addOnSuccessListener(unused ->
                        Log.d(TAG, "Pizza Palace saved successfully")
                )
                .addOnFailureListener(e ->
                        Log.e(TAG, "Pizza Palace ERROR", e)
                );

        addMenuItem(db, "1", "1",
                "Margherita Pizza",
                "Classic cheese and tomato pizza",
                "199",
                "margherita_pizza");

        addMenuItem(db, "1", "2",
                "Farmhouse Pizza",
                "Fresh vegetables with mozzarella cheese",
                "249",
                "farmhouse_pizza");

        addMenuItem(db, "1", "3",
                "Paneer Tikka Pizza",
                "Paneer, onion, capsicum and cheese",
                "279",
                "paneer_tikka_pizza");


        // =========================
        // BURGER HOUSE
        // =========================

        Map<String, Object> burger = new HashMap<>();
        burger.put("name", "Burger House");
        burger.put("category", "Fast Food • Burger");
        burger.put("rating", "4.3");
        burger.put("deliveryTime", "20 min");
        burger.put("imageName", "burger_house");

        db.collection("restaurants")
                .document("2")
                .set(burger)
                .addOnSuccessListener(unused ->
                        Log.d(TAG, "Burger House saved successfully")
                )
                .addOnFailureListener(e ->
                        Log.e(TAG, "Burger House ERROR", e)
                );

        addMenuItem(db, "2", "4",
                "Classic Burger",
                "Crispy patty with fresh vegetables",
                "149",
                "classic_burger");

        addMenuItem(db, "2", "5",
                "Cheese Burger",
                "Classic burger with extra cheese",
                "179",
                "cheese_burger");

        addMenuItem(db, "2", "6",
                "Veggie Burger",
                "Fresh vegetable patty with special sauce",
                "129",
                "veggie_burger");


        // =========================
        // DRAGON WOK
        // =========================

        Map<String, Object> chinese = new HashMap<>();
        chinese.put("name", "Dragon Wok");
        chinese.put("category", "Chinese • Asian");
        chinese.put("rating", "4.4");
        chinese.put("deliveryTime", "30 min");
        chinese.put("imageName", "dragon_wok");

        db.collection("restaurants")
                .document("3")
                .set(chinese)
                .addOnSuccessListener(unused ->
                        Log.d(TAG, "Dragon Wok saved successfully")
                )
                .addOnFailureListener(e ->
                        Log.e(TAG, "Dragon Wok ERROR", e)
                );

        addMenuItem(db, "3", "7",
                "Veg Hakka Noodles",
                "Stir-fried noodles with fresh vegetables",
                "169",
                "veg_hakka_noodles");

        addMenuItem(db, "3", "8",
                "Veg Manchurian",
                "Crispy vegetable balls with Manchurian sauce",
                "159",
                "veg_manchurian");

        addMenuItem(db, "3", "9",
                "Schezwan Fried Rice",
                "Spicy fried rice with vegetables",
                "179",
                "schezwan_fried_rice");
    }


    private static void addMenuItem(
            FirebaseFirestore db,
            String restaurantId,
            String itemId,
            String name,
            String description,
            String price,
            String imageName
    ) {

        Map<String, Object> item = new HashMap<>();

        item.put("name", name);
        item.put("description", description);
        item.put("price", price);
        item.put("imageName", imageName);

        db.collection("restaurants")
                .document(restaurantId)
                .collection("menuItems")
                .document(itemId)
                .set(item)
                .addOnSuccessListener(unused ->
                        Log.d(TAG,
                                "Menu saved: " + restaurantId + "/" + itemId)
                )
                .addOnFailureListener(e ->
                        Log.e(TAG,
                                "Menu ERROR: "
                                        + restaurantId + "/" + itemId,
                                e)
                );
    }
}