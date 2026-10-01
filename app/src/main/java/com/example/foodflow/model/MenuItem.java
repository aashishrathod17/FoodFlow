package com.example.foodflow.model;

public class MenuItem {

    private int id;
    private String name;
    private String description;
    private String price;
    private int imageResource;

    public MenuItem(int id, String name, String description,
                    String price, int imageResource) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}