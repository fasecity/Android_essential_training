package com.example.admin.study2;

/**
 * Created by admin on 5/22/2017.
 */

public class Product {
    private String productId;
    private String name;
    private String description;
    private double price;

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description + "\n";
    }

    public double getPrice() {
        return price;
    }

    public Product(String productId, String name, String description, double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
