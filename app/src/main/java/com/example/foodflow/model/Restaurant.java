package com.example.foodflow.model;

public class Restaurant {

    private int id;
    private String name;
    private String category;
    private String rating;
    private String deliveryTime;
    private int imageResource;

    public Restaurant(int id, String name, String category,
                      String rating, String deliveryTime,
                      int imageResource) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getRating() {
        return rating;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }
}