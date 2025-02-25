package com.example.menurestaurant;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String name;
    private String description;
    private double price;
    private int imageResId;

    public MenuItem(String name, String description, double price, int imageResId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
